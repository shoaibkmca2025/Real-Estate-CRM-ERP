import axios, { AxiosError } from 'axios';

/**
 * Central HTTP client — replaces the legacy `$http` + `config.url` constant.
 * Base URL comes from VITE_API_BASE_URL (default `/api/`, proxied to the
 * Spring Boot backend in dev). A JWT, once the backend is upgraded, is
 * attached automatically; until then session info is read from storage.
 */
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api/',
  headers: {
    'Content-Type': 'application/json',
  },
});

// --- Request interceptor: attach auth token if present -------------------
apiClient.interceptors.request.use((config) => {
  const token = sessionStorage.getItem('authToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// --- Response interceptor: centralised error handling --------------------
// On a genuine auth failure (401/403) we must CLEAR the stored token + session
// before redirecting — otherwise the guard still sees a session, sends the user
// back into the app, the stale token 401s again, and the app "repeatedly logs
// out" in a loop between the dashboard and the login page.
apiClient.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    const status = error.response?.status;
    const url = error.config?.url ?? '';
    // The auth endpoints legitimately return 401 (e.g. wrong password) — a
    // failed login must NOT trigger the global "session invalid" logout.
    const isAuthEndpoint = /loginWebService|forgotPassword|addLoginDetails/.test(url);

    if ((status === 401 || status === 403) && !isAuthEndpoint) {
      // Diagnostic (browser console, F12): names the call that failed auth.
      console.error(`[auth] session invalidated by ${error.config?.method?.toUpperCase()} ${url} -> HTTP ${status}`);

      // Wipe auth state so the guard stops treating the user as logged in and
      // no stale token is reused. rc_remember lives in localStorage and survives.
      sessionStorage.clear();
      sessionStorage.setItem('sessionInvalid', 'true');

      if (window.location.pathname !== '/login') {
        window.location.replace('/login');
      }
    }
    return Promise.reject(error);
  }
);

export default apiClient;

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
// Mirrors the old behaviour where a network / session failure redirected
// the user back to the login page ("session invalid").
apiClient.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    if (error.response?.status === 401 || error.response?.status === 403) {
      // Diagnostic: names the exact call that invalidated the session
      // (visible in the browser console via F12).
      console.error(
        `[auth] session invalidated by ${error.config?.method?.toUpperCase()} ` +
          `${error.config?.url} -> HTTP ${error.response.status}`
      );
      sessionStorage.setItem('sessionInvalid', 'true');
      if (window.location.pathname !== '/login') {
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default apiClient;

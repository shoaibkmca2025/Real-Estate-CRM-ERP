import { useEffect, useState, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Form } from 'react-bootstrap';
import { useAuth } from '../context/AuthContext';
import { login, forgotPassword, addLoginDetails } from '../api/authApi';
import Loader from '../components/common/Loader';

const REMEMBER_KEY = 'rc_remember';
const EMAIL_REGEX = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;

/**
 * Login screen — same fields, Remember Me, Forgot Password flow and post-login
 * routing as login.html + loginCtrl.js, with a refreshed card design.
 */
export default function Login() {
  const navigate = useNavigate();
  const { signIn } = useAuth();

  const [emailOrMobile, setEmailOrMobile] = useState('');
  const [password, setPassword] = useState('');
  const [remember, setRemember] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [showForgot, setShowForgot] = useState(false);
  const [forgotEmail, setForgotEmail] = useState('');

  // Restore the remembered credentials (replaces the old document.cookie logic).
  useEffect(() => {
    if (sessionStorage.getItem('sessionInvalid') === 'true') {
      toast.warn('Session is invalid, please login again.');
      sessionStorage.removeItem('sessionInvalid');
    }
    try {
      const saved = JSON.parse(localStorage.getItem(REMEMBER_KEY) || 'null');
      if (saved?.email) {
        setEmailOrMobile(saved.email);
        setPassword(saved.password || '');
        setRemember(true);
      }
    } catch {
      /* ignore */
    }
  }, []);

  const handleLogin = async (e: FormEvent) => {
    e.preventDefault();
    setError('');
    if (!emailOrMobile || !password) {
      setError('Please enter username and password');
      return;
    }

    // Persist / clear remembered credentials.
    if (remember) {
      localStorage.setItem(REMEMBER_KEY, JSON.stringify({ email: emailOrMobile, password }));
    } else {
      localStorage.removeItem(REMEMBER_KEY);
    }

    setLoading(true);
    try {
      const res = await login({ newEmailOrMobile: emailOrMobile, userPassword: password });
      if (res.code === 200 && res.object) {
        const user = res.object;
        // Store the JWT so apiClient's interceptor attaches it to every request.
        if (res.token) sessionStorage.setItem('authToken', res.token);
        signIn(user, res.constantFilePath);

        // Best-effort login audit (legacy addLoginDetails + browser/device info).
        void addLoginDetails({
          userId: user.userId,
          userDeviceName: navigator.userAgent,
        });

        // Post-login routing — identical branching to loginCtrl.js.
        if (user.userType === 1) {
          navigate('/admindashboard/viewCompanyDetails');
        } else if (user.userType === 2 || user.userType === 3) {
          if (user.isUpdated === 0 && user.userType === 2) {
            navigate('/userProfile');
          } else {
            navigate('/dashboard/mainDashboard');
          }
        }
      } else {
        setError(res.message || 'Wrong email/mobile or password.');
        setTimeout(() => setError(''), 5000);
      }
    } catch {
      toast.error('Unable to connect. Please check your internet connection.');
    } finally {
      setLoading(false);
    }
  };

  const handleForgot = async (e: FormEvent) => {
    e.preventDefault();
    if (!EMAIL_REGEX.test(forgotEmail)) {
      toast.error('Please enter a valid email address');
      return;
    }
    setLoading(true);
    try {
      const res = await forgotPassword(forgotEmail);
      if (res.code === 200) {
        toast.success(res.message || 'A new password has been emailed to you.');
        setShowForgot(false);
        setForgotEmail('');
      } else {
        toast.error(res.message || 'Unable to reset password.');
      }
    } catch {
      toast.error('Unable to connect. Please try again later.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <Loader show={loading} />
      <div className="login-card">
        <div className="login-brand">
          <img src="/images/Real-Estate1.png" alt="Real Estate CRM" onError={(e) => ((e.target as HTMLImageElement).style.display = 'none')} />
        </div>

        {!showForgot ? (
          <>
            <h4 className="mb-3">
              <i className="fa fa-mug-hot text-success me-2" />Login
            </h4>
            {error && <p className="red">{error}</p>}
            <Form onSubmit={handleLogin}>
              <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  size="lg"
                  value={emailOrMobile}
                  onChange={(e) => setEmailOrMobile(e.target.value)}
                  autoFocus
                  required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  size="lg"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </Form.Group>
              <div className="d-flex align-items-center justify-content-between mb-3">
                <Form.Check
                  type="checkbox"
                  label="Remember Me"
                  checked={remember}
                  onChange={(e) => setRemember(e.target.checked)}
                />
                <button type="submit" className="btn btn-primary btn-rounded">
                  <i className="fa fa-key me-2" />Login
                </button>
              </div>
              <div className="text-center">
                <a href="#forgot" className="blue" onClick={(e) => { e.preventDefault(); setShowForgot(true); }}>
                  Forgot Password
                </a>
              </div>
            </Form>
          </>
        ) : (
          <>
            <h4 className="mb-3"><i className="fa fa-key me-2" />Retrieve Password</h4>
            <p className="text-muted">
              Enter your registered email address. We'll send you a new password.
            </p>
            <Form onSubmit={handleForgot}>
              <Form.Group className="mb-4">
                <Form.Label>Email Address</Form.Label>
                <Form.Control
                  type="email"
                  size="lg"
                  value={forgotEmail}
                  onChange={(e) => setForgotEmail(e.target.value)}
                  required
                />
              </Form.Group>
              <div className="d-flex justify-content-end mb-3">
                <button type="submit" className="btn btn-primary btn-rounded">
                  Reset My Password!
                </button>
              </div>
              <div className="text-center">
                <a href="#login" className="blue" onClick={(e) => { e.preventDefault(); setShowForgot(false); }}>
                  Back to login <i className="fa fa-arrow-right" />
                </a>
              </div>
            </Form>
          </>
        )}
      </div>
    </div>
  );
}

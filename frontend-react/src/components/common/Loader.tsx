import { Spinner } from 'react-bootstrap';

/** Full-screen loading overlay — replaces the legacy `<div class="loading">`. */
export default function Loader({ show }: { show: boolean }) {
  if (!show) return null;
  return (
    <div className="loading-overlay">
      <Spinner animation="border" style={{ color: 'var(--rc-primary)' }} />
    </div>
  );
}

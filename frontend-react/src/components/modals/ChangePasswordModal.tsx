import { useState, type FormEvent } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { changePassword } from '../../api/authApi';

/** Change Password — ChangePasswordModal.html (dashboard.html) / adminDashboardCtrl.js. */
export default function ChangePasswordModal({ show, onClose }: { show: boolean; onClose: () => void }) {
  const { session } = useAuth();
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirm, setConfirm] = useState('');
  const [submitting, setSubmitting] = useState(false);

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (!session) return;
    if (newPassword.length < 6) {
      toast.error('Passwords must have at least 6 chars.');
      return;
    }
    if (newPassword !== confirm) {
      toast.error('Passwords do not match.');
      return;
    }
    setSubmitting(true);
    try {
      const res = await changePassword(session.userId, oldPassword, newPassword);
      if (res.code === 200) {
        toast.success(res.message || 'Password changed successfully.');
        setOldPassword(''); setNewPassword(''); setConfirm('');
        onClose();
      } else {
        toast.error(res.message || 'Unable to change password.');
      }
    } catch {
      toast.error('Unable to change password.');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton><Modal.Title><i className="fa fa-key me-2" />Change Password</Modal.Title></Modal.Header>
      <form onSubmit={submit}>
        <Modal.Body>
          <div className="mb-3">
            <label className="form-label">Old Password</label>
            <input type="password" className="form-control" value={oldPassword} onChange={(e) => setOldPassword(e.target.value)} required />
          </div>
          <div className="mb-3">
            <label className="form-label">New Password</label>
            <input type="password" className="form-control" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Confirm New Password</label>
            <input type="password" className="form-control" value={confirm} onChange={(e) => setConfirm(e.target.value)} required />
          </div>
        </Modal.Body>
        <Modal.Footer>
          <button type="submit" className="btn btn-sm btn-primary" disabled={submitting}><i className="fa fa-check me-1" />Update</button>
          <button type="button" className="btn btn-sm btn-danger" onClick={onClose}><i className="fa fa-times me-1" />Cancel</button>
        </Modal.Footer>
      </form>
    </Modal>
  );
}

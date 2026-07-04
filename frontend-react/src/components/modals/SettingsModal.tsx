import { useEffect, useState, type FormEvent } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getSettings, updateSettings, type Settings } from '../../api/settingsApi';

const EMPTY: Settings = {
  sendersEmail: '', followupNotificationDuration: 7, paymentDuedateDuration: 7, paymentNotificationDuration: 3,
};

/** Settings — SettingsModal.html (dashboard.html). Sliders → number inputs. */
export default function SettingsModal({ show, onClose }: { show: boolean; onClose: () => void }) {
  const { session } = useAuth();
  const [form, setForm] = useState<Settings>(EMPTY);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    if (!show || !session) return;
    getSettings(session.companyId ?? '')
      .then((res) => res.code === 200 && res.object && setForm({ ...EMPTY, ...res.object }))
      .catch(() => { /* keep defaults */ });
  }, [show, session]);

  const setNum = (k: keyof Settings, v: string) => setForm((f) => ({ ...f, [k]: Number(v) }));

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (!session) return;
    setSubmitting(true);
    try {
      const res = await updateSettings({ ...form, companyId: session.companyId });
      if (res.code === 200) {
        toast.success(res.message || 'Settings updated.');
        onClose();
      } else {
        toast.error(res.message || 'Unable to update settings.');
      }
    } catch {
      toast.error('Unable to update settings.');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton><Modal.Title><i className="fa fa-gears me-2" />Change Settings</Modal.Title></Modal.Header>
      <form onSubmit={submit}>
        <Modal.Body>
          <div className="mb-3">
            <label className="form-label">Sender's Email</label>
            <input type="email" className="form-control" value={form.sendersEmail} onChange={(e) => setForm((f) => ({ ...f, sendersEmail: e.target.value }))} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Enquiry followup after (days)</label>
            <input type="number" className="form-control" value={form.followupNotificationDuration} onChange={(e) => setNum('followupNotificationDuration', e.target.value)} />
          </div>
          <div className="mb-3">
            <label className="form-label">Payment followup after (days)</label>
            <input type="number" className="form-control" value={form.paymentDuedateDuration} onChange={(e) => setNum('paymentDuedateDuration', e.target.value)} />
          </div>
          <div className="mb-3">
            <label className="form-label">Payment notification before (days)</label>
            <input type="number" className="form-control" value={form.paymentNotificationDuration} onChange={(e) => setNum('paymentNotificationDuration', e.target.value)} />
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

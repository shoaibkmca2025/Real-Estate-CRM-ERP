import { useEffect, useState, type KeyboardEvent } from 'react';
import { Modal } from 'react-bootstrap';
import dayjs from 'dayjs';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getInprogressAndCompletedProjects, type ProjectFull } from '../../api/enquiryApi';
import {
  getMobileNumbersByType,
  getSmsListByDate,
  sendSms,
  getScheduledSms,
  type SmsListItem,
  type TextCredit,
  type ScheduledSms,
} from '../../api/smsApi';
import Loader from '../../components/common/Loader';
import { truncate } from '../../utils/format';

const MOBILE_RE = /^\d{10}$/;

/** Send SMS — sms.html / smsCtrl.js. */
export default function Sms() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [smsType, setSmsType] = useState(4); // 4 Custom, 0 All, 1 Enquiries, 3 Cancel, 2 Clients
  const [projectId, setProjectId] = useState(0);
  const [mobiles, setMobiles] = useState<string[]>([]);
  const [mobileInput, setMobileInput] = useState('');
  const [scheduled, setScheduled] = useState(false);
  const [eventName, setEventName] = useState('');
  const [eventDate, setEventDate] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [selectedDate, setSelectedDate] = useState(dayjs().format('YYYY-MM-DD'));
  const [smsData, setSmsData] = useState<SmsListItem[]>([]);
  const [credit, setCredit] = useState<TextCredit>({ availableCredits: 0 });
  const [showScheduled, setShowScheduled] = useState(false);
  const [scheduledList, setScheduledList] = useState<ScheduledSms[]>([]);

  const messageCost = Math.ceil(message.length / 160) * mobiles.length;

  useEffect(() => {
    if (!session) return;
    getInprogressAndCompletedProjects(session.userType, session.companyId ?? '', session.userId).then((res) => {
      if (res.code === 200) {
        const list = (res.list ?? []).map((p) => ({
          ...p,
          projectStatusName: p.projectStatus === 2 ? '-- Ongoing --' : '-- Completed --',
        }));
        setProjects([{ projectTranId: 0, projectId: 0, projectName: 'All', projectStatus: 0 }, ...list]);
      }
    });
  }, [session]);

  const loadMessages = (dateStr: string) => {
    if (!session) return;
    setLoading(true);
    getSmsListByDate({
      date: dayjs(dateStr).format('DD/MM/YYYY'),
      userId: session.userId,
      companyId: session.companyId ?? '',
    })
      .then((res) => {
        if (res.code === 200) {
          setSmsData(res.list ?? []);
          setCredit(res.object ?? { availableCredits: 0 });
        }
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    loadMessages(selectedDate);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedDate, session]);

  // Auto-fill recipients when a non-custom type/project is chosen.
  useEffect(() => {
    if (!session) return;
    if (smsType === 4) {
      setMobiles([]);
      return;
    }
    getMobileNumbersByType(session.userId, smsType, projectId).then((res) => {
      if (res.code === 200 && res.message) setMobiles(res.message.split(','));
      else setMobiles([]);
    });
  }, [smsType, projectId, session]);

  const addMobile = () => {
    const v = mobileInput.trim();
    if (!v) return;
    if (!MOBILE_RE.test(v)) {
      toast.error('Enter valid mobile number');
      return;
    }
    setMobiles((m) => [...m, v]);
    setMobileInput('');
  };
  const onMobileKey = (e: KeyboardEvent<HTMLInputElement>) => {
    if (['Enter', 'Tab', ' '].includes(e.key)) {
      e.preventDefault();
      addMobile();
    }
  };
  const removeMobile = (i: number) => setMobiles((m) => m.filter((_, idx) => idx !== i));

  const send = async () => {
    if (!session) return;
    setError('');
    if (mobiles.length === 0) {
      toast.error('Add a mobile number');
      return;
    }
    if (!message.trim()) {
      toast.error('Enter message');
      return;
    }
    if (messageCost > credit.availableCredits) {
      setError('Message could not send due to Insufficient Credits');
      return;
    }
    setLoading(true);
    try {
      const res = await sendSms({
        mobileNos: mobiles.join(','),
        smsText: message,
        companyId: session.companyId ?? '',
        isScheduledSMS: scheduled,
        eventName,
        eventDate: eventDate ? dayjs(eventDate).format('DD/MM/YYYY') : '',
        messageCost,
      });
      if (res.code === 200) {
        toast.success(res.message || 'Sent.');
        setCredit(res.object ?? credit);
        setMobiles([]); setMessage(''); setEventDate(''); setEventName(''); setScheduled(false); setSmsType(4); setProjectId(0);
        loadMessages(selectedDate);
      } else {
        toast.error(res.message || 'Unable to send.');
      }
    } finally {
      setLoading(false);
    }
  };

  const openScheduled = async () => {
    if (!session) return;
    const res = await getScheduledSms(session.companyId ?? '');
    if (res.code === 200) {
      setScheduledList(res.list ?? []);
      setShowScheduled(true);
    }
  };

  return (
    <div>
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-envelope me-2" />Send SMS</div>
        <div className="d-flex align-items-center gap-3">
          <div className="d-flex align-items-center gap-2">
            <label className="mb-0">Select Date:</label>
            <input type="date" className="form-control" value={selectedDate} max={dayjs().format('YYYY-MM-DD')} onChange={(e) => setSelectedDate(e.target.value)} />
          </div>
          <button className="btn btn-sm btn-info text-white" onClick={openScheduled}><i className="fa fa-calendar me-1" />Scheduled SMS</button>
        </div>
      </div>

      <div className="row g-3">
        <div className="col-lg-8">
          <div className="rc-card">
            <span className="float-end" style={{ color: '#A84141', fontWeight: 600 }}>
              {credit.availableCredits} Text Credits
            </span>
            <div className="mb-3">
              <label className="form-label">SMS Type:</label>
              <select className="form-select" value={smsType} onChange={(e) => setSmsType(Number(e.target.value))}>
                <option value={4}>Custom</option>
                <option value={0}>All</option>
                <option value={1}>Enquiries</option>
                <option value={3}>Cancel Enquiries</option>
                <option value={2}>Clients</option>
              </select>
            </div>
            {smsType !== 4 && (
              <div className="mb-3">
                <label className="form-label">Select Project:</label>
                <select className="form-select" value={projectId} onChange={(e) => setProjectId(Number(e.target.value))}>
                  {projects.map((p) => (
                    <option key={p.projectTranId} value={p.projectTranId}>{p.projectName}</option>
                  ))}
                </select>
              </div>
            )}
            <div className="mb-3">
              <label className="form-label">Mobile No.:</label>
              <div className="d-flex flex-wrap gap-2 border rounded p-2">
                {mobiles.map((m, i) => (
                  <span key={i} className="badge bg-secondary" role="button" onClick={() => removeMobile(i)}>{m} ✕</span>
                ))}
                <input
                  className="border-0 flex-fill"
                  style={{ outline: 'none', minWidth: 140 }}
                  placeholder="Add a mobile number"
                  value={mobileInput}
                  onChange={(e) => setMobileInput(e.target.value)}
                  onKeyDown={onMobileKey}
                  onBlur={addMobile}
                />
              </div>
            </div>
            <div className="form-check mb-3">
              <input className="form-check-input" type="checkbox" id="sched" checked={scheduled} onChange={(e) => setScheduled(e.target.checked)} />
              <label className="form-check-label blue" htmlFor="sched">Scheduled SMS ?</label>
            </div>
            {scheduled && (
              <>
                <div className="mb-3">
                  <label className="form-label">Event Name:</label>
                  <input className="form-control" value={eventName} onChange={(e) => setEventName(e.target.value)} />
                </div>
                <div className="mb-3">
                  <label className="form-label">Event Date:</label>
                  <input type="date" className="form-control" min={dayjs().add(1, 'day').format('YYYY-MM-DD')} value={eventDate} onChange={(e) => setEventDate(e.target.value)} />
                </div>
              </>
            )}
            <div className="mb-3">
              <label className="form-label">Message:</label>
              <textarea className="form-control" rows={5} maxLength={500} value={message} onChange={(e) => setMessage(e.target.value)} />
              <small className="text-muted">Cost: {messageCost} credit(s)</small>
            </div>
            {error && <div className="text-danger mb-2">{error}</div>}
            <div className="text-center">
              <button className="btn btn-primary" onClick={send}>Send <i className="fa fa-paper-plane ms-1" /></button>
            </div>
          </div>
        </div>

        <div className="col-lg-4">
          <div className="rc-card">
            <div className="rc-panel-title rc-panel-info">SMS Lists</div>
            <div style={{ maxHeight: 390, overflowY: 'auto' }}>
              {smsData.length === 0 ? (
                <span className="text-muted">There is no data available</span>
              ) : (
                smsData.map((d, i) => (
                  <div key={i} className="border-bottom py-2">
                    <b>{d.mobileNos}</b>
                    <span className="float-end text-muted">{d.smsSendTime}</span>
                    <div>{truncate(d.smsText, 105)}</div>
                  </div>
                ))
              )}
            </div>
          </div>
        </div>
      </div>

      <Modal show={showScheduled} onHide={() => setShowScheduled(false)} size="lg" centered>
        <Modal.Header closeButton><Modal.Title>Scheduled SMS Details</Modal.Title></Modal.Header>
        <Modal.Body>
          <table className="table table-striped table-bordered">
            <thead>
              <tr><th>#</th><th>Event Name</th><th>Event Date</th><th>Message</th><th>Mobile Nos.</th></tr>
            </thead>
            <tbody>
              {scheduledList.length === 0 ? (
                <tr><td colSpan={5} className="text-center">No data available</td></tr>
              ) : (
                scheduledList.map((s, i) => (
                  <tr key={i}>
                    <td>{i + 1}</td><td>{s.eventName}</td><td>{s.eventDate}</td><td>{s.smsText}</td>
                    <td>{s.mobileNos.split(',').map((m, j) => <span key={j} className="badge bg-light text-dark me-1">{m}</span>)}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </Modal.Body>
      </Modal>
    </div>
  );
}

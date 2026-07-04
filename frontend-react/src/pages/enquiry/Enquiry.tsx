import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getInprogressAndCompletedProjects,
  getEnquiryListByProject,
  getEnquiryDetail,
  deleteEnquiry,
  closeEnquiry,
  addRemark,
  type ProjectFull,
  type EnquiryListRow,
  type EnquiryDetail,
} from '../../api/enquiryApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import EnquiryDetailsModal from '../../components/common/EnquiryDetailsModal';
import Loader from '../../components/common/Loader';

/** Enquiry Book list — enquiry.html / enquiryCtrl.js. */
export default function Enquiry() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [projectId, setProjectId] = useState<number | ''>('');
  const [rows, setRows] = useState<EnquiryListRow[]>([]);
  const [detail, setDetail] = useState<EnquiryDetail | null>(null);

  // modals
  const [closeTarget, setCloseTarget] = useState<EnquiryListRow | null>(null);
  const [closeReason, setCloseReason] = useState('');
  const [remarkTarget, setRemarkTarget] = useState<EnquiryListRow | null>(null);
  const [remarkText, setRemarkText] = useState('');

  useEffect(() => {
    if (!session) return;
    setLoading(true);
    getInprogressAndCompletedProjects(session.userType, session.companyId ?? '', session.userId)
      .then((res) => {
        if (res.code === 200 && res.list?.length) {
          const list = res.list.map((p) => ({
            ...p,
            projectStatusName: p.projectStatus === 2 ? '-- Ongoing --' : '-- Completed --',
          }));
          setProjects(list);
          setProjectId(list[0].projectTranId);
        } else {
          toast.warn('Please add project details first.');
        }
      })
      .catch(() => toast.error('Unable to load projects.'))
      .finally(() => setLoading(false));
  }, [session]);

  const loadEnquiries = (pid: number) => {
    setLoading(true);
    getEnquiryListByProject(pid)
      .then((res) => setRows(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load enquiries.'))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    if (projectId !== '') loadEnquiries(projectId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [projectId]);

  const openDetail = async (enquiryId: number) => {
    setLoading(true);
    try {
      const res = await getEnquiryDetail(enquiryId);
      if (res.code === 200 && res.object) setDetail(res.object);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (r: EnquiryListRow) => {
    if (projectId === '' || !session) return;
    if (!window.confirm('Are you sure?')) return;
    setLoading(true);
    try {
      const res = await deleteEnquiry(r.enquiryId, projectId, session.userId);
      toast[res.code === 200 ? 'success' : 'error'](res.message || '');
      loadEnquiries(projectId);
    } finally {
      setLoading(false);
    }
  };

  const submitClose = async () => {
    if (!closeTarget || projectId === '' || !session) return;
    if (!closeReason.trim()) return;
    setLoading(true);
    try {
      await closeEnquiry(projectId, {
        enquiryId: closeTarget.enquiryId,
        reason: closeReason,
        userId: session.userId,
        userType: session.userType,
      });
      setCloseTarget(null);
      setCloseReason('');
      loadEnquiries(projectId);
    } finally {
      setLoading(false);
    }
  };

  const submitRemark = async () => {
    if (!remarkTarget || projectId === '' || !session) return;
    if (!remarkText.trim()) return;
    if (!window.confirm('Are you sure?')) return;
    setLoading(true);
    try {
      const res = await addRemark({ enquiryId: remarkTarget.enquiryId, remark: remarkText, userId: session.userId, projectId });
      toast[res.code === 200 ? 'success' : 'error'](res.message || '');
      setRemarkTarget(null);
      setRemarkText('');
      loadEnquiries(projectId);
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<EnquiryListRow>[] = [
    {
      key: 'firstName', header: 'Name',
      render: (r) => <a role="button" className="blue" onClick={() => openDetail(r.enquiryId)}>{r.firstName} {r.lastName}</a>,
    },
    { key: 'mobileNo', header: 'Mobile' },
    { key: 'email', header: 'Email' },
    { key: 'propertyName', header: 'Property' },
    { key: 'budgetMax', header: 'Budget', render: (r) => `${r.budgetMax} Lakhs` },
    {
      key: 'enquiryClosed', header: 'Enquiry Closed', sortable: false,
      render: (r) => <input type="checkbox" title="Close enquiry" onChange={() => setCloseTarget(r)} />,
    },
    {
      key: 'action', header: 'Action', sortable: false,
      render: (r) => (
        <span className="d-inline-flex gap-2">
          <a role="button" className="green" title="Edit" onClick={() => navigate(`/dashboard/addEnquiry?id=${r.enquiryId}`)}><i className="fa fa-pencil" /></a>
          <a role="button" className="red" title="Delete" onClick={() => handleDelete(r)}><i className="fa fa-trash" /></a>
          {r.remark === '' && (
            <a role="button" className="blue" title="Add Remark" onClick={() => setRemarkTarget(r)}><i className="fa fa-plus-circle" /></a>
          )}
        </span>
      ),
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-book me-2" />View all enquiry details</div>
        <div className="d-flex gap-2">
          <button className="btn btn-sm btn-danger" onClick={() => navigate('/dashboard/closedEnquiry')}>
            <i className="fa fa-xmark me-1" />Cancel Enquiry
          </button>
          <button className="btn btn-sm btn-success" onClick={() => navigate('/dashboard/addEnquiry')}>
            <i className="fa fa-plus me-1" />Add Enquiry
          </button>
        </div>
      </div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-4">
          <select className="form-select" value={projectId} onChange={(e) => setProjectId(Number(e.target.value))}>
            {projects.map((p) => (
              <option key={p.projectTranId} value={p.projectTranId}>{p.projectStatusName} {p.projectName}</option>
            ))}
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={rows} searchKeys={['firstName', 'lastName', 'mobileNo', 'email', 'propertyName']} />

      <EnquiryDetailsModal detail={detail} onClose={() => setDetail(null)} />

      {/* Close enquiry modal */}
      <Modal show={!!closeTarget} onHide={() => setCloseTarget(null)} centered>
        <Modal.Header closeButton><Modal.Title>Close Enquiry — {closeTarget?.firstName} {closeTarget?.lastName}</Modal.Title></Modal.Header>
        <Modal.Body>
          <label className="form-label">Reason</label>
          <textarea className="form-control" rows={3} value={closeReason} onChange={(e) => setCloseReason(e.target.value)} required />
        </Modal.Body>
        <Modal.Footer>
          <button className="btn btn-sm btn-primary" onClick={submitClose}><i className="fa fa-check me-1" />Submit</button>
          <button className="btn btn-sm btn-secondary" onClick={() => setCloseTarget(null)}>Cancel</button>
        </Modal.Footer>
      </Modal>

      {/* Add remark modal */}
      <Modal show={!!remarkTarget} onHide={() => setRemarkTarget(null)} centered>
        <Modal.Header closeButton><Modal.Title>Add Remark</Modal.Title></Modal.Header>
        <Modal.Body>
          <label className="form-label">Remark</label>
          <textarea className="form-control" rows={3} value={remarkText} onChange={(e) => setRemarkText(e.target.value)} required />
        </Modal.Body>
        <Modal.Footer>
          <button className="btn btn-sm btn-primary" onClick={submitRemark}><i className="fa fa-check me-1" />Add</button>
          <button className="btn btn-sm btn-secondary" onClick={() => setRemarkTarget(null)}>Cancel</button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

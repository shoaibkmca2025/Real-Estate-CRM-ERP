import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getAllCompanyDetails, type CompanyOption } from '../../api/smsApi';
import { getAllProjectDetailsFull, type ProjectFull } from '../../api/enquiryApi';
import { updateProjectApprovedStatus } from '../../api/adminApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

const STATUS_LABEL: Record<number, string> = { 1: 'Upcoming', 2: 'In Progress', 3: 'Completed' };

/** Super-admin project list with approve/unapprove — userPaymentDetails.html / paymentDetailsCtrl.js. */
export default function UserPaymentDetails() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [companies, setCompanies] = useState<CompanyOption[]>([]);
  const [companyId, setCompanyId] = useState<number | ''>('');
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [approvedFilter, setApprovedFilter] = useState('');

  useEffect(() => {
    setLoading(true);
    getAllCompanyDetails()
      .then((res) => {
        const list = res.list ?? [];
        setCompanies(list);
        if (list.length) setCompanyId(list[0].companyId);
      })
      .catch(() => toast.error('Unable to load companies.'))
      .finally(() => setLoading(false));
  }, []);

  const loadProjects = (cid: number) => {
    if (!session) return;
    setLoading(true);
    getAllProjectDetailsFull(session.userType, session.userId, cid, 0)
      .then((res) => setProjects(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load projects.'))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    if (companyId !== '') loadProjects(companyId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [companyId]);

  const filtered = useMemo(
    () => (approvedFilter === '' ? projects : projects.filter((p) => String(p.isApproved) === approvedFilter)),
    [projects, approvedFilter]
  );

  const toggleApproval = async (p: ProjectFull) => {
    const msg = p.isApproved === 1 ? 'Do you want to Unapproved This Project?' : 'Do you want to Approved This Project?';
    if (!window.confirm(msg)) return;
    setLoading(true);
    try {
      const res = await updateProjectApprovedStatus(p.projectTranId, p.isApproved === 1 ? 0 : 1);
      if (res.statusCode === 200 || res.code === 200) {
        toast.success(res.message || 'Updated.');
        if (companyId !== '') loadProjects(companyId);
      } else {
        toast.error(res.message || 'Unable to update.');
      }
    } catch {
      toast.error('Unable to update approval.');
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<ProjectFull>[] = [
    {
      key: 'projectName',
      header: 'Project Name',
      render: (p) => (
        <a
          role="button"
          className="blue"
          onClick={() => {
            sessionStorage.setItem('rc_payCtx', JSON.stringify({ projectId: p.projectTranId, projectName: p.projectName, companyId }));
            navigate('/admindashboard/projectwisePaymentDetails');
          }}
        >
          {p.projectName}
        </a>
      ),
    },
    { key: 'projectStatus', header: 'Project Status', render: (p) => STATUS_LABEL[p.projectStatus] ?? '' },
    {
      key: 'isApproved',
      header: 'Project Approval',
      render: (p) =>
        p.isApproved === 1 ? (
          <span className="label label-success">Approved</span>
        ) : (
          <span className="label label-danger">Unapproved</span>
        ),
    },
    {
      key: 'action',
      header: 'Action',
      sortable: false,
      render: (p) => (
        <input
          type="checkbox"
          title="Update Status"
          checked={p.isApproved === 1}
          onChange={() => toggleApproval(p)}
        />
      ),
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-book me-2" />View Projects List</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Company:</label>
        <div className="col-sm-3">
          <select className="form-select" value={companyId} onChange={(e) => setCompanyId(Number(e.target.value))}>
            {companies.map((c) => (
              <option key={c.companyId} value={c.companyId}>{c.companyName}</option>
            ))}
          </select>
        </div>
        <div className="col-sm-3">
          <select className="form-select" value={approvedFilter} onChange={(e) => setApprovedFilter(e.target.value)}>
            <option value="">All</option>
            <option value="1">Approved</option>
            <option value="0">Unapproved</option>
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={filtered} searchKeys={['projectName']} />
    </div>
  );
}

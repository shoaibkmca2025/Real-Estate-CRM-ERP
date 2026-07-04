import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getProjectStatusList,
  getProjectsByStatus,
  updateProjectStatus,
  deleteProject,
  type ProjectListRow,
} from '../../api/projectApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

/** View all projects — viewProjectList.html / viewProjectListCtrl.js. */
export default function ViewProjectList() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [statusList, setStatusList] = useState<Record<string, string>>({});
  const [status, setStatus] = useState('2'); // default Ongoing
  const [rows, setRows] = useState<ProjectListRow[]>([]);

  const statusName = statusList[status] ?? (status === '1' ? 'Upcoming' : status === '2' ? 'Ongoing' : 'Completed');
  const changeStatus = statusName === 'Ongoing' ? 'Completed' : statusName === 'Upcoming' ? 'Ongoing' : 'Ongoing';

  const loadProjects = (st: string) => {
    if (!session) return;
    setLoading(true);
    getProjectsByStatus(st, session.userType, session.userId, session.companyId ?? '')
      .then((res) => setRows(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load projects.'))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    if (!session) return;
    getProjectStatusList().then((res) => setStatusList(res.statusList ?? {}));
    loadProjects(status);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [session]);

  const changeTab = (st: string) => {
    setStatus(st);
    loadProjects(st);
  };

  const toggleStatus = async (p: ProjectListRow) => {
    if (!session) return;
    if (!window.confirm('Do you want to Update Status?')) return;
    setLoading(true);
    try {
      const next = p.projectStatus === 1 ? 2 : 3;
      const res = await updateProjectStatus({ projectTranId: p.projectTranId, projectStatus: next, userId: session.userId });
      toast[(res.statusCode === 200 || res.code === 200) ? 'success' : 'error'](res.message || '');
      loadProjects(status);
    } finally {
      setLoading(false);
    }
  };

  const removeProject = async (p: ProjectListRow) => {
    if (!session) return;
    if (!window.confirm('Are you Sure?')) return;
    setLoading(true);
    try {
      const res = await deleteProject(p.projectTranId, session.userId);
      toast[(res.statusCode === 200 || res.code === 200) ? 'success' : 'error'](res.message || '');
      loadProjects(status);
    } finally {
      setLoading(false);
    }
  };

  const openDetails = (projectId: number) => {
    sessionStorage.setItem('rc_viewProjectId', String(projectId));
    navigate('/dashboard/viewProjectDetails');
  };

  const columns: Column<ProjectListRow>[] = [
    { key: 'projectName', header: 'Project Name', render: (p) => <a role="button" className="blue" onClick={() => openDetails(p.projectTranId)}>{p.projectName}</a> },
    { key: 'startDate', header: 'Project Start Date' },
    { key: 'expectedCompletionDate', header: 'Expected Completion Date' },
    ...(statusName !== 'Completed'
      ? [{
          key: 'transfer', header: `Transfer to ${changeStatus}`, sortable: false,
          render: (p: ProjectListRow) => p.isApproved === 1
            ? <input type="checkbox" title="Update status" onChange={() => toggleStatus(p)} />
            : <span className="text-muted">—</span>,
        } as Column<ProjectListRow>]
      : []),
    {
      key: 'action', header: 'Action', sortable: false,
      render: (p) => (
        <span className="d-inline-flex gap-2">
          <a role="button" className="blue" title="View" onClick={() => openDetails(p.projectTranId)}><i className="fa fa-magnifying-glass-plus" /></a>
          {statusName !== 'Completed' && (
            <a role="button" className="red" title="Delete" onClick={() => removeProject(p)}><i className="fa fa-trash" /></a>
          )}
        </span>
      ),
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-rss me-2" />Project Details List</div>
        <ul className="nav nav-tabs">
          {Object.entries(statusList).map(([key, value]) => (
            <li className="nav-item" key={key}>
              <button className={`nav-link ${key === status ? 'active' : ''}`} onClick={() => changeTab(key)}>{value}</button>
            </li>
          ))}
        </ul>
      </div>
      <h5 className="green mb-3"><i className="fa fa-list me-2" />{statusName}</h5>
      <DataTable columns={columns} rows={rows} searchKeys={['projectName']} />
    </div>
  );
}

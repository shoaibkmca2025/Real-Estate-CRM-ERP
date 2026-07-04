import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getInprogressAndCompletedProjects,
  getFollowupsByProject,
  getEnquiryDetail,
  type ProjectFull,
  type FollowupRow,
  type EnquiryDetail,
} from '../../api/enquiryApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import EnquiryDetailsModal from '../../components/common/EnquiryDetailsModal';
import Loader from '../../components/common/Loader';

/** Follow Up — followup.html / followupCtrl.js. */
export default function Followup() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [projectId, setProjectId] = useState<number | ''>('');
  const [rows, setRows] = useState<FollowupRow[]>([]);
  const [detail, setDetail] = useState<EnquiryDetail | null>(null);

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

  useEffect(() => {
    if (projectId === '') return;
    setLoading(true);
    getFollowupsByProject(projectId)
      .then((res) => setRows(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load followups.'))
      .finally(() => setLoading(false));
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

  const strike = (r: FollowupRow): React.CSSProperties =>
    r.followupFlag === 1 ? { textDecoration: 'line-through' } : {};

  const columns: Column<FollowupRow>[] = [
    {
      key: 'firstName',
      header: 'Name',
      render: (r) => (
        <a role="button" className="blue" style={strike(r)} onClick={() => openDetail(r.enquiryId)}>
          {r.firstName} {r.lastName}
        </a>
      ),
    },
    { key: 'mobileNo', header: 'Mobile', render: (r) => <span style={strike(r)}>{r.mobileNo}</span> },
    { key: 'followupDescr', header: 'Followup Description', render: (r) => <span style={strike(r)}>{r.followupDescr}</span> },
    { key: 'followupDate', header: 'Followup Date', render: (r) => <span style={strike(r)}>{r.followupDate}</span> },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-comments me-2" />Follow Up</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-4">
          <select className="form-select" value={projectId} onChange={(e) => setProjectId(Number(e.target.value))}>
            {projects.map((p) => (
              <option key={p.projectTranId} value={p.projectTranId}>
                {p.projectStatusName} {p.projectName}
              </option>
            ))}
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={rows} searchKeys={['firstName', 'lastName', 'mobileNo', 'followupDescr']} />
      <EnquiryDetailsModal detail={detail} onClose={() => setDetail(null)} />
    </div>
  );
}

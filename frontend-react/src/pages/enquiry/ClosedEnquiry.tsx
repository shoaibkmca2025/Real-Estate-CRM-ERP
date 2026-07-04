import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getAllProjectDetailsFull,
  getClosedEnquiriesByProject,
  getEnquiryDetail,
  downloadClosedEnquiryExcel,
  type ProjectFull,
  type ClosedEnquiryRow,
  type EnquiryDetail,
} from '../../api/enquiryApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import EnquiryDetailsModal from '../../components/common/EnquiryDetailsModal';
import Loader from '../../components/common/Loader';

/** Closed / Cancelled Enquiry — closedEnquiry.html / closedEnquiryCtrl.js. */
export default function ClosedEnquiry() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [projectId, setProjectId] = useState<number>(0); // 0 = ALL
  const [rows, setRows] = useState<ClosedEnquiryRow[]>([]);
  const [detail, setDetail] = useState<EnquiryDetail | null>(null);

  useEffect(() => {
    if (!session) return;
    setLoading(true);
    getAllProjectDetailsFull(session.userType, session.userId, session.companyId ?? '', 0)
      .then((res) => {
        if (res.code === 200) {
          // keep approved, non-pending projects (projectStatus != 1 && isApproved == 1)
          setProjects((res.list ?? []).filter((p) => p.projectStatus !== 1 && p.isApproved === 1));
        }
        loadClosed(0);
      })
      .catch(() => toast.error('Unable to load projects.'))
      .finally(() => setLoading(false));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [session]);

  const loadClosed = (pid: number) => {
    if (!session) return;
    setProjectId(pid);
    setLoading(true);
    getClosedEnquiriesByProject(pid, session.companyId ?? '')
      .then((res) => setRows(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load closed enquiries.'))
      .finally(() => setLoading(false));
  };

  const openDetail = async (enquiryId: number) => {
    setLoading(true);
    try {
      const res = await getEnquiryDetail(enquiryId);
      if (res.code === 200 && res.object) setDetail(res.object);
    } finally {
      setLoading(false);
    }
  };

  const exportExcel = async () => {
    if (!session) return;
    if (!window.confirm('Will you want to generate Excel Report?')) return;
    setLoading(true);
    try {
      const blob = await downloadClosedEnquiryExcel(projectId, session.companyId ?? '');
      const url = URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'ClosedEnquiryExcelReport.xls';
      document.body.appendChild(a);
      a.click();
      a.remove();
      URL.revokeObjectURL(url);
    } catch {
      toast.error('Unable to generate report.');
    } finally {
      setLoading(false);
    }
  };

  const ongoing = projects.filter((p) => p.projectStatus === 2);
  const completed = projects.filter((p) => p.projectStatus === 3);

  const columns: Column<ClosedEnquiryRow>[] = [
    {
      key: 'fullName',
      header: 'Name',
      render: (r) => <a role="button" className="blue" onClick={() => openDetail(r.enquiryId)}>{r.fullName}</a>,
    },
    { key: 'mobileNo', header: 'Mobile Number' },
    { key: 'email', header: 'Email' },
    { key: 'reason', header: 'Reason' },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-times-circle me-2" />View All Cancel Enquiry</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-4">
          <select className="form-select" value={projectId} onChange={(e) => loadClosed(Number(e.target.value))}>
            <option value={0}>ALL</option>
            {ongoing.length > 0 && (
              <optgroup label="-- Ongoing --">
                {ongoing.map((p) => <option key={p.projectTranId} value={p.projectTranId}>{p.projectName}</option>)}
              </optgroup>
            )}
            {completed.length > 0 && (
              <optgroup label="-- Completed --">
                {completed.map((p) => <option key={p.projectTranId} value={p.projectTranId}>{p.projectName}</option>)}
              </optgroup>
            )}
          </select>
        </div>
        <div className="col-sm-2">
          {rows.length > 0 && (
            <button className="btn btn-primary" title="Generate Excel Report" onClick={exportExcel}>
              <i className="fa fa-file-excel" />
            </button>
          )}
        </div>
      </div>

      <DataTable columns={columns} rows={rows} searchKeys={['fullName', 'mobileNo', 'email', 'reason']} />
      <EnquiryDetailsModal detail={detail} onClose={() => setDetail(null)} />
    </div>
  );
}

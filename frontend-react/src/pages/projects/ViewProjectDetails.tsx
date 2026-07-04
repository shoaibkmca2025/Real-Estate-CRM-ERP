import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { getProjectDetails, type ProjectDetails } from '../../api/projectApi';
import Loader from '../../components/common/Loader';

const STATUS: Record<number, string> = { 1: 'Upcoming', 2: 'Ongoing', 3: 'Completed' };

/** View Project Details — viewProjectDetails.html / viewProjectDetailsCtrl.js. */
export default function ViewProjectDetails() {
  const navigate = useNavigate();
  const projectId = sessionStorage.getItem('rc_viewProjectId');
  const [loading, setLoading] = useState(false);
  const [project, setProject] = useState<ProjectDetails | null>(null);

  useEffect(() => {
    if (!projectId) {
      navigate('/dashboard/viewProjects');
      return;
    }
    setLoading(true);
    getProjectDetails(projectId)
      .then((res) => {
        if (res.code === 200 && res.object) setProject(res.object);
        else toast.error('Unable to load project details.');
      })
      .catch(() => toast.error('Unable to load project details.'))
      .finally(() => setLoading(false));
  }, [projectId, navigate]);

  if (!project) return <Loader show={loading} />;

  const approvedBanks = project.bankDetailsList?.filter((b) => b.bankType === 1) ?? [];
  const disbursementBanks = project.bankDetailsList?.filter((b) => b.bankType !== 1) ?? [];

  return (
    <div>
      <Loader show={loading} />

      <div className="rc-card d-flex justify-content-between align-items-center" style={{ background: '#2563eb', color: '#fff' }}>
        <span><i className="fa fa-building me-2" />{project.projectName}</span>
        <button className="btn btn-sm btn-light" onClick={() => navigate('/dashboard/viewProjects')}><i className="fa fa-reply-all me-1" />Back</button>
      </div>

      {/* Project info */}
      <div className="rc-card">
        <div className="rc-card-title">Project Information</div>
        <div className="row">
          <div className="col-sm-6">
            <Info label="Project Name" value={project.projectName} />
            <Info label="Address" value={project.address} />
            <Info label="RERA No." value={project.mahareraNo} />
          </div>
          <div className="col-sm-6">
            <Info label="Status" value={STATUS[project.projectStatus] ?? '-'} />
            <Info label="Start Date" value={project.startDate ?? '-'} />
            <Info label="Expected Completion" value={project.expectedCompletionDate ?? '-'} />
          </div>
        </div>
      </div>

      {/* Wings */}
      {project.wingList && project.wingList.length > 0 && (
        <div className="rc-card">
          <div className="rc-card-title">Wings</div>
          <div className="table-responsive">
            <table className="table table-bordered table-striped table-condensed">
              <thead><tr><th>Wing</th><th className="text-center">Floors</th><th className="text-center">Flats</th></tr></thead>
              <tbody>
                {project.wingList.map((w) => (
                  <tr key={w.wingId}><td>{w.wingName}</td><td className="text-center">{w.totalFloors ?? '-'}</td><td className="text-center">{w.totalFlats ?? '-'}</td></tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}

      {/* Disbursement schedule */}
      <div className="rc-card">
        <div className="rc-card-title">Disbursement Schedule</div>
        <div className="table-responsive">
          <table className="table table-bordered table-striped table-condensed">
            <thead><tr><th>Title</th><th className="text-end">%</th><th>Completion Date</th></tr></thead>
            <tbody>
              {project.disbursementList?.length ? project.disbursementList.map((d) => (
                <tr key={d.projectDisbursementId}>
                  <td>{d.disbursementTitle}</td>
                  <td className="text-end">{d.percentageValue}%</td>
                  <td>{d.completionDate || <span className="label label-danger">Pending</span>}</td>
                </tr>
              )) : <tr><td colSpan={3} className="text-center">No data available</td></tr>}
            </tbody>
          </table>
        </div>
      </div>

      {/* Banks */}
      <div className="row g-3">
        <div className="col-md-6">
          <div className="rc-card h-100">
            <div className="rc-card-title">Approved Banks</div>
            <BankList banks={approvedBanks} />
          </div>
        </div>
        <div className="col-md-6">
          <div className="rc-card h-100">
            <div className="rc-card-title">Disbursement Banks</div>
            <BankList banks={disbursementBanks} />
          </div>
        </div>
      </div>

      {/* Amenities */}
      {project.amenitiesList && project.amenitiesList.length > 0 && (
        <div className="rc-card">
          <div className="rc-card-title">Amenities</div>
          <div className="d-flex flex-wrap gap-2">
            {project.amenitiesList.map((a, i) => <span key={i} className="badge bg-info">{a.amenitiesName}</span>)}
          </div>
        </div>
      )}
    </div>
  );
}

const Info = ({ label, value }: { label: string; value: string }) => (
  <div className="d-flex justify-content-between border-bottom py-2"><span className="text-muted">{label}</span><span className="fw-semibold">{value}</span></div>
);

const BankList = ({ banks }: { banks: { bankName: string; branch?: string; accountNumber?: string; ifscCode?: string }[] }) =>
  banks.length === 0 ? (
    <p className="text-muted mb-0">No banks added.</p>
  ) : (
    <ul className="list-unstyled mb-0">
      {banks.map((b, i) => (
        <li key={i} className="border-bottom py-2">
          <b>{b.bankName}</b>{b.branch && ` — ${b.branch}`}
          {b.accountNumber && <div className="text-muted small">A/C: {b.accountNumber} {b.ifscCode && `· IFSC: ${b.ifscCode}`}</div>}
        </li>
      ))}
    </ul>
  );

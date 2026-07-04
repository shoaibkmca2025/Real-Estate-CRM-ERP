import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getInprogressAndCompletedProjects, type ProjectFull } from '../../api/enquiryApi';
import { getProjectStructure, type FlatCell } from '../../api/bookingApi';
import Loader from '../../components/common/Loader';

const STATUS_COLOR: Record<number, string> = {
  0: '#10b981', // available
  1: '#f59e0b', // register
  2: '#ef4444', // booked
  3: '#94a3b8', // cancelled
};
const STATUS_LABEL: Record<number, string> = { 0: 'Available', 1: 'Register', 2: 'Booked', 3: 'Cancelled' };
const floorLabel = (n: number) => (n === -1 ? 'Basement' : n === 0 ? 'Ground' : String(n));

/** Property booking grid — bookingDetails.html / propertyBookingCtrl.js. */
export default function Bookings() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [projectId, setProjectId] = useState<number | ''>('');
  const [cells, setCells] = useState<FlatCell[]>([]);

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
    getProjectStructure(projectId)
      .then((res) => setCells(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load project structure.'))
      .finally(() => setLoading(false));
  }, [projectId]);

  // group cells: wing -> floor -> flats
  const wings = useMemo(() => {
    const byWing = new Map<number, { wingName: string; floors: Map<number, FlatCell[]> }>();
    for (const c of cells) {
      if (!byWing.has(c.wingTranId)) byWing.set(c.wingTranId, { wingName: c.wingName, floors: new Map() });
      const wing = byWing.get(c.wingTranId)!;
      if (!wing.floors.has(c.floorNumber)) wing.floors.set(c.floorNumber, []);
      wing.floors.get(c.floorNumber)!.push(c);
    }
    return byWing;
  }, [cells]);

  const openFlat = (c: FlatCell) => {
    sessionStorage.setItem('rc_flat', JSON.stringify({ projectId, wingId: c.wingTranId, floorNumber: c.floorNumber, flatNumber: c.flatNumber }));
    navigate('/dashboard/showBookedPropertyDetails');
  };

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-building me-2" />Booking Details</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-4">
          <select className="form-select" value={projectId} onChange={(e) => setProjectId(Number(e.target.value))}>
            {projects.map((p) => (
              <option key={p.projectTranId} value={p.projectTranId}>{p.projectStatusName} {p.projectName}</option>
            ))}
          </select>
        </div>
        <div className="col-sm-6 d-flex justify-content-end gap-3">
          {Object.entries(STATUS_LABEL).map(([k, label]) => (
            <span key={k} className="d-inline-flex align-items-center gap-1">
              <span style={{ width: 14, height: 14, background: STATUS_COLOR[Number(k)], display: 'inline-block', borderRadius: 3 }} />
              <small>{label}</small>
            </span>
          ))}
        </div>
      </div>

      {wings.size === 0 ? (
        <p className="text-muted">No structure available for this project.</p>
      ) : (
        Array.from(wings.entries()).map(([wingId, wing]) => (
          <div key={wingId} className="mb-4">
            <h6 className="blue">{wing.wingName}</h6>
            {Array.from(wing.floors.entries())
              .sort((a, b) => b[0] - a[0])
              .map(([floor, flats]) => (
                <div key={floor} className="d-flex align-items-center mb-2 gap-2 flex-wrap">
                  <span className="text-muted" style={{ width: 80 }}>{floorLabel(floor)}</span>
                  {flats.map((c, i) => (
                    <button
                      key={i}
                      className="btn btn-sm text-white"
                      style={{ background: STATUS_COLOR[c.bookingStatus], minWidth: 56 }}
                      title={`${c.propertyName ?? ''} ${STATUS_LABEL[c.bookingStatus]}`}
                      onClick={() => openFlat(c)}
                    >
                      {c.flatNumber}
                    </button>
                  ))}
                </div>
              ))}
          </div>
        ))
      )}
    </div>
  );
}

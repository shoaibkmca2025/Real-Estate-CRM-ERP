import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getInprogressAndCompletedProjects, type ProjectFull } from '../../api/enquiryApi';
import { getClientsByProject, type ClientRow } from '../../api/clientApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

const BOOKING_STATUS = [
  { value: '', displayName: 'All' },
  { value: '1', displayName: 'Register' },
  { value: '2', displayName: 'Booked' },
  { value: '3', displayName: 'Cancel Bookings' },
];

const floorLabel = (n: number) => (n === -1 ? 'Basement' : n === 0 ? 'Ground Floor' : String(n));

/** Clients list — viewClientsList.html / clientCtrl.js. */
export default function ViewClients() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [projectId, setProjectId] = useState<number | ''>('');
  const [allClients, setAllClients] = useState<ClientRow[]>([]);
  const [bookingStatus, setBookingStatus] = useState('');

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
          setProjectId(list[0].projectId);
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
    getClientsByProject(projectId)
      .then((res) => setAllClients(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load clients.'))
      .finally(() => setLoading(false));
  }, [projectId]);

  const clients = useMemo(
    () => (bookingStatus === '' ? allClients : allClients.filter((c) => String(c.bookingStatus) === bookingStatus)),
    [allClients, bookingStatus]
  );

  const openClient = (clientId: number) => {
    sessionStorage.setItem('rc_clientId', String(clientId));
    navigate('/dashboard/showClientDetails');
  };

  const cancelStyle = (c: ClientRow): React.CSSProperties =>
    c.bookingStatus === 3 && bookingStatus === ''
      ? { textDecoration: 'line-through', color: '#c54040' }
      : {};

  const columns: Column<ClientRow>[] = [
    {
      key: 'ownerName',
      header: 'Client Name',
      render: (c) => (
        <a role="button" className="blue" style={cancelStyle(c)} onClick={() => openClient(c.clientTranId)}>{c.ownerName}</a>
      ),
    },
    { key: 'wingName', header: 'Wing Name', render: (c) => <span style={cancelStyle(c)}>{c.wingName}</span> },
    { key: 'propertyTypeDescr', header: 'Property Type', render: (c) => <span style={cancelStyle(c)}>{c.propertyTypeDescr}</span> },
    { key: 'propertyName', header: 'Property', render: (c) => <span style={cancelStyle(c)}>{c.propertyName}</span> },
    { key: 'propertyArea', header: 'Property Area', render: (c) => <span style={cancelStyle(c)}>{c.propertyArea}</span> },
    { key: 'floorNumber', header: 'Floor Number', render: (c) => <span style={cancelStyle(c)}>{floorLabel(c.floorNumber)}</span> },
    { key: 'flatNumber', header: 'Flat Number', render: (c) => <span style={cancelStyle(c)}>{c.flatNumber}</span> },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-users me-2" />View all client details</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-3">
          <select className="form-select" value={projectId} onChange={(e) => setProjectId(Number(e.target.value))}>
            {projects.map((p) => (
              <option key={p.projectId} value={p.projectId}>{p.projectStatusName} {p.projectName}</option>
            ))}
          </select>
        </div>
        <div className="col-sm-3">
          <select className="form-select" value={bookingStatus} onChange={(e) => setBookingStatus(e.target.value)}>
            {BOOKING_STATUS.map((b) => (
              <option key={b.value} value={b.value}>{b.displayName}</option>
            ))}
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={clients} searchKeys={['ownerName', 'wingName', 'propertyName']} />
    </div>
  );
}

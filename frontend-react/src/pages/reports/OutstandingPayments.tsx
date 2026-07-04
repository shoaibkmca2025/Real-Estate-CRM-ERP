import { useEffect, useState } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { getAllProjectDetails, type ProjectOption } from '../../api/projectApi';
import {
  getOutstandingByProject,
  getClientPaymentSummary,
  type OutstandingPaymentRow,
  type ClientPaymentSummary,
} from '../../api/reportsApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';
import { formatINR } from '../../utils/format';

/** Outstanding Payment report — outstandingPayments.html / reportCtrl.js. */
export default function OutstandingPayments() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [projects, setProjects] = useState<ProjectOption[]>([]);
  const [projectId, setProjectId] = useState<number | ''>('');
  const [rows, setRows] = useState<OutstandingPaymentRow[]>([]);
  const [detail, setDetail] = useState<ClientPaymentSummary | null>(null);

  useEffect(() => {
    if (!session) return;
    setLoading(true);
    getAllProjectDetails(session.userType, session.userId, session.companyId ?? '', 2)
      .then((res) => {
        if (res.code === 200 && res.list?.length) {
          setProjects(res.list);
          setProjectId(res.list[0].projectTranId);
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
    getOutstandingByProject(projectId)
      .then((res) => setRows(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load outstanding payments.'))
      .finally(() => setLoading(false));
  }, [projectId]);

  const openDetail = async (clientId: number) => {
    setLoading(true);
    try {
      const res = await getClientPaymentSummary(clientId);
      if (res.code === 200 && res.object) setDetail(res.object);
    } catch {
      toast.error('Unable to load payment details.');
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<OutstandingPaymentRow>[] = [
    {
      key: 'ownerName',
      header: 'Client Name',
      render: (r) => (
        <a role="button" className="blue" onClick={() => openDetail(r.clientTranId)}>{r.ownerName}</a>
      ),
    },
    { key: 'mobileNo', header: 'Mobile No.' },
    { key: 'wingName', header: 'Wing Name' },
    { key: 'flatNumber', header: 'Flat Number' },
    { key: 'remainingAmount', header: 'Remaining Amount', className: 'text-end', render: (r) => formatINR(r.remainingAmount) },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-users me-2" />Outstanding Payment Details</div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Project:</label>
        <div className="col-sm-4">
          <select
            className="form-select"
            value={projectId}
            onChange={(e) => setProjectId(Number(e.target.value))}
          >
            {projects.map((p) => (
              <option key={p.projectTranId} value={p.projectTranId}>{p.projectName}</option>
            ))}
          </select>
        </div>
      </div>

      <DataTable
        columns={columns}
        rows={rows}
        searchKeys={['ownerName', 'mobileNo', 'wingName']}
      />

      <Modal show={!!detail} onHide={() => setDetail(null)} size="lg" centered>
        <Modal.Header closeButton>
          <Modal.Title>
            <i className="fa fa-user me-2" />{detail?.ownerName}
            <span className="ms-3 text-muted"><i className="fa fa-phone me-1" />{detail?.mobileNo}</span>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {detail && (
            <div className="row">
              <div className="col-sm-6">
                <InfoRow label="Project" value={detail.projectName} />
                <InfoRow label="Wing Name" value={detail.wingName} />
                <InfoRow label="Flat No." value={String(detail.flatNumber)} />
                <InfoRow label="Remaining Amount" value={`Rs. ${formatINR(detail.remainingAmount)}`} />
              </div>
              <div className="col-sm-6">
                <InfoRow label="Agreement Amount" value={`Rs. ${formatINR(detail.agreementAmount)}`} />
                <InfoRow label="GST Amount" value={`Rs. ${formatINR(detail.gstAmount)}`} />
                <InfoRow label="Total Amount" value={`Rs. ${formatINR(detail.totalAmount)}`} />
                <InfoRow label="Paid Amount" value={`Rs. ${formatINR(detail.paidAmount)}`} />
              </div>
            </div>
          )}
        </Modal.Body>
        <Modal.Footer>
          <button className="btn btn-sm btn-danger" onClick={() => setDetail(null)}>
            <i className="fa fa-times me-1" />Close
          </button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

function InfoRow({ label, value }: { label: string; value: string }) {
  return (
    <div className="d-flex justify-content-between border-bottom py-2">
      <span className="text-muted">{label}</span>
      <span className="fw-semibold">{value}</span>
    </div>
  );
}

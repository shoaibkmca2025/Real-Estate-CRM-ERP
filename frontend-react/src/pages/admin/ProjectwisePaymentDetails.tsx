import { useEffect, useMemo, useState, type FormEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap';
import dayjs from 'dayjs';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getPaymentDetailsByProjectId,
  getCompanyDetailsByCompanyId,
  savePaymentDetails,
  type CompanyPayRow,
  type CompanyPayInfo,
} from '../../api/adminApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';
import { formatINR } from '../../utils/format';

const PAYMENT_TYPES = [
  { value: 1, label: 'Demo', months: 0 },
  { value: 2, label: 'Monthly', months: 1 },
  { value: 3, label: 'Quarterly', months: 3 },
  { value: 4, label: 'Semi-Annual', months: 6 },
  { value: 5, label: 'Annual', months: 12 },
];

interface PaymentForm {
  paymentId: number;
  paymentType: number | '';
  amount: string;
  startDate: string;
  endDate: string;
  paidDate: string;
  discount: string;
  gst: string;
  totalAmount: string;
}
const EMPTY: PaymentForm = {
  paymentId: 0, paymentType: '', amount: '', startDate: '', endDate: '', paidDate: '', discount: '', gst: '', totalAmount: '',
};

/** Project-wise / company payment details — projectwisePaymentDetails.html / paymentDetailsCtrl.js. */
export default function ProjectwisePaymentDetails() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const ctx = useMemo(() => {
    try { return JSON.parse(sessionStorage.getItem('rc_payCtx') || 'null') as { projectId: number; projectName: string; companyId: number } | null; }
    catch { return null; }
  }, []);

  const [loading, setLoading] = useState(false);
  const [company, setCompany] = useState<CompanyPayInfo | null>(null);
  const [rows, setRows] = useState<CompanyPayRow[]>([]);
  const [show, setShow] = useState(false);
  const [form, setForm] = useState<PaymentForm>(EMPTY);

  const load = () => {
    if (!ctx) { navigate('/admindashboard/userPaymentDetails'); return; }
    setLoading(true);
    getPaymentDetailsByProjectId(ctx.projectId)
      .then(async (res) => {
        const list = res.list ?? [];
        setRows(list);
        if (list[0]) setCompany(list[0] as unknown as CompanyPayInfo);
        else {
          const c = await getCompanyDetailsByCompanyId(ctx.companyId);
          if (c.code === 200 && c.object) setCompany({ ...c.object, projectName: ctx.projectName });
        }
      })
      .catch(() => toast.error('Unable to load payment details.'))
      .finally(() => setLoading(false));
  };
  useEffect(load, []);

  // recompute end date + total whenever inputs change
  const recompute = (next: PaymentForm): PaymentForm => {
    const amount = parseFloat(next.amount) || 0;
    const discount = parseFloat(next.discount) || 0;
    const gst = parseFloat(next.gst) || 0;
    const total = amount - (amount * discount) / 100 + (amount * gst) / 100;
    let endDate = next.endDate;
    const type = PAYMENT_TYPES.find((t) => t.value === next.paymentType);
    if (type && next.startDate) {
      endDate = type.months ? dayjs(next.startDate).add(type.months, 'month').format('YYYY-MM-DD') : next.startDate;
    }
    return { ...next, endDate, totalAmount: total ? total.toFixed(2) : '' };
  };
  const set = (k: keyof PaymentForm, v: string | number) => setForm((f) => recompute({ ...f, [k]: v }));

  const openModal = (row?: CompanyPayRow) => {
    if (row) {
      setForm(recompute({
        paymentId: row.paymentId,
        paymentType: row.paymentType,
        amount: String(row.amount ?? ''),
        startDate: row.startDate ? dayjs(row.startDate, 'DD/MM/YYYY').format('YYYY-MM-DD') : '',
        endDate: row.endDate ? dayjs(row.endDate, 'DD/MM/YYYY').format('YYYY-MM-DD') : '',
        paidDate: row.paidDate ? dayjs(row.paidDate, 'DD/MM/YYYY').format('YYYY-MM-DD') : '',
        discount: String(row.discount ?? ''),
        gst: String(row.gst ?? ''),
        totalAmount: String(row.totalAmount ?? ''),
      }));
    } else {
      setForm(EMPTY);
    }
    setShow(true);
  };

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (!ctx || !session) return;
    setLoading(true);
    try {
      const res = await savePaymentDetails({
        ...form,
        companyId: ctx.companyId,
        projectId: ctx.projectId,
        userId: session.userId,
        startDate: form.startDate ? dayjs(form.startDate).format('DD/MM/YYYY') : '',
        endDate: form.endDate ? dayjs(form.endDate).format('DD/MM/YYYY') : '',
        paidDate: form.paidDate ? dayjs(form.paidDate).format('DD/MM/YYYY') : '',
      });
      if (res.code === 200) {
        toast.success(res.message || 'Saved.');
        setShow(false);
        load();
      } else {
        toast.error(res.message || 'Unable to save payment.');
      }
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<CompanyPayRow>[] = [
    { key: 'paymentTypeName', header: 'Payment type' },
    { key: 'startDate', header: 'Start Date' },
    { key: 'endDate', header: 'End Date' },
    { key: 'paidDate', header: 'Paid Date' },
    { key: 'amount', header: 'Amount', className: 'text-end', render: (r) => formatINR(r.amount) },
    { key: 'discount', header: 'Discount(%)', className: 'text-end' },
    { key: 'gst', header: 'GST(%)', className: 'text-end' },
    { key: 'totalAmount', header: 'Total Amount', className: 'text-end', render: (r) => formatINR(r.totalAmount) },
    {
      key: 'action', header: 'Action', sortable: false,
      render: (r) => <a role="button" className="green" title="Edit" onClick={() => openModal(r)}><i className="fa fa-pencil" /></a>,
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3" style={{ background: '#2563eb', color: '#fff', padding: '10px 14px', borderRadius: 8 }}>
        <span><i className="fa fa-user me-2" />{company?.companyName} {company?.mobile && <>(<i className="fa fa-phone mx-1" />{company.mobile})</>}</span>
        <button className="btn btn-sm btn-light" onClick={() => openModal()}><i className="fa fa-plus me-1" />Add Payment</button>
      </div>

      {company && (
        <div className="row mb-3">
          <div className="col-sm-6">
            <InfoRow label="Company" value={company.companyName} />
            <InfoRow label="Website" value={company.website} />
            <InfoRow label="Landline" value={company.landline} />
          </div>
          <div className="col-sm-6">
            <InfoRow label="Company Email" value={company.companyEmail} />
            <InfoRow label="Location" value={company.address} />
          </div>
        </div>
      )}

      <div className="label label-info mb-2 d-inline-block">{ctx?.projectName} Payment Details</div>
      <DataTable columns={columns} rows={rows} searchKeys={['paymentTypeName']} />

      <div className="text-center mt-3">
        <button className="btn btn-info btn-sm text-white" onClick={() => navigate('/admindashboard/userPaymentDetails')}>
          <i className="fa fa-reply-all me-1" />Back
        </button>
      </div>

      <Modal show={show} onHide={() => setShow(false)} centered>
        <Modal.Header closeButton><Modal.Title>{form.paymentId ? 'Update' : 'Add'} Payment Details</Modal.Title></Modal.Header>
        <form onSubmit={submit}>
          <Modal.Body>
            <div className="mb-3">
              <label className="form-label">Payment Type</label>
              <select className="form-select" value={form.paymentType} onChange={(e) => set('paymentType', Number(e.target.value))} required>
                <option value="">Click to Choose...</option>
                {PAYMENT_TYPES.map((t) => <option key={t.value} value={t.value}>{t.label}</option>)}
              </select>
            </div>
            <FieldNum label="Amount" value={form.amount} onChange={(v) => set('amount', v)} required />
            <FieldDate label="Start Date" value={form.startDate} onChange={(v) => set('startDate', v)} required />
            <FieldDate label="End Date" value={form.endDate} onChange={() => {}} disabled />
            <FieldDate label="Paid Date" value={form.paidDate} onChange={(v) => set('paidDate', v)} required />
            <FieldNum label="Discount (%)" value={form.discount} onChange={(v) => set('discount', v)} />
            <FieldNum label="GST (%)" value={form.gst} onChange={(v) => set('gst', v)} />
            <div className="mb-3">
              <label className="form-label">Total Amount</label>
              <input className="form-control" value={form.totalAmount} readOnly />
            </div>
          </Modal.Body>
          <Modal.Footer>
            <button type="submit" className="btn btn-sm btn-primary"><i className="fa fa-check me-1" />{form.paymentId ? 'Update' : 'Add'}</button>
            <button type="button" className="btn btn-sm btn-secondary" onClick={() => setShow(false)}>Cancel</button>
          </Modal.Footer>
        </form>
      </Modal>
    </div>
  );
}

const InfoRow = ({ label, value }: { label: string; value?: string }) => (
  <div className="d-flex justify-content-between border-bottom py-2"><span className="text-muted">{label}</span><span className="fw-semibold">{value}</span></div>
);
const FieldNum = ({ label, value, onChange, required = false }: { label: string; value: string; onChange: (v: string) => void; required?: boolean }) => (
  <div className="mb-3"><label className="form-label">{label}</label><input className="form-control" value={value} onChange={(e) => onChange(e.target.value)} required={required} /></div>
);
const FieldDate = ({ label, value, onChange, required = false, disabled = false }: { label: string; value: string; onChange: (v: string) => void; required?: boolean; disabled?: boolean }) => (
  <div className="mb-3"><label className="form-label">{label}</label><input type="date" className="form-control" value={value} onChange={(e) => onChange(e.target.value)} required={required} disabled={disabled} /></div>
);

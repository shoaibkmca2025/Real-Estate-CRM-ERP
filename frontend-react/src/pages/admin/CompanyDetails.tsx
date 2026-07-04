import { useEffect, useState, type FormEvent } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import {
  getAllCompanies,
  getCompanyById,
  addCompany,
  updateCompany,
  normaliseWebsite,
  type Company,
} from '../../api/companyApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

const EMPTY: Company = {
  companyName: '', companyEmail: '', website: '', mobile: '', landline: '',
  address: '', marketedBy: '', marketedByWebsite: '', startDate: '', endDate: '',
};

/** Company list + add/edit — companyDetails.html / companyDetailsCtrl.js. */
export default function CompanyDetails() {
  const [loading, setLoading] = useState(false);
  const [companies, setCompanies] = useState<Company[]>([]);
  const [show, setShow] = useState(false);
  const [editingId, setEditingId] = useState(0);
  const [form, setForm] = useState<Company>(EMPTY);

  const load = () => {
    setLoading(true);
    getAllCompanies()
      .then((res) => setCompanies(res.list ?? []))
      .catch(() => toast.error('Unable to load companies.'))
      .finally(() => setLoading(false));
  };
  useEffect(load, []);

  const openModal = async (companyId: number) => {
    setEditingId(companyId);
    setForm(EMPTY);
    if (companyId !== 0) {
      setLoading(true);
      try {
        const res = await getCompanyById(companyId);
        if (res.code === 200 && res.object) setForm(res.object);
      } finally {
        setLoading(false);
      }
    }
    setShow(true);
  };

  const set = (k: keyof Company, v: string) => setForm((f) => ({ ...f, [k]: v }));

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (editingId === 0 && !form.startDate) {
      toast.error('Please select Start Date.');
      return;
    }
    if (!window.confirm('Are you sure?')) return;

    const payload: Company = {
      ...form,
      website: normaliseWebsite(form.website),
      marketedByWebsite: normaliseWebsite(form.marketedByWebsite),
    };

    setLoading(true);
    try {
      const res = editingId === 0
        ? await addCompany({ ...payload, resultDate: payload.startDate })
        : await updateCompany({ ...payload, companyId: editingId });
      if (res.code === 200) {
        toast.success(res.message || 'Saved.');
        setShow(false);
        load();
      } else {
        toast.error(res.message || 'Unable to save company.');
      }
    } catch {
      toast.error('Unable to save company.');
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<Company>[] = [
    { key: 'companyName', header: 'Company Name', render: (c) => <b>{c.companyName}</b> },
    { key: 'companyEmail', header: 'Company Email' },
    { key: 'website', header: 'Website' },
    { key: 'mobile', header: 'Mobile' },
    { key: 'landline', header: 'Landline No' },
    { key: 'address', header: 'Address' },
    { key: 'marketedBy', header: 'Marketed By' },
    {
      key: 'action', header: 'Action', sortable: false,
      render: (c) => (
        <a role="button" title="Edit" className="blue" onClick={() => openModal(c.companyId!)}>
          <i className="fa fa-pen-to-square" />
        </a>
      ),
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-industry me-2" />View Company List</div>
        <button className="btn btn-info btn-sm text-white" onClick={() => openModal(0)}>
          <i className="fa fa-plus me-1" />Add Company
        </button>
      </div>

      <DataTable columns={columns} rows={companies} searchKeys={['companyName', 'companyEmail', 'mobile', 'address']} />

      <Modal show={show} onHide={() => setShow(false)} size="lg" centered>
        <Modal.Header closeButton>
          <Modal.Title>{editingId === 0 ? 'Add' : 'Update'} Company Details</Modal.Title>
        </Modal.Header>
        <form onSubmit={submit}>
          <Modal.Body>
            <div className="row g-3">
              <Field label="Company Name" value={form.companyName} onChange={(v) => set('companyName', v)} required />
              <Field label="Company Email" type="email" value={form.companyEmail} onChange={(v) => set('companyEmail', v)} required />
              <Field label="Website" value={form.website ?? ''} onChange={(v) => set('website', v)} />
              <Field label="Mobile" value={form.mobile} onChange={(v) => set('mobile', v)} required />
              <Field label="Landline No" value={form.landline ?? ''} onChange={(v) => set('landline', v)} />
              <Field label="Address" value={form.address} onChange={(v) => set('address', v)} required />
              <Field label="Marketed By" value={form.marketedBy ?? ''} onChange={(v) => set('marketedBy', v)} />
              <Field label="Marketed By Website" value={form.marketedByWebsite ?? ''} onChange={(v) => set('marketedByWebsite', v)} />
              <Field label="Start Date" type="date" value={form.startDate ?? ''} onChange={(v) => set('startDate', v)} />
              <Field label="End Date" type="date" value={form.endDate ?? ''} onChange={(v) => set('endDate', v)} />
            </div>
          </Modal.Body>
          <Modal.Footer>
            <button type="submit" className="btn btn-sm btn-primary"><i className="fa fa-check me-1" />{editingId === 0 ? 'Add' : 'Update'}</button>
            <button type="button" className="btn btn-sm btn-secondary" onClick={() => setShow(false)}><i className="fa fa-times me-1" />Cancel</button>
          </Modal.Footer>
        </form>
      </Modal>
    </div>
  );
}

function Field({
  label, value, onChange, type = 'text', required = false,
}: {
  label: string; value: string; onChange: (v: string) => void; type?: string; required?: boolean;
}) {
  return (
    <div className="col-sm-6">
      <label className="form-label">{label}{required && ' *'}</label>
      <input className="form-control" type={type} value={value} onChange={(e) => onChange(e.target.value)} required={required} />
    </div>
  );
}

import { useEffect, useState, type FormEvent } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getAllProjectDetailsFull,
  getAllPropertyTypes,
  getPropertyByType,
  getPropertyArea,
  getAllReferences,
  getEnquiryFormById,
  addEnquiry,
  updateEnquiry,
  type ProjectFull,
  type EnquiryForm,
} from '../../api/enquiryApi';
import Loader from '../../components/common/Loader';

type Opt = { id: number | string; label: string };

/** Add / Edit Enquiry — addEnquiryDetails.html / enquiryCtrl.js. */
export default function AddEnquiry() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [params] = useSearchParams();
  const editId = params.get('id');
  const isEdit = !!editId;

  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState<EnquiryForm>({});
  const [projects, setProjects] = useState<ProjectFull[]>([]);
  const [propertyTypes, setPropertyTypes] = useState<Opt[]>([]);
  const [properties, setProperties] = useState<Opt[]>([]);
  const [areas, setAreas] = useState<(string | number)[]>([]);
  const [references, setReferences] = useState<Opt[]>([]);

  const set = (k: keyof EnquiryForm, v: unknown) => setForm((f) => ({ ...f, [k]: v }));

  // initial data
  useEffect(() => {
    if (!session) return;
    setLoading(true);
    Promise.all([
      getAllProjectDetailsFull(session.userType, session.userId, session.companyId ?? '', 2),
      getAllReferences(),
    ])
      .then(([proj, ref]) => {
        setProjects((proj.list ?? []).filter((p) => p.projectStatus === 2));
        setReferences((ref.list ?? []).map((r) => ({ id: r.referenceId, label: r.referenceTypeDescr })));
      })
      .finally(() => setLoading(false));
  }, [session]);

  // load enquiry for edit (and its cascaded dropdowns)
  useEffect(() => {
    if (!editId) return;
    setLoading(true);
    (async () => {
      try {
        const res = await getEnquiryFormById(editId);
        if (res.code === 200 && res.object) {
          const obj = res.object;
          setForm(obj);
          const [types] = await Promise.all([getAllPropertyTypes()]);
          setPropertyTypes((types.list ?? []).map((t) => ({ id: t.propertyTypeId, label: t.propertyTypeDescr })));
          if (obj.propertyType) {
            const props = await getPropertyByType(obj.propertyType);
            setProperties((props.list ?? []).map((p) => ({ id: p.propertyId, label: p.propertyName })));
          }
          if (obj.projectId && obj.propertyType && obj.property) {
            const ar = await getPropertyArea(obj.projectId, obj.propertyType, obj.property);
            setAreas((ar.list ?? []).map((a) => a.propertyArea));
          }
        }
      } finally {
        setLoading(false);
      }
    })();
  }, [editId]);

  // cascading loaders
  const onProjectChange = async (projectId: string) => {
    setForm((f) => ({ ...f, projectId, propertyType: '', property: '', propertyArea: '' }));
    setProperties([]);
    setAreas([]);
    const res = await getAllPropertyTypes();
    setPropertyTypes((res.list ?? []).map((t) => ({ id: t.propertyTypeId, label: t.propertyTypeDescr })));
  };
  const onTypeChange = async (propertyType: string) => {
    setForm((f) => ({ ...f, propertyType, property: '', propertyArea: '' }));
    setAreas([]);
    const res = await getPropertyByType(propertyType);
    setProperties((res.list ?? []).map((p) => ({ id: p.propertyId, label: p.propertyName })));
  };
  const onPropertyChange = async (property: string) => {
    setForm((f) => ({ ...f, property, propertyArea: '' }));
    if (form.projectId && form.propertyType) {
      const res = await getPropertyArea(form.projectId, form.propertyType, property);
      setAreas((res.list ?? []).map((a) => a.propertyArea));
    }
  };

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (!session) return;
    const payload: EnquiryForm = {
      ...form,
      propertyArea:
        typeof form.propertyArea === 'object' && form.propertyArea !== null
          ? (form.propertyArea as { propertyArea: string | number }).propertyArea
          : form.propertyArea,
      userId: session.userId,
    };
    setLoading(true);
    try {
      let res;
      if (isEdit) {
        res = await updateEnquiry(payload);
      } else {
        res = await addEnquiry({ ...payload, userType: session.userType, companyId: session.companyId });
      }
      if (res.code === 200) {
        toast.success(res.message || 'Saved.');
        navigate('/dashboard/enquiry');
      } else {
        toast.error(res.message || 'Unable to save.');
      }
    } catch {
      toast.error('Unable to save enquiry.');
    } finally {
      setLoading(false);
    }
  };

  const reset = () => {
    setForm({});
    setProperties([]);
    setAreas([]);
  };

  return (
    <form onSubmit={submit}>
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-book me-2" />{isEdit ? 'Update' : 'Add'} Enquiry Information</div>
      <div className="row g-3">
        {/* Left column — property */}
        <div className="col-md-4">
          <div className="rc-card">
            <Select label="Project" value={form.projectId} onChange={onProjectChange}
              options={projects.map((p) => ({ id: p.projectTranId, label: p.projectName }))} required />
            <Select label="Property Type" value={form.propertyType} onChange={onTypeChange} options={propertyTypes} required />
            <Select label="Property" value={form.property} onChange={onPropertyChange} options={properties} required />
            <div className="mb-3">
              <label className="form-label">Area (in Sq. ft.)</label>
              <select className="form-select" value={String(form.propertyArea ?? '')} onChange={(e) => set('propertyArea', e.target.value)} required>
                <option value="">Click to Choose...</option>
                {areas.map((a, i) => <option key={i} value={String(a)}>{a}</option>)}
              </select>
            </div>
            <Input label="Budget (in Lakhs)" value={form.budgetMax} onChange={(v) => set('budgetMax', v.replace(/[^0-9]/g, ''))} required />
            <Select label="Reference" value={form.reference} onChange={(v) => set('reference', v)} options={references} required />
            <Input label="Reference Name" value={form.referenceName} onChange={(v) => set('referenceName', v)} />
          </div>
        </div>

        {/* Right column — personal */}
        <div className="col-md-8">
          <div className="rc-card">
            <div className="row g-3">
              <Col6><Input label="First Name" value={form.firstName} onChange={(v) => set('firstName', v)} required /></Col6>
              <Col6><Input label="Last Name" value={form.lastName} onChange={(v) => set('lastName', v)} required /></Col6>
              <Col6><Input label="Mobile Number" value={form.mobileNo} onChange={(v) => set('mobileNo', v)} required /></Col6>
              <Col6><Input label="Landline Number (With STD Code)" value={form.landlineNo} onChange={(v) => set('landlineNo', v)} /></Col6>
              <Col6><Input label="Email" type="email" value={form.email} onChange={(v) => set('email', v)} required /></Col6>
              <Col6><Input label="City" value={form.city} onChange={(v) => set('city', v)} required /></Col6>
              <div className="col-12"><Input label="Address" value={form.address} onChange={(v) => set('address', v)} required /></div>
              <Col6><Input label="Occupation" value={form.occupation} onChange={(v) => set('occupation', v)} required /></Col6>
              <Col6><Input label="Company" value={form.company} onChange={(v) => set('company', v)} /></Col6>
            </div>
          </div>
          <div className="rc-card d-flex gap-3">
            <button type="submit" className="btn btn-success flex-fill">{isEdit ? 'Update' : 'Add'}</button>
            <button type="button" className="btn btn-danger flex-fill" onClick={reset}>Reset</button>
          </div>
        </div>
      </div>
    </form>
  );
}

const Col6 = ({ children }: { children: React.ReactNode }) => <div className="col-sm-6">{children}</div>;

function Input({
  label, value, onChange, type = 'text', required = false,
}: {
  label: string; value?: string | number; onChange: (v: string) => void; type?: string; required?: boolean;
}) {
  return (
    <div className="mb-3">
      <label className="form-label">{label}{required && ' *'}</label>
      <input className="form-control" type={type} value={value ?? ''} onChange={(e) => onChange(e.target.value)} required={required} />
    </div>
  );
}

function Select({
  label, value, onChange, options, required = false,
}: {
  label: string; value?: number | string; onChange: (v: string) => void; options: Opt[]; required?: boolean;
}) {
  return (
    <div className="mb-3">
      <label className="form-label">{label}{required && ' *'}</label>
      <select className="form-select" value={String(value ?? '')} onChange={(e) => onChange(e.target.value)} required={required}>
        <option value="">Click to Choose...</option>
        {options.map((o) => <option key={String(o.id)} value={String(o.id)}>{o.label}</option>)}
      </select>
    </div>
  );
}

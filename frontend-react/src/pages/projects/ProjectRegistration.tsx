import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import { addProjectDetails } from '../../api/bookingApi';
import Loader from '../../components/common/Loader';

const STEPS = ['Project Info', 'Wings', 'Disbursement', 'Banks', 'Amenities', 'Review'];

interface Wing { wingName: string; totalFloors: string; flatsPerFloor: string }
interface Disb { disbursementTitle: string; percentageValue: string }
interface Bank { bankName: string; branch: string; accountNumber: string; ifscCode: string; bankType: string }

/** Project Registration wizard — projectRegistration.html / projectRegistrationCtrl.js. */
export default function ProjectRegistration() {
  const { session } = useAuth();
  const navigate = useNavigate();
  const [step, setStep] = useState(0);
  const [loading, setLoading] = useState(false);

  // Step 1 — project info
  const [project, setProject] = useState({
    projectName: '', address: '', mahareraNo: '', startDate: '', expectedCompletionDate: '',
  });
  // Step 2 — wings
  const [wings, setWings] = useState<Wing[]>([{ wingName: '', totalFloors: '', flatsPerFloor: '' }]);
  // Step 3 — disbursement
  const [disbursements, setDisbursements] = useState<Disb[]>([{ disbursementTitle: '', percentageValue: '' }]);
  // Step 4 — banks
  const [banks, setBanks] = useState<Bank[]>([{ bankName: '', branch: '', accountNumber: '', ifscCode: '', bankType: '1' }]);
  // Step 5 — amenities
  const [amenities, setAmenities] = useState<string[]>(['']);

  const next = () => setStep((s) => Math.min(s + 1, STEPS.length - 1));
  const prev = () => setStep((s) => Math.max(s - 1, 0));

  const totalDisb = disbursements.reduce((s, d) => s + (parseFloat(d.percentageValue) || 0), 0);

  const submit = async () => {
    if (!session) return;
    if (!project.projectName || !project.address) {
      toast.error('Project name and address are required.');
      setStep(0);
      return;
    }
    if (totalDisb !== 100 && disbursements.some((d) => d.disbursementTitle)) {
      toast.error('Disbursement percentages must total 100%.');
      setStep(2);
      return;
    }
    setLoading(true);
    try {
      const res = await addProjectDetails({
        ...project,
        userId: session.userId,
        userType: session.userType,
        companyId: session.companyId,
        wingList: wings.filter((w) => w.wingName),
        disbursementList: disbursements.filter((d) => d.disbursementTitle),
        bankDetailsList: banks.filter((b) => b.bankName),
        amenitiesList: amenities.filter(Boolean).map((a) => ({ amenitiesName: a })),
      });
      if (res.code === 200) {
        toast.success(res.message || 'Project added successfully and sent for approval.');
        navigate('/dashboard/viewProjects');
      } else {
        toast.error(res.message || 'Unable to add project.');
      }
    } catch {
      toast.error('Unable to add project.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-edit me-2" />Project Registration</div>

      {/* Step indicator */}
      <div className="d-flex mb-4 flex-wrap gap-2">
        {STEPS.map((label, i) => (
          <div
            key={label}
            className={`px-3 py-2 rounded ${i === step ? 'bg-primary text-white' : i < step ? 'bg-success text-white' : 'bg-light text-muted'}`}
            style={{ cursor: i < step ? 'pointer' : 'default' }}
            onClick={() => i < step && setStep(i)}
          >
            {i + 1}. {label}
          </div>
        ))}
      </div>

      {/* Step 1 */}
      {step === 0 && (
        <div className="row g-3">
          <Field col={6} label="Project Name" value={project.projectName} onChange={(v) => setProject({ ...project, projectName: v })} required />
          <Field col={6} label="RERA No." value={project.mahareraNo} onChange={(v) => setProject({ ...project, mahareraNo: v })} />
          <Field col={12} label="Address" value={project.address} onChange={(v) => setProject({ ...project, address: v })} required />
          <Field col={6} label="Start Date" type="date" value={project.startDate} onChange={(v) => setProject({ ...project, startDate: v })} />
          <Field col={6} label="Expected Completion Date" type="date" value={project.expectedCompletionDate} onChange={(v) => setProject({ ...project, expectedCompletionDate: v })} />
        </div>
      )}

      {/* Step 2 — Wings */}
      {step === 1 && (
        <RepeaterTable
          headers={['Wing Name', 'Total Floors', 'Flats / Floor']}
          rows={wings}
          onAdd={() => setWings([...wings, { wingName: '', totalFloors: '', flatsPerFloor: '' }])}
          onRemove={(i) => setWings(wings.filter((_, idx) => idx !== i))}
          render={(w, i) => (
            <>
              <Cell value={w.wingName} onChange={(v) => setWings(wings.map((x, idx) => (idx === i ? { ...x, wingName: v } : x)))} />
              <Cell value={w.totalFloors} onChange={(v) => setWings(wings.map((x, idx) => (idx === i ? { ...x, totalFloors: v } : x)))} />
              <Cell value={w.flatsPerFloor} onChange={(v) => setWings(wings.map((x, idx) => (idx === i ? { ...x, flatsPerFloor: v } : x)))} />
            </>
          )}
        />
      )}

      {/* Step 3 — Disbursement */}
      {step === 2 && (
        <>
          <RepeaterTable
            headers={['Disbursement Title', 'Percentage (%)']}
            rows={disbursements}
            onAdd={() => setDisbursements([...disbursements, { disbursementTitle: '', percentageValue: '' }])}
            onRemove={(i) => setDisbursements(disbursements.filter((_, idx) => idx !== i))}
            render={(d, i) => (
              <>
                <Cell value={d.disbursementTitle} onChange={(v) => setDisbursements(disbursements.map((x, idx) => (idx === i ? { ...x, disbursementTitle: v } : x)))} />
                <Cell value={d.percentageValue} onChange={(v) => setDisbursements(disbursements.map((x, idx) => (idx === i ? { ...x, percentageValue: v } : x)))} />
              </>
            )}
          />
          <div className={`mt-2 fw-bold ${totalDisb === 100 ? 'text-success' : 'text-danger'}`}>Total: {totalDisb}% (must be 100%)</div>
        </>
      )}

      {/* Step 4 — Banks */}
      {step === 3 && (
        <RepeaterTable
          headers={['Bank Name', 'Branch', 'Account No.', 'IFSC', 'Type']}
          rows={banks}
          onAdd={() => setBanks([...banks, { bankName: '', branch: '', accountNumber: '', ifscCode: '', bankType: '1' }])}
          onRemove={(i) => setBanks(banks.filter((_, idx) => idx !== i))}
          render={(b, i) => (
            <>
              <Cell value={b.bankName} onChange={(v) => setBanks(banks.map((x, idx) => (idx === i ? { ...x, bankName: v } : x)))} />
              <Cell value={b.branch} onChange={(v) => setBanks(banks.map((x, idx) => (idx === i ? { ...x, branch: v } : x)))} />
              <Cell value={b.accountNumber} onChange={(v) => setBanks(banks.map((x, idx) => (idx === i ? { ...x, accountNumber: v } : x)))} />
              <Cell value={b.ifscCode} onChange={(v) => setBanks(banks.map((x, idx) => (idx === i ? { ...x, ifscCode: v } : x)))} />
              <td>
                <select className="form-select form-select-sm" value={b.bankType} onChange={(e) => setBanks(banks.map((x, idx) => (idx === i ? { ...x, bankType: e.target.value } : x)))}>
                  <option value="1">Approved</option>
                  <option value="2">Disbursement</option>
                </select>
              </td>
            </>
          )}
        />
      )}

      {/* Step 5 — Amenities */}
      {step === 4 && (
        <div>
          {amenities.map((a, i) => (
            <div key={i} className="d-flex gap-2 mb-2">
              <input className="form-control" value={a} placeholder="Amenity" onChange={(e) => setAmenities(amenities.map((x, idx) => (idx === i ? e.target.value : x)))} />
              <button className="btn btn-outline-danger" onClick={() => setAmenities(amenities.filter((_, idx) => idx !== i))}><i className="fa fa-trash" /></button>
            </div>
          ))}
          <button className="btn btn-sm btn-outline-primary" onClick={() => setAmenities([...amenities, ''])}><i className="fa fa-plus me-1" />Add Amenity</button>
        </div>
      )}

      {/* Step 6 — Review */}
      {step === 5 && (
        <div>
          <h6>Review</h6>
          <p><b>Project:</b> {project.projectName} — {project.address} {project.mahareraNo && `(RERA ${project.mahareraNo})`}</p>
          <p><b>Wings:</b> {wings.filter((w) => w.wingName).map((w) => w.wingName).join(', ') || '—'}</p>
          <p><b>Disbursements:</b> {disbursements.filter((d) => d.disbursementTitle).length} ({totalDisb}%)</p>
          <p><b>Banks:</b> {banks.filter((b) => b.bankName).length}</p>
          <p><b>Amenities:</b> {amenities.filter(Boolean).join(', ') || '—'}</p>
        </div>
      )}

      {/* Nav buttons */}
      <div className="d-flex justify-content-between mt-4">
        <button className="btn btn-secondary" onClick={prev} disabled={step === 0}><i className="fa fa-arrow-left me-1" />Back</button>
        {step < STEPS.length - 1 ? (
          <button className="btn btn-primary" onClick={next}>Next <i className="fa fa-arrow-right ms-1" /></button>
        ) : (
          <button className="btn btn-success" onClick={submit}><i className="fa fa-check me-1" />Submit Project</button>
        )}
      </div>
    </div>
  );
}

function Field({ col, label, value, onChange, type = 'text', required = false }: { col: number; label: string; value: string; onChange: (v: string) => void; type?: string; required?: boolean }) {
  return (
    <div className={`col-sm-${col}`}>
      <label className="form-label">{label}{required && ' *'}</label>
      <input className="form-control" type={type} value={value} onChange={(e) => onChange(e.target.value)} required={required} />
    </div>
  );
}

const Cell = ({ value, onChange }: { value: string; onChange: (v: string) => void }) => (
  <td><input className="form-control form-control-sm" value={value} onChange={(e) => onChange(e.target.value)} /></td>
);

function RepeaterTable<T>({ headers, rows, render, onAdd, onRemove }: {
  headers: string[]; rows: T[]; render: (row: T, i: number) => React.ReactNode; onAdd: () => void; onRemove: (i: number) => void;
}) {
  return (
    <div>
      <div className="table-responsive">
        <table className="table table-bordered table-condensed">
          <thead><tr>{headers.map((h) => <th key={h}>{h}</th>)}<th /></tr></thead>
          <tbody>
            {rows.map((row, i) => (
              <tr key={i}>
                {render(row, i)}
                <td><button className="btn btn-sm btn-outline-danger" onClick={() => onRemove(i)}><i className="fa fa-trash" /></button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <button className="btn btn-sm btn-outline-primary" onClick={onAdd}><i className="fa fa-plus me-1" />Add Row</button>
    </div>
  );
}

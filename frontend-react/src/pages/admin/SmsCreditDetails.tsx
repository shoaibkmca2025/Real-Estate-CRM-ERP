import { useEffect, useState, type FormEvent } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import {
  getAllCompanyDetails,
  getSmsCreditsByCompany,
  addSmsCredits,
  type CompanyOption,
  type SmsCreditRow,
} from '../../api/smsApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

/** SMS Credit Details — smsCreditDetails.html / smsCreditDetailsCtrl.js. */
export default function SmsCreditDetails() {
  const [loading, setLoading] = useState(false);
  const [companies, setCompanies] = useState<CompanyOption[]>([]);
  const [companyId, setCompanyId] = useState<number | ''>('');
  const [rows, setRows] = useState<SmsCreditRow[]>([]);
  const [totalAvailable, setTotalAvailable] = useState(0);
  const [showAdd, setShowAdd] = useState(false);
  const [totalCredits, setTotalCredits] = useState('');

  useEffect(() => {
    setLoading(true);
    getAllCompanyDetails()
      .then((res) => {
        const list = res.list ?? [];
        setCompanies(list);
        if (list.length) setCompanyId(list[0].companyId);
      })
      .catch(() => toast.error('Unable to load companies.'))
      .finally(() => setLoading(false));
  }, []);

  const loadCredits = (id: number) => {
    setLoading(true);
    getSmsCreditsByCompany(id)
      .then((res) => {
        const list = res.list ?? [];
        setRows(list);
        setTotalAvailable(list.length ? list[list.length - 1].availableCredits : 0);
      })
      .catch(() => toast.error('Unable to load SMS credits.'))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    if (companyId !== '') loadCredits(companyId);
  }, [companyId]);

  const submitCredits = async (e: FormEvent) => {
    e.preventDefault();
    if (!totalCredits) return;
    if (companyId === '') return;
    setLoading(true);
    try {
      const res = await addSmsCredits({
        companyId,
        totalCredits: Number(totalCredits),
        availableCredits: Number(totalCredits) + totalAvailable,
      });
      if (res.code === 200) {
        toast.success(res.message || 'Credits added.');
        setShowAdd(false);
        setTotalCredits('');
        loadCredits(companyId);
      } else {
        toast.error(res.message || 'Unable to add credits.');
      }
    } catch {
      toast.error('Unable to add credits.');
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<SmsCreditRow>[] = [
    { key: 'insertionDate', header: 'Date' },
    { key: 'totalCredits', header: 'Total Credits', className: 'text-end' },
    { key: 'totalSent', header: 'Sent', className: 'text-end' },
    { key: 'availableCredits', header: 'Available', className: 'text-end' },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-industry me-2" />View SMS Credit Details</div>
        <button className="btn btn-info btn-sm text-white" onClick={() => setShowAdd(true)}>
          <i className="fa fa-plus me-1" />Add Credits
        </button>
      </div>

      <div className="row align-items-center mb-3">
        <label className="col-sm-2 col-form-label">Select Company:</label>
        <div className="col-sm-4">
          <select className="form-select" value={companyId} onChange={(e) => setCompanyId(Number(e.target.value))}>
            {companies.map((c) => (
              <option key={c.companyId} value={c.companyId}>{c.companyName}</option>
            ))}
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={rows} />
      <div className="text-end fw-bold mt-2">Total Available: {totalAvailable}</div>

      <Modal show={showAdd} onHide={() => setShowAdd(false)} centered>
        <Modal.Header closeButton><Modal.Title>Add SMS Credits</Modal.Title></Modal.Header>
        <form onSubmit={submitCredits}>
          <Modal.Body>
            <div className="mb-2">
              <label className="form-label">Total Credits:</label>
              <input
                type="number"
                className="form-control"
                placeholder="Enter total credits"
                value={totalCredits}
                onChange={(e) => setTotalCredits(e.target.value)}
                required
              />
            </div>
          </Modal.Body>
          <Modal.Footer>
            <button type="submit" className="btn btn-sm btn-primary"><i className="fa fa-check me-1" />Add</button>
            <button type="button" className="btn btn-sm btn-secondary" onClick={() => setShowAdd(false)}>
              <i className="fa fa-times me-1" />Cancel
            </button>
          </Modal.Footer>
        </form>
      </Modal>
    </div>
  );
}

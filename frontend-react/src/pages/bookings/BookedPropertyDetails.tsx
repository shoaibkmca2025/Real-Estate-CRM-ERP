import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { getBookedPropertyDetails } from '../../api/bookingApi';
import Loader from '../../components/common/Loader';
import { formatINR } from '../../utils/format';

/** Booked property details — showBookedPropertyDetails.html / showBookedPropertyDetailsCtrl.js. */
export default function BookedPropertyDetails() {
  const navigate = useNavigate();
  const flat = useMemo(() => {
    try { return JSON.parse(sessionStorage.getItem('rc_flat') || 'null') as { projectId: number; wingId: number; floorNumber: number; flatNumber: number } | null; }
    catch { return null; }
  }, []);

  const [loading, setLoading] = useState(false);
  const [details, setDetails] = useState<Record<string, unknown> | null>(null);

  useEffect(() => {
    if (!flat) {
      navigate('/dashboard/bookingDetails');
      return;
    }
    setLoading(true);
    getBookedPropertyDetails(flat.projectId, flat.wingId, flat.floorNumber, flat.flatNumber)
      .then((res) => {
        if (res.code === 200 && res.object) setDetails(res.object);
        else toast.info('This property has no booking yet.');
      })
      .catch(() => toast.error('Unable to load property details.'))
      .finally(() => setLoading(false));
  }, [flat, navigate]);

  const get = (k: string) => (details?.[k] != null ? String(details[k]) : '-');

  return (
    <div>
      <Loader show={loading} />
      <div className="rc-card d-flex justify-content-between align-items-center" style={{ background: '#2563eb', color: '#fff' }}>
        <span><i className="fa fa-building me-2" />Booked Property Details</span>
        <button className="btn btn-sm btn-light" onClick={() => navigate('/dashboard/bookingDetails')}><i className="fa fa-reply-all me-1" />Back</button>
      </div>

      {!details ? (
        <div className="rc-card text-muted">No booking information available for this flat.</div>
      ) : (
        <div className="rc-card">
          <div className="rc-card-title">Property &amp; Owner</div>
          <div className="row">
            <div className="col-sm-6">
              <Info label="Owner Name" value={get('ownerName')} />
              <Info label="Mobile" value={get('mobileNo')} />
              <Info label="Project" value={get('projectName')} />
              <Info label="Wing" value={get('wingName')} />
              <Info label="Flat No." value={get('flatNumber')} />
            </div>
            <div className="col-sm-6">
              <Info label="Property Area" value={get('propertyArea')} />
              <Info label="Agreement Amount" value={`Rs. ${formatINR(Number(details.agreementAmount) || 0)}`} />
              <Info label="Booking Date" value={get('bookingDate')} />
              <Info label="Booking Status" value={get('bookingStatusName')} />
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

const Info = ({ label, value }: { label: string; value: string }) => (
  <div className="d-flex justify-content-between border-bottom py-2"><span className="text-muted">{label}</span><span className="fw-semibold">{value}</span></div>
);

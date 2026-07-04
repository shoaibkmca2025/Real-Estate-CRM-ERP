import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { getClientDetailsById, type PropertyDetails } from '../../api/clientApi';
import Loader from '../../components/common/Loader';
import { formatINR } from '../../utils/format';

/** Client Details + disbursement payments — showClientDetails.html / showClientDetailsCtrl.js. */
export default function ClientDetails() {
  const navigate = useNavigate();
  const clientId = sessionStorage.getItem('rc_clientId');
  const [loading, setLoading] = useState(false);
  const [details, setDetails] = useState<PropertyDetails | null>(null);

  useEffect(() => {
    if (!clientId) {
      navigate('/dashboard/viewClients');
      return;
    }
    setLoading(true);
    getClientDetailsById(clientId)
      .then((res) => {
        if (res.code === 200 && res.object) setDetails(res.object);
        else toast.error('Unable to load client details.');
      })
      .catch(() => toast.error('Unable to load client details.'))
      .finally(() => setLoading(false));
  }, [clientId, navigate]);

  const totals = useMemo(() => {
    const d = details?.disbursementList ?? [];
    const o = details?.otherPaymentList ?? [];
    const gst = d.reduce((s, x) => s + (+x.gstAmount || 0), 0);
    const paid = d.reduce((s, x) => s + (+x.paidAmount || 0), 0);
    const remaining = d.reduce((s, x) => s + (+x.remainingAmount || 0), 0);
    const agreement = details?.agreementAmount ?? 0;
    const total = gst + agreement;
    const otherTotal = o.reduce((s, x) => s + (+x.amount || 0), 0);
    const otherPaid = o.filter((x) => x.paidDate).reduce((s, x) => s + (+x.amount || 0), 0);
    return { gst, paid, remaining, agreement, total, otherTotal, otherPaid, otherUnpaid: otherTotal - otherPaid };
  }, [details]);

  if (!details) return <Loader show={loading} />;

  return (
    <div>
      <Loader show={loading} />

      {/* Header */}
      <div className="rc-card d-flex justify-content-between align-items-center" style={{ background: '#2563eb', color: '#fff' }}>
        <span><i className="fa fa-user me-2" />{details.ownerName} <span className="ms-3"><i className="fa fa-phone me-1" />{details.mobileNo}</span></span>
        <button className="btn btn-sm btn-light" onClick={() => navigate('/dashboard/viewClients')}><i className="fa fa-reply-all me-1" />Back</button>
      </div>

      {/* Property info */}
      <div className="rc-card">
        <div className="rc-card-title">Property Details</div>
        <div className="row">
          <div className="col-sm-6">
            <Info label="Project" value={details.projectName} />
            <Info label="Wing" value={details.wingName} />
            <Info label="Flat No." value={String(details.flatNumber)} />
          </div>
          <div className="col-sm-6">
            <Info label="Area" value={String(details.propertyArea)} />
            <Info label="Agreement Amount" value={`Rs. ${formatINR(details.agreementAmount)}`} />
          </div>
        </div>
      </div>

      {/* Disbursement schedule */}
      <div className="rc-card">
        <div className="rc-card-title">Disbursement Details</div>
        <div className="table-responsive">
          <table className="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th>Title</th><th className="text-end">%</th><th className="text-end">Amount</th>
                <th className="text-end">GST</th><th className="text-end">Paid</th><th className="text-end">Remaining</th>
                <th>Paid Date</th><th>Status</th>
              </tr>
            </thead>
            <tbody>
              {details.disbursementList.length === 0 ? (
                <tr><td colSpan={8} className="text-center">No data available in table</td></tr>
              ) : (
                details.disbursementList.map((d) => (
                  <tr key={d.disbursementId}>
                    <td>{d.disbursementTitle}</td>
                    <td className="text-end">{d.percentageValue}%</td>
                    <td className="text-end">{formatINR(d.disbursementAmount)}</td>
                    <td className="text-end">{formatINR(d.gstAmount)}</td>
                    <td className="text-end">{formatINR(d.paidAmount)}</td>
                    <td className="text-end">{formatINR(d.remainingAmount)}</td>
                    <td>{d.paidDate || '-'}</td>
                    <td>{d.paidDate ? <span className="label label-success">Paid</span> : <span className="label label-danger">Pending</span>}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
        <div className="row mt-2">
          <div className="col-sm-3"><b>Total: </b>{formatINR(totals.total)}</div>
          <div className="col-sm-3"><b>GST: </b>{formatINR(totals.gst)}</div>
          <div className="col-sm-3"><b>Paid: </b>{formatINR(totals.paid)}</div>
          <div className="col-sm-3"><b>Unpaid: </b>{formatINR(totals.remaining)}</div>
        </div>
      </div>

      {/* Other payments */}
      {details.otherPaymentList?.length > 0 && (
        <div className="rc-card">
          <div className="rc-card-title">Other Payments</div>
          <div className="table-responsive">
            <table className="table table-striped table-bordered table-condensed">
              <thead><tr><th>Title</th><th className="text-end">Amount</th><th>Paid Date</th><th>Status</th></tr></thead>
              <tbody>
                {details.otherPaymentList.map((o, i) => (
                  <tr key={i}>
                    <td>{o.title}</td>
                    <td className="text-end">{formatINR(o.amount)}</td>
                    <td>{o.paidDate || '-'}</td>
                    <td>{o.paidDate ? <span className="label label-success">Paid</span> : <span className="label label-danger">Pending</span>}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="row mt-2">
            <div className="col-sm-4"><b>Total: </b>{formatINR(totals.otherTotal)}</div>
            <div className="col-sm-4"><b>Paid: </b>{formatINR(totals.otherPaid)}</div>
            <div className="col-sm-4"><b>Unpaid: </b>{formatINR(totals.otherUnpaid)}</div>
          </div>
        </div>
      )}
    </div>
  );
}

const Info = ({ label, value }: { label: string; value: string }) => (
  <div className="d-flex justify-content-between border-bottom py-2"><span className="text-muted">{label}</span><span className="fw-semibold">{value}</span></div>
);

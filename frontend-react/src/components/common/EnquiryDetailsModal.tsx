import { Modal } from 'react-bootstrap';
import type { EnquiryDetail } from '../../api/enquiryApi';

/**
 * Read-only enquiry/client detail popup — shared by Followup and Closed
 * Enquiry (showEnquiryDetailsOnModal.html / showClosedEnquiryDetailsOnModal.html).
 */
export default function EnquiryDetailsModal({
  detail,
  onClose,
}: {
  detail: EnquiryDetail | null;
  onClose: () => void;
}) {
  return (
    <Modal show={!!detail} onHide={onClose} size="lg" centered>
      <Modal.Header closeButton>
        <Modal.Title>
          <i className="fa fa-user me-2" />
          {detail?.firstName} {detail?.lastName}
          <span className="ms-3 text-muted"><i className="fa fa-phone me-1" />{detail?.mobileNo}</span>
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {detail && (
          <div className="row">
            <div className="col-sm-6">
              <Row label="Project" value={detail.projectName} />
              <Row label="Property Type" value={detail.propertyTypeDescr} />
              <Row label="Property" value={detail.propertyName} />
              <Row label="Area" value={String(detail.propertyArea ?? '')} />
              <Row label="Budget" value={String(detail.budgetMax ?? '')} />
              <Row label="Reference" value={detail.referenceDescr} />
              <Row label="Ref-Name" value={detail.referenceName} />
            </div>
            <div className="col-sm-6">
              <Row label="Landline" value={detail.landlineNo} />
              <Row label="Email" value={detail.email} />
              <Row label="City" value={detail.city} />
              <Row label="Location" value={detail.address} />
              <Row label="Occupation" value={detail.occupation} />
              <Row label="Company" value={detail.company} />
            </div>
          </div>
        )}
      </Modal.Body>
      <Modal.Footer>
        <button className="btn btn-sm btn-danger" onClick={onClose}>
          <i className="fa fa-times me-1" />Close
        </button>
      </Modal.Footer>
    </Modal>
  );
}

function Row({ label, value }: { label: string; value: string }) {
  return (
    <div className="d-flex justify-content-between border-bottom py-2">
      <span className="text-muted">{label}</span>
      <span className="fw-semibold text-end">{value || '-'}</span>
    </div>
  );
}

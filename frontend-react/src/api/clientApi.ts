import apiClient from './client';
import type { ApiStatus } from '../types';

export interface ClientRow {
  clientTranId: number;
  clientEnquiryId: number;
  ownerName: string;
  wingName: string;
  propertyTypeDescr: string;
  propertyName: string;
  propertyArea: string | number;
  floorNumber: number;
  flatNumber: number;
  bookingStatus: number; // 1 = register, 2 = booked, 3 = cancelled
}

export interface ClientListResponse extends ApiStatus {
  list: ClientRow[];
}

/** GET getAllClientsByProjectId/{projectId} */
export async function getClientsByProject(
  projectId: number | string
): Promise<ClientListResponse> {
  const { data } = await apiClient.get<ClientListResponse>(
    `getAllClientsByProjectId/${projectId}`
  );
  return data;
}

// ===================== Client details (payments) ==========================
export interface Disbursement {
  disbursementId: number;
  disbursementTitle: string;
  percentageValue: number;
  disbursementAmount: number;
  gstAmount: number;
  paidAmount: number;
  remainingAmount: number;
  prevRemainingAmount: number;
  paidDate: string;
  completionDate: string;
  sendPdfDate: string;
}
export interface OtherPayment {
  title: string;
  amount: number;
  paidDate: string;
}
export interface PropertyDetails {
  clientTranId: number;
  ownerName: string;
  mobileNo: string;
  projectName: string;
  wingName: string;
  flatNumber: number;
  propertyArea: string | number;
  agreementAmount: number;
  disbursementList: Disbursement[];
  otherPaymentList: OtherPayment[];
}

/** GET getClientDetailsById/{clientId} */
export async function getClientDetailsById(
  clientId: number | string
): Promise<ApiStatus<PropertyDetails> & { constantFilePath?: string }> {
  const { data } = await apiClient.get(`getClientDetailsById/${clientId}`);
  return data;
}

import apiClient from './client';
import type { ApiStatus } from '../types';

export interface OutstandingPaymentRow {
  clientTranId: number;
  ownerName: string;
  mobileNo: string;
  wingName: string;
  flatNumber: number;
  remainingAmount: number;
}

export interface OutstandingPaymentListResponse extends ApiStatus {
  list: OutstandingPaymentRow[];
}

export interface ClientPaymentSummary {
  ownerName: string;
  mobileNo: string;
  projectName: string;
  wingName: string;
  flatNumber: number;
  remainingAmount: number;
  agreementAmount: number;
  gstAmount: number;
  totalAmount: number;
  paidAmount: number;
}

/** GET getClientsDisbursementRemaingAmountByProjectId/{projectId} */
export async function getOutstandingByProject(
  projectId: number | string
): Promise<OutstandingPaymentListResponse> {
  const { data } = await apiClient.get<OutstandingPaymentListResponse>(
    `getClientsDisbursementRemaingAmountByProjectId/${projectId}`
  );
  return data;
}

/** GET getAllPaidAndRemainingDisbursementByClientId/{clientId} */
export async function getClientPaymentSummary(
  clientId: number | string
): Promise<ApiStatus<ClientPaymentSummary>> {
  const { data } = await apiClient.get<ApiStatus<ClientPaymentSummary>>(
    `getAllPaidAndRemainingDisbursementByClientId/${clientId}`
  );
  return data;
}

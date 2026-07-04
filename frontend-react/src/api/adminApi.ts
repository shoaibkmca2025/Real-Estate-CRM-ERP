import apiClient from './client';
import type { ApiStatus } from '../types';

export interface AdminProjectRow {
  projectTranId: number;
  projectName: string;
  projectStatus: number; // 1 Upcoming, 2 In Progress, 3 Completed
  isApproved: number; // 1 approved, 0 unapproved
  companyId: number;
}

/** POST updateProjectApprovedStatus */
export async function updateProjectApprovedStatus(
  projectTranId: number,
  isApproved: number
): Promise<ApiStatus & { statusCode?: number }> {
  const { data } = await apiClient.post<ApiStatus & { statusCode?: number }>(
    'updateProjectApprovedStatus',
    { projectTranId, isApproved }
  );
  return data;
}

export interface CompanyPayRow {
  paymentId: number;
  companyId: number;
  projectId: number;
  paymentType: number;
  paymentTypeName: string;
  startDate: string;
  endDate: string;
  paidDate: string;
  amount: number;
  discount: number;
  gst: number;
  totalAmount: number;
}

export interface CompanyPayInfo {
  companyId: number;
  companyName: string;
  companyEmail: string;
  website: string;
  landline: string;
  mobile: string;
  address: string;
  projectId?: number;
  projectName?: string;
}

/** GET getPaymentDetailsByProjectId/{projectId} */
export async function getPaymentDetailsByProjectId(
  projectId: number | string
): Promise<ApiStatus & { list: CompanyPayRow[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: CompanyPayRow[] }>(
    `getPaymentDetailsByProjectId/${projectId}`
  );
  return data;
}

/** GET getCompanyDetailsByCompanyId/{companyId} */
export async function getCompanyDetailsByCompanyId(
  companyId: number | string
): Promise<ApiStatus<CompanyPayInfo>> {
  const { data } = await apiClient.get<ApiStatus<CompanyPayInfo>>(`getCompanyDetailsByCompanyId/${companyId}`);
  return data;
}

/** POST addPaymentDetails (also used for edit when paymentId is set). */
export async function savePaymentDetails(payload: Record<string, unknown>): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addPaymentDetails', payload);
  return data;
}

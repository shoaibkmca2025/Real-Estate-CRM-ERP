import apiClient from './client';
import type { ApiStatus } from '../types';

export interface CompanyOption {
  companyId: number;
  companyName: string;
}

export interface CompanyListResponse extends ApiStatus {
  list: CompanyOption[];
}

export interface SmsCreditRow {
  insertionDate: string;
  totalCredits: number;
  totalSent: number;
  availableCredits: number;
}

export interface SmsCreditListResponse extends ApiStatus {
  list: SmsCreditRow[];
}

/** GET getAllCompanyDetails */
export async function getAllCompanyDetails(): Promise<CompanyListResponse> {
  const { data } = await apiClient.get<CompanyListResponse>('getAllCompanyDetails');
  return data;
}

/** GET getAllSmsCreditDetailsByCompanyId/{companyId} */
export async function getSmsCreditsByCompany(
  companyId: number | string
): Promise<SmsCreditListResponse> {
  const { data } = await apiClient.get<SmsCreditListResponse>(
    `getAllSmsCreditDetailsByCompanyId/${companyId}`
  );
  return data;
}

/** POST addSmsCreditDetails */
export async function addSmsCredits(payload: {
  companyId: number;
  totalCredits: number;
  availableCredits: number;
}): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addSmsCreditDetails', payload);
  return data;
}

// ===================== Send SMS screen ====================================
export interface SmsListItem {
  mobileNos: string;
  smsText: string;
  smsSendTime: string;
}
export interface TextCredit {
  availableCredits: number;
}
export interface ScheduledSms {
  eventName: string;
  eventDate: string;
  smsText: string;
  mobileNos: string;
}

/** GET getAllMobileNumbersByType/{userId}/{type}/{projectId} — comma list in message. */
export async function getMobileNumbersByType(
  userId: number | string,
  type: number,
  projectId: number
): Promise<ApiStatus & { message: string | null }> {
  const { data } = await apiClient.get<ApiStatus & { message: string | null }>(
    `getAllMobileNumbersByType/${userId}/${type}/${projectId}`
  );
  return data;
}

/** POST getAllSMSListByDate */
export async function getSmsListByDate(payload: {
  date: string;
  userId: number | string;
  companyId: number | string;
}): Promise<ApiStatus & { list: SmsListItem[]; object: TextCredit }> {
  const { data } = await apiClient.post('getAllSMSListByDate', payload);
  return data;
}

/** POST sendSMS */
export async function sendSms(payload: {
  mobileNos: string;
  smsText: string;
  companyId: number | string;
  isScheduledSMS: boolean;
  eventName?: string;
  eventDate?: string;
  messageCost: number;
}): Promise<ApiStatus & { object: TextCredit }> {
  const { data } = await apiClient.post('sendSMS', payload);
  return data;
}

/** GET getAllScheduledSMSListByCompanyId/{companyId} */
export async function getScheduledSms(
  companyId: number | string
): Promise<ApiStatus & { list: ScheduledSms[] }> {
  const { data } = await apiClient.get(`getAllScheduledSMSListByCompanyId/${companyId}`);
  return data;
}

import apiClient from './client';
import type { ApiStatus } from '../types';

export interface Company {
  companyId?: number;
  companyName: string;
  companyEmail: string;
  website?: string;
  mobile: string;
  landline?: string;
  address: string;
  marketedBy?: string;
  marketedByWebsite?: string;
  startDate?: string;
  endDate?: string;
  resultDate?: string;
}

export interface CompanyListResponse extends ApiStatus {
  list: Company[];
}

/** GET getAllCompanyDetails (full rows). */
export async function getAllCompanies(): Promise<CompanyListResponse> {
  const { data } = await apiClient.get<CompanyListResponse>('getAllCompanyDetails');
  return data;
}

/** GET getCompanyDetailsByCompanyId/{companyId} */
export async function getCompanyById(companyId: number): Promise<ApiStatus<Company>> {
  const { data } = await apiClient.get<ApiStatus<Company>>(`getCompanyDetailsByCompanyId/${companyId}`);
  return data;
}

/** POST addCompanyDetails */
export async function addCompany(company: Company): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addCompanyDetails', company);
  return data;
}

/** PUT updateCompanyByCompanyId */
export async function updateCompany(company: Company): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>('updateCompanyByCompanyId', company);
  return data;
}

/** Normalise a website value the way the legacy controller did (prefix http://). */
export function normaliseWebsite(url?: string): string | undefined {
  if (!url) return url;
  if (url.split('//')[0] === 'http:') return url;
  return `http://${url}`;
}

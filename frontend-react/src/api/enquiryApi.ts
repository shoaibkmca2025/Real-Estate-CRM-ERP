import apiClient from './client';
import type { ApiStatus } from '../types';

/** Project as returned by getInprogressAndCompletedProjects / getAllProjectDetails. */
export interface ProjectFull {
  projectTranId: number;
  projectId: number;
  projectName: string;
  projectStatus: number; // 1 = pending, 2 = ongoing, 3 = completed
  isApproved?: number;
  projectStatusName?: string;
}

export interface ProjectFullListResponse extends ApiStatus {
  list: ProjectFull[];
}

/** Enquiry / client detail object shown in the "view details" modal. */
export interface EnquiryDetail {
  firstName: string;
  lastName: string;
  mobileNo: string;
  projectName: string;
  propertyTypeDescr: string;
  propertyName: string;
  propertyArea: string | number;
  budgetMax: string | number;
  referenceDescr: string;
  referenceName: string;
  landlineNo: string;
  email: string;
  city: string;
  address: string;
  occupation: string;
  company: string;
}

export interface FollowupRow {
  enquiryId: number;
  firstName: string;
  lastName: string;
  mobileNo: string;
  followupDescr: string;
  followupDate: string;
  followupFlag: number; // 1 = closed (struck through)
}

export interface ClosedEnquiryRow {
  enquiryId: number;
  fullName: string;
  mobileNo: string;
  email: string;
  reason: string;
}

/** GET getInprogressAndCompletedProjects/{userType}/{companyId}/{userId} */
export async function getInprogressAndCompletedProjects(
  userType: number | string,
  companyId: number | string,
  userId: number | string
): Promise<ProjectFullListResponse> {
  const { data } = await apiClient.get<ProjectFullListResponse>(
    `getInprogressAndCompletedProjects/${userType}/${companyId}/${userId}`
  );
  return data;
}

/** GET getAllProjectDetails/{userType}/{userId}/{companyId}/{status} (full rows). */
export async function getAllProjectDetailsFull(
  userType: number | string,
  userId: number | string,
  companyId: number | string,
  status = 0
): Promise<ProjectFullListResponse> {
  const { data } = await apiClient.get<ProjectFullListResponse>(
    `getAllProjectDetails/${userType}/${userId}/${companyId}/${status}`
  );
  return data;
}

/** GET getAllFollowupDetailsByProjectId/{projectId} */
export async function getFollowupsByProject(
  projectId: number | string
): Promise<ApiStatus & { list: FollowupRow[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: FollowupRow[] }>(
    `getAllFollowupDetailsByProjectId/${projectId}`
  );
  return data;
}

/** GET getAllClosedEnquiryByProjectId/{projectId}/3/{companyId} */
export async function getClosedEnquiriesByProject(
  projectId: number | string,
  companyId: number | string
): Promise<ApiStatus & { list: ClosedEnquiryRow[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: ClosedEnquiryRow[] }>(
    `getAllClosedEnquiryByProjectId/${projectId}/3/${companyId}`
  );
  return data;
}

/** GET getEnquiryDetailsByEnquiryId/{enquiryId} */
export async function getEnquiryDetail(
  enquiryId: number | string
): Promise<ApiStatus<EnquiryDetail>> {
  const { data } = await apiClient.get<ApiStatus<EnquiryDetail>>(
    `getEnquiryDetailsByEnquiryId/${enquiryId}`
  );
  return data;
}

/** GET genarateClosedEnquiryExcelReport/{projectId}/3/{companyId} (binary download). */
export async function downloadClosedEnquiryExcel(
  projectId: number | string,
  companyId: number | string
): Promise<Blob> {
  const res = await apiClient.get(`genarateClosedEnquiryExcelReport/${projectId}/3/${companyId}`, {
    responseType: 'blob',
  });
  return res.data as Blob;
}

// ===================== Enquiry list + add/edit =============================
export interface EnquiryListRow {
  enquiryId: number;
  firstName: string;
  lastName: string;
  mobileNo: string;
  email: string;
  propertyName: string;
  budgetMax: string | number;
  remark: string;
}

/** Full enquiry form payload (mirrors the legacy enquiryListByEnqId object). */
export interface EnquiryForm {
  enquiryId?: number;
  projectId?: number | string;
  propertyType?: number | string;
  property?: number | string;
  propertyArea?: string | number | { propertyArea: string | number };
  budgetMax?: string | number;
  reference?: number | string;
  referenceName?: string;
  firstName?: string;
  lastName?: string;
  mobileNo?: string;
  landlineNo?: string;
  email?: string;
  city?: string;
  address?: string;
  occupation?: string;
  company?: string;
  userId?: number | string;
  userType?: number | string;
  companyId?: number | string;
}

export interface NamedOption {
  [key: string]: unknown;
}

/** GET getEnquiryListByProject/{projectId} */
export async function getEnquiryListByProject(
  projectId: number | string
): Promise<ApiStatus & { list: EnquiryListRow[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: EnquiryListRow[] }>(
    `getEnquiryListByProject/${projectId}`
  );
  return data;
}

/** GET getAllPropertyTypeDetails */
export async function getAllPropertyTypes(): Promise<ApiStatus & { list: { propertyTypeId: number; propertyTypeDescr: string }[] }> {
  const { data } = await apiClient.get('getAllPropertyTypeDetails');
  return data;
}

/** GET getPropertyByPropertyTypeId/{propertyTypeId} */
export async function getPropertyByType(propertyTypeId: number | string): Promise<ApiStatus & { list: { propertyId: number; propertyName: string }[] }> {
  const { data } = await apiClient.get(`getPropertyByPropertyTypeId/${propertyTypeId}`);
  return data;
}

/** GET getPropertyArea/{projectId}/{propertyTypeId}/{propertyId} */
export async function getPropertyArea(
  projectId: number | string,
  propertyTypeId: number | string,
  propertyId: number | string
): Promise<ApiStatus & { list: { propertyArea: string | number }[] }> {
  const { data } = await apiClient.get(`getPropertyArea/${projectId}/${propertyTypeId}/${propertyId}`);
  return data;
}

/** GET getAllReferenceDetails */
export async function getAllReferences(): Promise<ApiStatus & { list: { referenceId: number; referenceTypeDescr: string }[] }> {
  const { data } = await apiClient.get('getAllReferenceDetails');
  return data;
}

/** GET getEnquiryDetailsByEnquiryId/{enquiryId} → full form object. */
export async function getEnquiryFormById(enquiryId: number | string): Promise<ApiStatus<EnquiryForm>> {
  const { data } = await apiClient.get<ApiStatus<EnquiryForm>>(`getEnquiryDetailsByEnquiryId/${enquiryId}`);
  return data;
}

/** POST addEnquiryDetailsWS */
export async function addEnquiry(payload: EnquiryForm): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addEnquiryDetailsWS', payload);
  return data;
}

/** PUT updateEnquiryDetailsWS */
export async function updateEnquiry(payload: EnquiryForm): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>('updateEnquiryDetailsWS', payload);
  return data;
}

/** DELETE deleteEnquiryDetails/{enquiryId}/{projectId}/{userId} */
export async function deleteEnquiry(
  enquiryId: number,
  projectId: number,
  userId: number | string
): Promise<ApiStatus> {
  const { data } = await apiClient.delete<ApiStatus>(`deleteEnquiryDetails/${enquiryId}/${projectId}/${userId}`);
  return data;
}

/** POST closedEnquiry/{projectId} */
export async function closeEnquiry(
  projectId: number,
  payload: { enquiryId: number; reason: string; userId: number | string; userType: number | string }
): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>(`closedEnquiry/${projectId}`, payload);
  return data;
}

/** POST addRemark */
export async function addRemark(payload: {
  enquiryId: number;
  remark: string;
  userId: number | string;
  projectId: number;
}): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addRemark', payload);
  return data;
}

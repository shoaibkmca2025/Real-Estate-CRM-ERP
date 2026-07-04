import apiClient from './client';
import type { ApiStatus } from '../types';

/** A project option as returned by getAllProjectDetails. */
export interface ProjectOption {
  projectTranId: number;
  projectName: string;
}

export interface ProjectListResponse extends ApiStatus {
  list: ProjectOption[];
}

/**
 * GET getAllProjectDetails/{userType}/{userId}/{companyId}/{projectStatus}
 * projectStatus 2 = ongoing.
 */
export async function getAllProjectDetails(
  userType: number | string,
  userId: number | string,
  companyId: number | string,
  projectStatus = 2
): Promise<ProjectListResponse> {
  const { data } = await apiClient.get<ProjectListResponse>(
    `getAllProjectDetails/${userType}/${userId}/${companyId}/${projectStatus}`
  );
  return data;
}

// --- Project dashboard (drill-down) --------------------------------------
export interface ProjectMonthRow {
  monthName: string;
  totalBooked: number;
  totalUnbooked: number;
  totalEnquiry: number;
  closedEnquiry: number;
  response: number;
}

export interface ProjectDashboardData {
  dashboardProjectDetails: ProjectMonthRow[];
  lineChartLabels: string[];
  lineChartData: number[][];
  pieChartLabels: string[];
  pieChartEnquiryData: number[];
  pieChartClientData: number[];
  donutChartAreaDetails: string[];
  donutChartAreaCounts: number[];
}

/** GET getProjectDashboardDetails/{projectId} */
export async function getProjectDashboardDetails(
  projectId: number | string
): Promise<ApiStatus<ProjectDashboardData>> {
  const { data } = await apiClient.get<ApiStatus<ProjectDashboardData>>(
    `getProjectDashboardDetails/${projectId}`
  );
  return data;
}

// ===================== Project list (view all projects) ===================
export interface ProjectListRow {
  projectTranId: number;
  projectName: string;
  startDate: string;
  expectedCompletionDate: string;
  projectStatus: number;
  isApproved: number;
}

/** GET getProjectStatusList → { statusList: { "1": "Upcoming", ... } } */
export async function getProjectStatusList(): Promise<ApiStatus & { statusList: Record<string, string> }> {
  const { data } = await apiClient.get<ApiStatus & { statusList: Record<string, string> }>('getProjectStatusList');
  return data;
}

/** GET getProjectDetailsByStatus/{status}/{userType}/{userId}/{companyId} */
export async function getProjectsByStatus(
  status: number | string,
  userType: number | string,
  userId: number | string,
  companyId: number | string
): Promise<ApiStatus & { list: ProjectListRow[]; constantFilePath: string }> {
  const { data } = await apiClient.get(`getProjectDetailsByStatus/${status}/${userType}/${userId}/${companyId}`);
  return data;
}

/** POST updateProjectStatus */
export async function updateProjectStatus(payload: {
  projectTranId: number;
  projectStatus: number;
  userId: number | string;
}): Promise<ApiStatus & { statusCode?: number }> {
  const { data } = await apiClient.post('updateProjectStatus', payload);
  return data;
}

/** DELETE deleteProjectDetails/{projectId}/{userId} */
export async function deleteProject(
  projectId: number,
  userId: number | string
): Promise<ApiStatus & { statusCode?: number }> {
  const { data } = await apiClient.delete(`deleteProjectDetails/${projectId}/${userId}`);
  return data;
}

// ===================== Project details (full view) ========================
export interface BankDetail {
  bankType: number; // 1 = approved bank, else disbursement bank
  bankName: string;
  branch?: string;
  accountNumber?: string;
  ifscCode?: string;
}
export interface ProjectDisbursement {
  projectDisbursementId: number;
  disbursementTitle: string;
  percentageValue: number;
  completionDate: string;
}
export interface WingInfo {
  wingId: number;
  wingName: string;
  totalFloors?: number;
  totalFlats?: number;
}
export interface ProjectDetails {
  projectTranId: number;
  projectName: string;
  address: string;
  mahareraNo: string;
  projectStatus: number;
  startDate?: string;
  expectedCompletionDate?: string;
  bankDetailsList: BankDetail[];
  disbursementList: ProjectDisbursement[];
  wingList?: WingInfo[];
  amenitiesList?: { amenitiesName: string }[];
}

/** GET getProjectDetails/{projectId} */
export async function getProjectDetails(
  projectId: number | string
): Promise<ApiStatus<ProjectDetails> & { result?: boolean }> {
  const { data } = await apiClient.get(`getProjectDetails/${projectId}`);
  return data;
}

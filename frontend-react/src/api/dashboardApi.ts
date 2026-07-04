import apiClient from './client';
import type { ApiStatus } from '../types';

export interface DashboardProjectRow {
  projectId: number;
  projectName: string;
  totalUnits: number;
  totalBooked: number;
  totalUnbooked: number;
  totalEnquiry: number;
  closedEnquiry: number;
  response: number;
  projectCompletion: number;
}

export interface DashboardData {
  dashboardProjectDetails: DashboardProjectRow[];
  barChartLabels: string[];
  barChartData: number[][];
  lineChartLabels: string[];
  lineChartSeries: string[];
  lineChartData: number[][];
  pieChartLabels: string[];
  pieChartEnquiryData: number[];
  pieChartClientData: number[];
  donutChartLabels: string[];
  donutChartEnquiryData: number[];
}

/**
 * GET getAllDashboadDetails/{companyId}/{userType}/{userId}/{projectStatus}
 * projectStatus: 2 = Ongoing, 3 = Completed.
 */
export async function getDashboardDetails(
  companyId: number | string,
  userType: number | string,
  userId: number | string,
  projectStatus: number
): Promise<ApiStatus<DashboardData>> {
  const { data } = await apiClient.get<ApiStatus<DashboardData>>(
    `getAllDashboadDetails/${companyId}/${userType}/${userId}/${projectStatus}`
  );
  return data;
}

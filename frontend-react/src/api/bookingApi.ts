import apiClient from './client';
import type { ApiStatus } from '../types';

/** A flat cell within a wing's structure. */
export interface FlatCell {
  wingTranId: number;
  wingName: string;
  floorNumber: number;
  floorName: string;
  flatNumber: number;
  propertyName?: string;
  propertyArea?: string | number;
  bookingStatus: number; // 0 available, 1 register, 2 booked, 3 cancelled
  clientTranId?: number;
}

/** GET getProjectStructureByProjectId/{projectId} */
export async function getProjectStructure(
  projectId: number | string
): Promise<ApiStatus & { list: FlatCell[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: FlatCell[] }>(
    `getProjectStructureByProjectId/${projectId}`
  );
  return data;
}

/** GET getBookedPropertyDetails/{projectId}/{wingId}/{floorNumber}/{flatNumber} */
export async function getBookedPropertyDetails(
  projectId: number | string,
  wingId: number,
  floorNumber: number,
  flatNumber: number
): Promise<ApiStatus<Record<string, unknown>>> {
  const { data } = await apiClient.get(
    `getBookedPropertyDetails/${projectId}/${wingId}/${floorNumber}/${flatNumber}`
  );
  return data;
}

/** POST addProjectDetails (multipart in legacy; here JSON for the core fields). */
export async function addProjectDetails(payload: Record<string, unknown>): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addProjectDetails', payload);
  return data;
}

import apiClient from './client';
import type { ApiStatus } from '../types';

export interface ProfileDetails {
  userId: number;
  userName: string;
  userEmail: string;
  userMobile: string;
  companyName: string;
  companyEmail: string;
  companyMobile: string;
  website: string;
  landline: string;
  address: string;
  logoPath: string;
}

export interface UserRow {
  userId: number;
  userName: string;
  userEmail: string;
  userMobile: string;
  userType: number; // 2 admin, 3 subuser
  userStatus: number; // 1 active, 0 inactive
}

// --- Profile -------------------------------------------------------------
/** GET getUserDetailsByUserId/{userId} */
export async function getUserDetails(userId: number | string): Promise<ApiStatus<ProfileDetails>> {
  const { data } = await apiClient.get<ApiStatus<ProfileDetails>>(`getUserDetailsByUserId/${userId}`);
  return data;
}

/** PUT updateUserProfileDetailsByUserId */
export async function updateUserProfile(payload: {
  userId: number;
  userName: string;
  userEmail: string;
  userMobile: string;
}): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>('updateUserProfileDetailsByUserId', payload);
  return data;
}

/** POST updateCompanyProfileDetailsByCompanyId (multipart: profileDetails JSON + file). */
export async function updateCompanyProfile(
  profile: Record<string, unknown>,
  file?: File | null
): Promise<ApiStatus & { constantFilePath?: string }> {
  const formData = new FormData();
  formData.append('profileDetails', JSON.stringify(profile));
  if (file) formData.append('file', file);
  const { data } = await apiClient.post('updateCompanyProfileDetailsByCompanyId', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return data;
}

// --- Users management ----------------------------------------------------
/** GET getAllUserDetailsByCompanyId/{companyId} (or all users for the company). */
export async function getAllUsers(companyId: number | string): Promise<ApiStatus & { list: UserRow[] }> {
  const { data } = await apiClient.get<ApiStatus & { list: UserRow[] }>(
    `getAllUserDetailsByCompanyId/${companyId}`
  );
  return data;
}

/** GET getUserDetailsByUserId/{userId} reused for editing a user. */
export async function getUserById(userId: number): Promise<ApiStatus<UserRow>> {
  const { data } = await apiClient.get<ApiStatus<UserRow>>(`getUserDetailsByUserId/${userId}`);
  return data;
}

/** POST addUserDetails */
export async function addUser(payload: Record<string, unknown>): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('addUserDetails', payload);
  return data;
}

/** PUT updateUserDetails */
export async function updateUser(payload: Record<string, unknown>): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>('updateUserDetails', payload);
  return data;
}

/** PUT updateUserStatus/{userId}/{status} */
export async function updateUserStatus(userId: number, status: number): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>(`updateUserStatus/${userId}/${status}`, {});
  return data;
}

import apiClient from './client';
import type { ApiStatus, User } from '../types';

export interface LoginRequest {
  newEmailOrMobile: string;
  userPassword: string;
}

export interface LoginDetailsPayload {
  latitude?: string | number;
  longitude?: string | number;
  userLocation?: string;
  userDeviceName?: string;
  userIpAddress?: string;
  userId: number;
}

/** POST loginWebService — returns the Status envelope with the User object. */
export async function login(payload: LoginRequest): Promise<ApiStatus<User>> {
  const { data } = await apiClient.post<ApiStatus<User>>('loginWebService', payload);
  return data;
}

/** POST forgotPassword — emails a new random password. */
export async function forgotPassword(newEmailOrMobile: string): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('forgotPassword', { newEmailOrMobile });
  return data;
}

/** POST changePassword. */
export async function changePassword(
  userId: number,
  userPassword: string,
  newPassword: string
): Promise<ApiStatus> {
  const { data } = await apiClient.post<ApiStatus>('changePassword', {
    userId,
    userPassword,
    newPassword,
  });
  return data;
}

/** POST addLoginDetails — best-effort audit log of the login. */
export async function addLoginDetails(payload: LoginDetailsPayload): Promise<void> {
  try {
    await apiClient.post('addLoginDetails', payload);
  } catch {
    /* non-blocking, mirrors the legacy best-effort behaviour */
  }
}

/** PUT logout — clears the device token server-side. */
export async function logout(userId: number): Promise<void> {
  try {
    await apiClient.put('logout', { userId });
  } catch {
    /* ignore — local session is cleared regardless */
  }
}

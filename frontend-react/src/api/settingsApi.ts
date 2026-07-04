import apiClient from './client';
import type { ApiStatus } from '../types';

export interface Settings {
  settingsId?: number;
  companyId?: number | string;
  sendersEmail: string;
  followupNotificationDuration: number;
  paymentDuedateDuration: number;
  paymentNotificationDuration: number;
}

/** GET getSettingsByCompanyId/{companyId} */
export async function getSettings(companyId: number | string): Promise<ApiStatus<Settings>> {
  const { data } = await apiClient.get<ApiStatus<Settings>>(`getSettingsByCompanyId/${companyId}`);
  return data;
}

/** PUT updateSettings */
export async function updateSettings(settings: Settings): Promise<ApiStatus> {
  const { data } = await apiClient.put<ApiStatus>('updateSettings', settings);
  return data;
}

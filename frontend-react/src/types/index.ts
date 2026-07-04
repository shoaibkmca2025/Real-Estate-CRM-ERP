// Core domain types mirrored from the legacy Java models / JSON responses.
// Extended incrementally as each screen is migrated.

/** Generic API envelope returned by the Spring controllers (`Status` model). */
export interface ApiStatus<T = unknown> {
  code: number;
  message: string;
  object?: T;
  constantFilePath?: string;
  /** JWT issued by the Spring Boot 3 backend on successful login. */
  token?: string;
}

export interface User {
  userId: number;
  userName: string;
  userEmail: string;
  userMobile?: string;
  userType: number; // 1 = super admin, 2 = admin, 3 = subuser
  userStatus?: number;
  userPassword?: string;
  newPassword?: string;
  newEmailOrMobile?: string;
  companyId?: number;
  companyName?: string;
  website?: string;
  logoPath?: string;
  isUpdated?: number;
  marketedBy?: string;
  marketedByWebsite?: string;
  builderId?: number;
}

/** The authenticated session, replacing the old scattered sessionStorage keys. */
export interface AuthSession {
  userId: number;
  userName: string;
  userEmail: string;
  userType: number;
  companyId?: number;
  companyName?: string;
  logoPath?: string;
  constantFilePath?: string;
  builderLogo?: string;
  websiteAddress?: string;
  marketedBy?: string;
}

export interface FollowupNotification {
  enquiryId: number;
  firstName: string;
  lastName: string;
  mobileNo: string;
  followupDate: string;
  updatedDatetime: string;
}

export interface PaymentNotification {
  projectId: number;
  wingId: number;
  floorNumber: number;
  floorName: string;
  flatNumber: number;
  ownerName: string;
  mobileNo: string;
  projectName: string;
  wingName: string;
  remainingAmount: number;
  title?: string;
  dueDate: string;
}

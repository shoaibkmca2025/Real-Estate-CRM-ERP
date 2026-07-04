import { createContext, useContext, useState, useCallback, type ReactNode } from 'react';
import type { AuthSession, User } from '../types';

interface AuthContextValue {
  session: AuthSession | null;
  isAuthenticated: boolean;
  /** Persist the session after a successful login (replaces the old sessionStorage spread). */
  signIn: (user: User, constantFilePath?: string) => AuthSession;
  signOut: () => void;
}

const STORAGE_KEY = 'rc_session';

function loadSession(): AuthSession | null {
  try {
    const raw = sessionStorage.getItem(STORAGE_KEY);
    return raw ? (JSON.parse(raw) as AuthSession) : null;
  } catch {
    return null;
  }
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [session, setSession] = useState<AuthSession | null>(loadSession);

  const signIn = useCallback((user: User, constantFilePath?: string): AuthSession => {
    const next: AuthSession = {
      userId: user.userId,
      userName: (user.userName || '').split(' ')[0],
      userEmail: user.userEmail,
      userType: user.userType,
      companyId: user.companyId,
      companyName: user.companyName,
      logoPath: constantFilePath && user.logoPath ? constantFilePath + user.logoPath : user.logoPath,
      constantFilePath,
      builderLogo: user.logoPath,
      websiteAddress: user.website || '',
      marketedBy: user.marketedBy,
    };
    sessionStorage.setItem(STORAGE_KEY, JSON.stringify(next));
    setSession(next);
    return next;
  }, []);

  const signOut = useCallback(() => {
    sessionStorage.clear();
    setSession(null);
  }, []);

  return (
    <AuthContext.Provider
      value={{ session, isAuthenticated: !!session, signIn, signOut }}
    >
      {children}
    </AuthContext.Provider>
  );
}

// eslint-disable-next-line react-refresh/only-export-components
export function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within an AuthProvider');
  return ctx;
}

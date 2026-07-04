import { useState } from 'react';
import { Outlet } from 'react-router-dom';
import TopNavbar from './TopNavbar';
import Sidebar from './Sidebar';
import ChangePasswordModal from '../modals/ChangePasswordModal';
import SettingsModal from '../modals/SettingsModal';

/**
 * Dashboard shell: fixed top navbar + fixed left sidebar + offset content area.
 * Equivalent to base.html + dashboard.html wrapping the routed <ui-view>.
 * Also hosts the shared navbar modals (change password, settings).
 */
export default function AppLayout() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [showChangePassword, setShowChangePassword] = useState(false);
  const [showSettings, setShowSettings] = useState(false);

  return (
    <div className="app-shell">
      <TopNavbar
        onToggleSidebar={() => setSidebarOpen((o) => !o)}
        onChangePassword={() => setShowChangePassword(true)}
        onSettings={() => setShowSettings(true)}
      />
      <Sidebar open={sidebarOpen} />
      <main className="app-content">
        <Outlet />
      </main>

      <ChangePasswordModal show={showChangePassword} onClose={() => setShowChangePassword(false)} />
      <SettingsModal show={showSettings} onClose={() => setShowSettings(false)} />
    </div>
  );
}

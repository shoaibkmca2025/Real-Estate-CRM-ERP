import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Dropdown } from 'react-bootstrap';
import { useAuth } from '../../context/AuthContext';
import { logout as logoutApi } from '../../api/authApi';
import type { FollowupNotification, PaymentNotification } from '../../types';

interface TopNavbarProps {
  onToggleSidebar: () => void;
  followupNotifications?: FollowupNotification[];
  paymentNotifications?: PaymentNotification[];
  demandLetterNotifications?: PaymentNotification[];
  onChangePassword?: () => void;
  onSettings?: () => void;
  onViewUsers?: () => void;
  onProjectAllocation?: () => void;
}

/**
 * Top navigation bar — mirrors the legacy navbar in dashboard.html:
 * company brand, three notification bells (followup / payment due / demand
 * letter) and the user account dropdown.
 */
export default function TopNavbar({
  onToggleSidebar,
  followupNotifications = [],
  paymentNotifications = [],
  demandLetterNotifications = [],
  onChangePassword,
  onSettings,
  onViewUsers,
  onProjectAllocation,
}: TopNavbarProps) {
  const { session, signOut } = useAuth();
  const navigate = useNavigate();
  const isAdminUser = session?.userType === 2 || session?.userType === 3;
  // Company logos may point at an unreachable legacy file server; fall back
  // to the company name if the image cannot load.
  const [logoFailed, setLogoFailed] = useState(false);

  const handleLogout = async () => {
    if (session) await logoutApi(session.userId);
    signOut();
    navigate('/login', { replace: true });
  };

  return (
    <nav className="topnav-navbar">
      <button className="topnav-icon-btn d-lg-none" onClick={onToggleSidebar} aria-label="Toggle menu">
        <i className="fa fa-bars" />
      </button>

      <a className="navbar-brand" href={session?.websiteAddress || '#'}>
        {session?.logoPath && !logoFailed ? (
          <img
            src={session.logoPath}
            alt="logo"
            style={{ height: 36 }}
            onError={() => setLogoFailed(true)}
          />
        ) : (
          <span>{session?.companyName || 'Real Estate CRM'}</span>
        )}
      </a>

      <div className="topnav-spacer" />

      <div className="topnav-actions">
        {/* Followup notifications */}
        <NotificationBell
          icon="fa-bell"
          count={followupNotifications.length}
          title="Followup Notifications"
          header={`You have ${followupNotifications.length} new followup notifications.`}
        >
          {followupNotifications.map((n) => (
            <a key={n.enquiryId} href="#followup">
              <div className="blue">{n.firstName} {n.lastName}</div>
              <div className="msg-time"><i className="fa fa-phone" /> {n.mobileNo}</div>
              <div className="msg-time"><i className="fa fa-clock-o" /> {n.followupDate}</div>
            </a>
          ))}
        </NotificationBell>

        {/* Payment due notifications */}
        <NotificationBell
          icon="fa-bullhorn"
          count={paymentNotifications.length}
          title="Payment Due Date Notifications"
          header={`You have ${paymentNotifications.length} payment due date notifications.`}
        >
          {paymentNotifications.map((n, i) => (
            <a key={i} href="#payment">
              <div className="blue">{n.ownerName}</div>
              <div className="msg-time"><i className="fa fa-home" /> {n.projectName} ({n.wingName}-{n.flatNumber})</div>
              <div className="msg-time"><i className="fa fa-info-circle" /> Due: Rs. {n.remainingAmount}</div>
              <div className="msg-time"><i className="fa fa-clock-o" /> {n.dueDate}</div>
            </a>
          ))}
        </NotificationBell>

        {/* Pending demand letter notifications */}
        <NotificationBell
          icon="fa-envelope"
          count={demandLetterNotifications.length}
          title="Pending Demand Letter Notifications"
          header={`You have ${demandLetterNotifications.length} Pending Demand Letter Notifications.`}
        >
          {demandLetterNotifications.map((n, i) => (
            <a key={i} href="#demand">
              <div className="blue">{n.ownerName}</div>
              <div className="msg-time"><i className="fa fa-home" /> {n.projectName} ({n.wingName}-{n.flatNumber})</div>
            </a>
          ))}
        </NotificationBell>

        {/* User account dropdown */}
        <Dropdown align="end">
          <Dropdown.Toggle as="button" className="topnav-icon-btn" style={{ width: 'auto', gap: 8 }} id="user-menu">
            <img src="/images/flat-avatar.png" className="topnav-img" alt="avatar" onError={(e) => ((e.target as HTMLImageElement).style.display = 'none')} />
            <span className="d-none d-sm-inline ms-2">{session?.userName}</span>
          </Dropdown.Toggle>
          <Dropdown.Menu>
            {isAdminUser && (
              <Dropdown.Item onClick={() => navigate('/dashboard/editUserProfile')}>
                <i className="fa fa-address-card me-2" />User Profile
              </Dropdown.Item>
            )}
            {isAdminUser && onViewUsers && (
              <Dropdown.Item onClick={onViewUsers}><i className="fa fa-users me-2" />View Users</Dropdown.Item>
            )}
            {isAdminUser && onProjectAllocation && (
              <Dropdown.Item onClick={onProjectAllocation}><i className="fa fa-building me-2" />Project Allocation</Dropdown.Item>
            )}
            {isAdminUser && onSettings && (
              <Dropdown.Item onClick={onSettings}><i className="fa fa-cogs me-2" />Settings</Dropdown.Item>
            )}
            {onChangePassword && (
              <Dropdown.Item onClick={onChangePassword}><i className="fa fa-key me-2" />Change Password</Dropdown.Item>
            )}
            <Dropdown.Divider />
            <Dropdown.Item onClick={handleLogout}><i className="fa fa-power-off me-2" />Logout</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </div>
    </nav>
  );
}

/** A single notification bell with a badge and a dropdown list. */
function NotificationBell({
  icon,
  count,
  title,
  header,
  children,
}: {
  icon: string;
  count: number;
  title: string;
  header: string;
  children: React.ReactNode;
}) {
  return (
    <Dropdown align="end">
      <Dropdown.Toggle as="button" className="topnav-icon-btn" title={title} id={`notify-${icon}`}>
        <i className={`fa ${icon}`} />
        {count > 0 && <span className="badge-red">{count}</span>}
      </Dropdown.Toggle>
      <Dropdown.Menu style={{ width: 320, maxHeight: 380, overflowY: 'auto', padding: 0 }}>
        <div className="notification-header text-center">
          <i className="fa fa-exclamation-triangle me-1" />
          {header}
        </div>
        <div className="dropdown-messages">
          {count === 0 ? (
            <div className="text-center text-muted p-3">No notifications</div>
          ) : (
            children
          )}
        </div>
      </Dropdown.Menu>
    </Dropdown>
  );
}

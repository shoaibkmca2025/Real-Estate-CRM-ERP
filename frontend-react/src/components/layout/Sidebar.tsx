import { Link, useLocation } from 'react-router-dom';

/**
 * Left navigation — same items, order and icons as the legacy
 * `<ul class="nav nav-sidebar">` in dashboard.html. The `active` class is
 * applied on the <li> to match the original highlight behaviour ($state.includes).
 */
interface MenuItem {
  to: string;
  icon: string;
  label: string;
  /** route prefixes that should also activate this item (mirrors $state.includes) */
  match: string[];
}

const MENU: MenuItem[] = [
  { to: '/dashboard/mainDashboard', icon: 'fa-home', label: 'Dashboard', match: ['/dashboard/mainDashboard', '/dashboard/projectDashboard'] },
  { to: '/dashboard/projectRegistration', icon: 'fa-edit', label: 'Registration', match: ['/dashboard/projectRegistration'] },
  { to: '/dashboard/viewProjects', icon: 'fa-building', label: 'Projects', match: ['/dashboard/viewProjects', '/dashboard/viewProjectDetails'] },
  { to: '/dashboard/enquiry', icon: 'fa-book', label: 'Enquiry Book', match: ['/dashboard/enquiry', '/dashboard/addEnquiry', '/dashboard/closedEnquiry'] },
  { to: '/dashboard/followup', icon: 'fa-comments', label: 'Follow Up', match: ['/dashboard/followup'] },
  { to: '/dashboard/viewClients', icon: 'fa-users', label: 'Clients', match: ['/dashboard/viewClients', '/dashboard/showClientDetails'] },
  { to: '/dashboard/bookingDetails', icon: 'fa-building', label: 'Bookings', match: ['/dashboard/bookingDetails', '/dashboard/showBookedPropertyDetails'] },
  { to: '/dashboard/sms', icon: 'fa-envelope', label: 'SMS', match: ['/dashboard/sms'] },
];

export default function Sidebar({ open }: { open: boolean }) {
  const { pathname } = useLocation();
  return (
    <aside className={`app-sidebar${open ? ' open' : ''}`}>
      <ul className="nav-sidebar">
        {MENU.map((item) => {
          const active = item.match.some((m) => pathname.startsWith(m));
          return (
            <li key={item.to} className={active ? 'active' : ''}>
              <Link to={item.to}>
                <i className={`menu-icon fa ${item.icon}`} />
                <span>{item.label}</span>
              </Link>
            </li>
          );
        })}
      </ul>
    </aside>
  );
}

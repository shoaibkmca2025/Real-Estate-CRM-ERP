import { Routes, Route, Navigate } from 'react-router-dom';
import ProtectedRoute from './components/common/ProtectedRoute';
import AppLayout from './components/layout/AppLayout';
import Login from './pages/Login';
import Dashboard from './pages/dashboard/Dashboard';
import ProjectDashboard from './pages/dashboard/ProjectDashboard';
import OutstandingPayments from './pages/reports/OutstandingPayments';
import SmsCreditDetails from './pages/admin/SmsCreditDetails';
import UserPaymentDetails from './pages/admin/UserPaymentDetails';
import CompanyDetails from './pages/admin/CompanyDetails';
import ViewUsers from './pages/admin/ViewUsers';
import ProjectwisePaymentDetails from './pages/admin/ProjectwisePaymentDetails';
import UserProfile from './pages/profile/UserProfile';
import Enquiry from './pages/enquiry/Enquiry';
import AddEnquiry from './pages/enquiry/AddEnquiry';
import Followup from './pages/enquiry/Followup';
import ClosedEnquiry from './pages/enquiry/ClosedEnquiry';
import ViewClients from './pages/clients/ViewClients';
import ClientDetails from './pages/clients/ClientDetails';
import Sms from './pages/sms/Sms';
import ViewProjectList from './pages/projects/ViewProjectList';
import ViewProjectDetails from './pages/projects/ViewProjectDetails';
import ProjectRegistration from './pages/projects/ProjectRegistration';
import Bookings from './pages/bookings/Bookings';
import BookedPropertyDetails from './pages/bookings/BookedPropertyDetails';

/**
 * Application routes — a 1:1 map of the legacy ui-router `$stateProvider`
 * states (scripts.js). Every screen has been migrated to React.
 */
export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />

      {/* Authenticated area — shares the navbar + sidebar shell */}
      <Route element={<ProtectedRoute />}>
        <Route element={<AppLayout />}>
          {/* Dashboard module */}
          <Route path="/dashboard/mainDashboard" element={<Dashboard />} />
          <Route path="/dashboard/projectDashboard" element={<ProjectDashboard />} />
          <Route path="/dashboard/projectRegistration" element={<ProjectRegistration />} />
          <Route path="/dashboard/viewProjects" element={<ViewProjectList />} />
          <Route path="/dashboard/viewProjectDetails" element={<ViewProjectDetails />} />
          <Route path="/dashboard/enquiry" element={<Enquiry />} />
          <Route path="/dashboard/addEnquiry" element={<AddEnquiry />} />
          <Route path="/dashboard/followup" element={<Followup />} />
          <Route path="/dashboard/closedEnquiry" element={<ClosedEnquiry />} />
          <Route path="/dashboard/viewClients" element={<ViewClients />} />
          <Route path="/dashboard/showClientDetails" element={<ClientDetails />} />
          <Route path="/dashboard/bookingDetails" element={<Bookings />} />
          <Route path="/dashboard/showBookedPropertyDetails" element={<BookedPropertyDetails />} />
          <Route path="/dashboard/sms" element={<Sms />} />
          <Route path="/dashboard/outstandingPayments" element={<OutstandingPayments />} />
          <Route path="/dashboard/editUserProfile" element={<UserProfile />} />

          {/* Admin module */}
          <Route path="/admindashboard/viewUsers" element={<ViewUsers />} />
          <Route path="/admindashboard/viewCompanyDetails" element={<CompanyDetails />} />
          <Route path="/admindashboard/userPaymentDetails" element={<UserPaymentDetails />} />
          <Route path="/admindashboard/projectwisePaymentDetails" element={<ProjectwisePaymentDetails />} />
          <Route path="/admindashboard/smsCreditDetails" element={<SmsCreditDetails />} />

          {/* Base-level */}
          <Route path="/userProfile" element={<UserProfile />} />
        </Route>
      </Route>

      {/* Default + fallback (otherwise -> /login, like $urlRouterProvider) */}
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
}

import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Bar, Line, Pie, Doughnut } from 'react-chartjs-2';
import { Form } from 'react-bootstrap';
import { useAuth } from '../../context/AuthContext';
import { getDashboardDetails, type DashboardData } from '../../api/dashboardApi';
import { CHART_COLORS, PIE_COLORS } from '../../utils/chartSetup';
import Loader from '../../components/common/Loader';

interface InfoBox {
  label: string;
  value: number;
  icon: string;
  bg: string;
}

/**
 * Main Dashboard — mirrors mainDashboard.html / mainDashboardCtrl.js:
 * Ongoing/Completed toggle, 5 summary info-boxes, the project-wise table with
 * completion progress bars, and the bar/line/pie/pie/doughnut charts.
 */
export default function Dashboard() {
  const { session } = useAuth();
  const navigate = useNavigate();

  const [ongoing, setOngoing] = useState(true); // true => status 2, false => status 3
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(false);
  const [data, setData] = useState<DashboardData | null>(null);

  useEffect(() => {
    if (!session) return;
    let cancelled = false;
    setLoading(true);
    setError(false);
    getDashboardDetails(session.companyId ?? '', session.userType, session.userId, ongoing ? 2 : 3)
      .then((res) => {
        if (cancelled) return;
        if (res.code === 200 && res.object) setData(res.object);
        else setError(true);
      })
      .catch(() => !cancelled && setError(true))
      .finally(() => !cancelled && setLoading(false));
    return () => {
      cancelled = true;
    };
  }, [session, ongoing]);

  const rows = data?.dashboardProjectDetails ?? [];

  const totals = useMemo(() => {
    return rows.reduce(
      (acc, r) => ({
        totalUnits: acc.totalUnits + (r.totalUnits || 0),
        booked: acc.booked + (r.totalBooked || 0),
        unbooked: acc.unbooked + (r.totalUnbooked || 0),
        totalEnquiry: acc.totalEnquiry + (r.totalEnquiry || 0),
        closedEnquiry: acc.closedEnquiry + (r.closedEnquiry || 0),
      }),
      { totalUnits: 0, booked: 0, unbooked: 0, totalEnquiry: 0, closedEnquiry: 0 }
    );
  }, [rows]);

  const infoBoxes: InfoBox[] = [
    { label: 'TOTAL UNITS', value: totals.totalUnits, icon: 'fa-layer-group', bg: 'bg-cyan' },
    { label: 'BOOKED', value: totals.booked, icon: 'fa-circle-check', bg: 'bg-pink' },
    { label: 'UNBOOKED', value: totals.unbooked, icon: 'fa-clipboard-list', bg: 'bg-light-green' },
    { label: 'TOTAL ENQUIRY', value: totals.totalEnquiry, icon: 'fa-user-plus', bg: 'bg-orange' },
    { label: 'CLOSED ENQUIRY', value: totals.closedEnquiry, icon: 'fa-ban', bg: 'bg-yellow-green' },
  ];

  const drillDown = (projectId: number, projectName: string, totalUnits: number) => {
    sessionStorage.setItem('rc_project', JSON.stringify({ projectId, projectName, totalUnits }));
    navigate('/dashboard/projectDashboard');
  };

  const barData = {
    labels: data?.barChartLabels ?? [],
    datasets: ['Total Units', 'Booked', 'Total Enquiry'].map((label, i) => ({
      label,
      data: (data?.barChartData ?? []).map((row) => row[i]),
      backgroundColor: CHART_COLORS[i],
    })),
  };

  const lineData = {
    labels: data?.lineChartLabels ?? [],
    datasets: (data?.lineChartSeries ?? []).map((label, i) => ({
      label,
      data: (data?.lineChartData ?? [])[i] ?? [],
      borderColor: CHART_COLORS[i % CHART_COLORS.length],
      backgroundColor: CHART_COLORS[i % CHART_COLORS.length],
      tension: 0.3,
    })),
  };

  const enquiryPie = {
    labels: data?.pieChartLabels ?? [],
    datasets: [{ data: data?.pieChartEnquiryData ?? [], backgroundColor: PIE_COLORS }],
  };
  const clientPie = {
    labels: data?.pieChartLabels ?? [],
    datasets: [{ data: data?.pieChartClientData ?? [], backgroundColor: PIE_COLORS }],
  };
  const donut = {
    labels: data?.donutChartLabels ?? [],
    datasets: [{ data: data?.donutChartEnquiryData ?? [], backgroundColor: PIE_COLORS }],
  };

  if (error) {
    return (
      <div className="rc-card text-center">
        <h4>Oops! Your request could not complete</h4>
        <p className="text-muted">Please check your internet connection or try again later.</p>
        <button className="btn btn-info btn-sm" onClick={() => setOngoing((v) => v)}>
          <i className="fa fa-rotate-right me-2" />Refresh
        </button>
      </div>
    );
  }

  return (
    <div>
      <Loader show={loading} />

      <div className="d-flex justify-content-between align-items-center mb-3">
        <h4 style={{ color: '#2563eb' }}>
          <i className="fa fa-list me-2" />DASHBOARD ({ongoing ? 'Ongoing' : 'Completed'} Projects)
        </h4>
        <Form.Check
          type="switch"
          id="dashboard-switch"
          label={ongoing ? 'Ongoing' : 'Completed'}
          checked={ongoing}
          onChange={(e) => setOngoing(e.target.checked)}
        />
      </div>

      {/* Info boxes */}
      <div className="row g-3 mb-4">
        {infoBoxes.map((box) => (
          <div className="col-6 col-md" key={box.label}>
            <div className={`info-box ${box.bg}`}>
              <div className="info-box-icon"><i className={`fa ${box.icon}`} /></div>
              <div className="info-box-content">
                <div className="info-box-text">{box.label}</div>
                <div className="info-box-number">{box.value}</div>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Project-wise details table */}
      <div className="rc-card">
        <div className="table-responsive">
          <table className="table table-condensed table-bordered align-middle mb-0">
            <thead>
              <tr style={{ background: '#2563eb', color: '#fff' }}>
                <th className="text-center">Project Name</th>
                <th className="text-center">Total Units</th>
                <th className="text-center">Booked</th>
                <th className="text-center">Unbooked</th>
                <th className="text-center">Total Enquiry</th>
                <th className="text-center">Closed Enquiry</th>
                <th className="text-center">Response</th>
                <th className="text-center">Project Status</th>
              </tr>
            </thead>
            <tbody style={{ fontWeight: 600, color: '#2563eb' }}>
              {rows.length === 0 ? (
                <tr><td colSpan={8} className="text-center">No data available in table</td></tr>
              ) : (
                rows.map((p) => (
                  <tr key={p.projectId}>
                    <td className="text-center">
                      <a className="blue" role="button" onClick={() => drillDown(p.projectId, p.projectName, p.totalUnits)}>
                        {p.projectName}
                      </a>
                    </td>
                    <td className="text-center">{p.totalUnits}</td>
                    <td className="text-center" style={{ color: '#e44141' }}>{p.totalBooked}</td>
                    <td className="text-center" style={{ color: '#5fbb5f' }}>{p.totalUnbooked}</td>
                    <td className="text-center">{p.totalEnquiry}</td>
                    <td className="text-center">{p.closedEnquiry}</td>
                    <td className="text-center">{p.response} %</td>
                    <td className="text-center">
                      <div className="progress" style={{ height: 18 }}>
                        <div
                          className="progress-bar progress-bar-striped"
                          role="progressbar"
                          style={{ width: `${p.projectCompletion}%`, background: 'var(--rc-info)' }}
                        >
                          {p.projectCompletion}%
                        </div>
                      </div>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* Charts */}
      <div className="row g-3">
        <div className="col-md-6">
          <ChartPanel title="Project Wise Details" variant="danger"><Bar data={barData} /></ChartPanel>
        </div>
        <div className="col-md-6">
          <ChartPanel title="Month Wise Total Enquiry" variant="warning"><Line data={lineData} /></ChartPanel>
        </div>
        <div className="col-md-4">
          <ChartPanel title="Reference Wise Total Enquiry" variant="info"><Pie data={enquiryPie} /></ChartPanel>
        </div>
        <div className="col-md-4">
          <ChartPanel title="Reference Wise Total Clients" variant="success"><Pie data={clientPie} /></ChartPanel>
        </div>
        <div className="col-md-4">
          <ChartPanel title="Project Wise Total Enquiry" variant="primary"><Doughnut data={donut} /></ChartPanel>
        </div>
      </div>

      <div className="text-center text-muted mt-4">
        <small>Copyright © Real Estate CRM. All rights reserved. <strong>Version</strong> 2.0</small>
      </div>
    </div>
  );
}

function ChartPanel({ title, variant, children }: { title: string; variant: string; children: React.ReactNode }) {
  return (
    <div className="rc-card h-100">
      <div className={`rc-panel-title rc-panel-${variant}`}>{title}</div>
      <div style={{ minHeight: 260 }}>{children}</div>
    </div>
  );
}

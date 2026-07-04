import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Line, Pie, Doughnut } from 'react-chartjs-2';
import { getProjectDashboardDetails, type ProjectDashboardData } from '../../api/projectApi';
import { CHART_COLORS, PIE_COLORS } from '../../utils/chartSetup';
import Loader from '../../components/common/Loader';

/**
 * Project drill-down dashboard — projectDashboard.html / projectDashboard.js.
 * Shows the selected project's monthly counts, info boxes and charts.
 */
export default function ProjectDashboard() {
  const navigate = useNavigate();
  const project = useMemo(() => {
    try {
      return JSON.parse(sessionStorage.getItem('rc_project') || 'null') as
        | { projectId: number; projectName: string; totalUnits: number }
        | null;
    } catch {
      return null;
    }
  }, []);

  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<ProjectDashboardData | null>(null);

  useEffect(() => {
    if (!project) {
      navigate('/dashboard/mainDashboard');
      return;
    }
    setLoading(true);
    getProjectDashboardDetails(project.projectId)
      .then((res) => res.code === 200 && res.object && setData(res.object))
      .catch(() => navigate('/dashboard/mainDashboard'))
      .finally(() => setLoading(false));
  }, [project, navigate]);

  const rows = data?.dashboardProjectDetails ?? [];
  const totals = useMemo(() => {
    const booked = rows.reduce((s, r) => s + (r.totalBooked || 0), 0);
    const totalEnquiry = rows.reduce((s, r) => s + (r.totalEnquiry || 0), 0);
    const closedEnquiry = rows.reduce((s, r) => s + (r.closedEnquiry || 0), 0);
    const unbooked = rows.length ? rows[rows.length - 1].totalUnbooked : 0;
    return { booked, totalEnquiry, closedEnquiry, unbooked };
  }, [rows]);

  const lineData = {
    labels: data?.lineChartLabels ?? [],
    datasets: ['Total Booked', 'Total Enquiry', 'Closed Enquiry'].map((label, i) => ({
      label,
      data: (data?.lineChartData ?? [])[i] ?? [],
      borderColor: CHART_COLORS[i],
      backgroundColor: CHART_COLORS[i],
      tension: 0.3,
    })),
  };
  const enquiryPie = { labels: data?.pieChartLabels ?? [], datasets: [{ data: data?.pieChartEnquiryData ?? [], backgroundColor: PIE_COLORS }] };
  const clientPie = { labels: data?.pieChartLabels ?? [], datasets: [{ data: data?.pieChartClientData ?? [], backgroundColor: PIE_COLORS }] };
  const areaDonut = { labels: data?.donutChartAreaDetails ?? [], datasets: [{ data: data?.donutChartAreaCounts ?? [], backgroundColor: PIE_COLORS }] };

  const boxes = [
    { label: 'TOTAL UNITS', value: project?.totalUnits ?? 0, bg: 'bg-cyan', icon: 'fa-layer-group' },
    { label: 'BOOKED', value: totals.booked, bg: 'bg-pink', icon: 'fa-circle-check' },
    { label: 'UNBOOKED', value: totals.unbooked, bg: 'bg-light-green', icon: 'fa-clipboard-list' },
    { label: 'TOTAL ENQUIRY', value: totals.totalEnquiry, bg: 'bg-orange', icon: 'fa-user-plus' },
    { label: 'CLOSED ENQUIRY', value: totals.closedEnquiry, bg: 'bg-yellow-green', icon: 'fa-ban' },
  ];

  return (
    <div>
      <Loader show={loading} />
      <h4 className="mb-3" style={{ color: '#2563eb' }}>
        <i className="fa fa-list me-2" />{project?.projectName}
      </h4>

      <div className="row g-3 mb-4">
        {boxes.map((b) => (
          <div className="col-6 col-md" key={b.label}>
            <div className={`info-box ${b.bg}`}>
              <div className="info-box-icon"><i className={`fa ${b.icon}`} /></div>
              <div className="info-box-content">
                <div className="info-box-text">{b.label}</div>
                <div className="info-box-number">{b.value}</div>
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="rc-card">
        <div className="table-responsive">
          <table className="table table-condensed table-bordered mb-0">
            <thead>
              <tr style={{ background: '#2563eb', color: '#fff' }}>
                <th className="text-center">Month</th>
                <th className="text-center">Booked</th>
                <th className="text-center">Unbooked</th>
                <th className="text-center">Total Enquiry</th>
                <th className="text-center">Closed Enquiry</th>
                <th className="text-center">Response %</th>
              </tr>
            </thead>
            <tbody style={{ fontWeight: 600, color: '#2563eb' }}>
              {rows.length === 0 ? (
                <tr><td colSpan={6} className="text-center">No data available in table</td></tr>
              ) : (
                rows.map((r, i) => (
                  <tr key={i}>
                    <td className="text-center">{r.monthName}</td>
                    <td className="text-center" style={{ color: '#e44141' }}>{r.totalBooked}</td>
                    <td className="text-center" style={{ color: '#5fbb5f' }}>{r.totalUnbooked}</td>
                    <td className="text-center">{r.totalEnquiry}</td>
                    <td className="text-center">{r.closedEnquiry}</td>
                    <td className="text-center">{r.response}%</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      <div className="rc-card">
        <div className="rc-panel-title rc-panel-success">Month Wise Details</div>
        <div style={{ minHeight: 280 }}><Line data={lineData} /></div>
      </div>

      <div className="row g-3">
        <div className="col-md-4"><div className="rc-card h-100"><div className="rc-panel-title rc-panel-warning">Reference Wise Total Enquiry</div><Pie data={enquiryPie} /></div></div>
        <div className="col-md-4"><div className="rc-card h-100"><div className="rc-panel-title rc-panel-info">Reference Wise Total Clients</div><Pie data={clientPie} /></div></div>
        <div className="col-md-4"><div className="rc-card h-100"><div className="rc-panel-title rc-panel-danger">Area Wise Total Enquiry</div><Doughnut data={areaDonut} /></div></div>
      </div>
    </div>
  );
}

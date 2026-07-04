// Registers the Chart.js pieces used across the app (react-chartjs-2 v5
// requires explicit registration). Imported once from main.tsx.
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement,
  PointElement,
  ArcElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement,
  PointElement,
  ArcElement,
  Title,
  Tooltip,
  Legend
);

/** Chart palettes copied from the legacy dashboard controller. */
export const CHART_COLORS = ['#E74C3C', '#3498DB', '#52BE80', '#F1C40F', '#884EA0', '#16A085', '#D35400'];
export const PIE_COLORS = ['#F1948A', '#58D68D', '#5DADE2', '#F4D03F', '#9B59B6', '#52BE80', '#566573'];

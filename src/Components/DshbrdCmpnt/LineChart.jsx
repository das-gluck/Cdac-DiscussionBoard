import React from 'react'
import './Dashboard.css'
import { Line } from "react-chartjs-2";
import { Chart as ChartJS, LineElement, PointElement, LinearScale, Title, Tooltip, CategoryScale, Legend } from "chart.js";

ChartJS.register(LineElement, PointElement, LinearScale, Title, Tooltip, CategoryScale, Legend);

// Sample Data: Posts done by date
const postData = {
  labels: ["Feb 1", "Feb 2", "Feb 3", "Feb 4", "Feb 5", "Feb 6", "Feb 7"],
  datasets: [
    {
      label: "Posts",
      data: [5, 8, 15, 12, 20, 25, 30], // Posts per day
      borderColor: "#3b82f6", // Blue line
      backgroundColor: "rgba(255, 255, 255, 0.2)",
      borderWidth: 2,
      pointRadius: 5,
      pointBackgroundColor: "#3b82f6",
      tension: 0.4, // Smooth curve
    },
  ],
};

// Chart Options
const options = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: { display: true, position: "top" },
    tooltip: { enabled: true },
    title:{
      display: true,
      text: 'Post Frequency',
      font: {
        size: 18,
        weight: 'bold',
      },
      padding: 10,
    }
  },
  scales: {
    x: { grid: { display: false } },
    y: { beginAtZero: true },
  },
  
};

const LineChart = () => {
  return (
    <div className='line-chart-container-child'>
        <Line data={postData} options={options} />
    </div>
  )
}

export default LineChart
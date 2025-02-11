import React from 'react'
import './Dashboard.css'
import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

// Register Chart.js components
ChartJS.register(ArcElement, Tooltip, Legend);
// Dummy Data
// const totalUsers = 1000;
// const activeUsers = 650;
// const inactiveUsers = totalUsers - activeUsers;

const PieChart = ({ totalUsers = 0, posts = [], inactiveThreshold = 2 }) => {
  const currentDate = new Date();

  // Ensure posts is always an array to avoid errors
  const safePosts = Array.isArray(posts) ? posts : [];

  // Calculate inactive users based on the threshold for days since last post
  const inactiveUsersCalculated = safePosts.filter((post) => {
    const postDate = new Date(post.createdAt);
    const diffTime = currentDate - postDate;
    const diffDays = diffTime / (1000 * 60 * 60 * 24);
    return diffDays > inactiveThreshold;
  }).length;

  // Ensure that inactive users do not exceed total users
  const inactiveUsers = Math.min(inactiveUsersCalculated, totalUsers);

  // Calculate active users
  const activeUsers = Math.max(0, totalUsers - inactiveUsers); // Ensure active users is not negative

  console.log('Total Users:', totalUsers);  // Log to check
  console.log('Inactive Users:', inactiveUsers);  // Log to check
  console.log('Active Users:', activeUsers);  // Log to check

  // The data object
  const data = {
    labels: ['Active Users', 'Inactive Users'],
    datasets: [
      {
        data: [3, 2],  // Use dynamic values here
        backgroundColor: ['#36A2EB', '#FFCE56'],
        borderColor: ['#36A2EB', '#FFCE56'],
        borderWidth: 2,
      },
    ],
  };

  const options = {
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        enabled: true,
      },
      title: {
        display: true,
        text: 'User Activity Overview',
        font: {
          size: 18,
          weight: 'bold',
        },
        padding: 10,
      },
    },
    responsive: true,
    maintainAspectRatio: false,
  };

  return (
    <div className='pie-chart-container-child'>
      <Pie data={data} options={options} />
    </div>
  );
};

export default PieChart;

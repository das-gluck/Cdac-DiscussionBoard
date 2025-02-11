import React from 'react';
import './Dashboard.css';
import LineChart from './LineChart';
import PieChart from './PieChart';
import DataBoard from './DataBoard';
import Navbar from '../HomePage/Navbar';
import MenuBar from '../HomePage/MenuBar';

const Dashboard = () => {
  return (
    <div className='dashboard-layout'>
      <Navbar />
      <div className='dashboard-content'>
        <MenuBar />
        <div className='main-dashboard'>
          <div className='data-board'>
            <DataBoard />
          </div>
           
        </div>
      </div>
    </div>
  );
};

export default Dashboard;

import React from 'react';
import './Home.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom

const MenuBar = () => {
  // Get the userRole from local storage
  const userRole = localStorage.getItem('userRole');

  return (
    <div className="navbar-jumbotron">
      <div className="menu-container">
        <Link to="/home" className="fa-solid fa-house" title="Home"></Link>
        <Link to="/add-post" className="fa-solid fa-pencil-alt" title="Add Post"></Link>
       
        <Link to="/info" className="fa-solid fa-circle-info" title="Info"></Link>
        
        {/* Conditionally render the Dashboard link if userRole is Admin */}
        {userRole === 'ROLE_ADMIN' && (
          <Link to="/dashboard" className="fa-solid fa-tachometer-alt" title="Dashboard"></Link>
        )}
        {/* Conditionally render the Dashboard link if userRole is Admin */}
        {userRole === 'ROLE_ADMIN' && (
         <Link to="/profile" className="fa-solid fa-user" title="Profile"></Link>
        )}
      </div>
    </div>
  );
};

export default MenuBar;
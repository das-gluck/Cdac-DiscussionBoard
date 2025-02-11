import React from 'react';
import "./Home.css";
import { useNavigate } from 'react-router-dom';

const DeleteButton = () => {
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const handleDelete = () => {
    // Delete the JWT token and user role from local storage
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userRole');
     
    // Navigate back to the login page
    navigate('/login');
    console.log("Log Out Successful");
  };

  return (
    <button className="delete-btn" onClick={handleDelete}>
      Log Out
    </button>
  );
};

export default DeleteButton;

import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';
 
import '@fortawesome/fontawesome-free/css/all.min.css';
import DeleteButton from './DeleteButton';

const Navbar = ({ setSearchQuery }) => {
  const navigate = useNavigate();

  const handleLogoClick = () => {
    navigate('/home'); 
  };

  const handleSearch = (e) => {
    if (setSearchQuery) { // Only call if it's defined
      setSearchQuery(e.target.value);
    }
  };

  return (
    <nav className="navbar">
      <div className="navbar-logo" onClick={handleLogoClick}></div>

      <div className="navbar-search">
        <input type="text" placeholder="Search..." onChange={handleSearch} />
        <i className="fas fa-search search-icon"></i>
      </div>

      <div className="navbar-profile">
        <div className="profile-pic"></div>
        <div className="language">English</div>
        <DeleteButton/>
      </div>
    </nav>
  );
};

export default Navbar;

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { LoginSignup } from './Components/LoginSignup/LoginSignup';
import { Login } from './Components/Login/Login';
import HomePage from './Components/HomePage/Home.jsx';
import '@fortawesome/fontawesome-free/css/all.min.css';
import './App.css';
import AddChatHive from './Components/AddPost/AddChatHive.jsx';
import Home from './Components/HomePage/Home.jsx';
import SinglePost from './Components/HomePage/SinglePost.jsx';
import Landing from './Components/LandingPage/Landing.jsx';
import LandingPage from './Components/LandingPage/Landing.jsx';
import Dashboard from './Components/DshbrdCmpnt/Dashboard.jsx';
import Profile from './Components/Profile/Profile.jsx';
   
function App() {
  return (
    <Router>
      
      <Routes>
      <Route path="/" element={<LandingPage />} />
        <Route path="/signup" element={<LoginSignup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={< HomePage />} />
        <Route path="/home" element={<Home />} />
        <Route path="/add-post" element={<AddChatHive />} />
        <Route path="/Dashboard" element={<Dashboard />} />
        <Route path="/info" element={<Profile />} />
        <Route path="/post/:id" element={<SinglePost />} />
       </Routes>
    </Router>
    
  );
}

export default App;

import React, { useState } from "react";
import Navbar from "./Navbar";
import MenuBar from "./MenuBar";
import ChatList from "./ChatList";
import Jumbotron from "./Jumbotron"; // Import Jumbotron
import "./Home.css";

const Home = () => {
  const [searchQuery, setSearchQuery] = useState(""); // State for search query

  return (
    <>
      <Navbar setSearchQuery={setSearchQuery} /> {/* Pass setSearchQuery */}
      <MenuBar />
      <div className="home-container">
        <ChatList searchQuery={searchQuery} /> {/* Pass searchQuery */}
        <Jumbotron /> {/* Add Jumbotron here */}
      </div>
    </>
  );
};

export default Home;

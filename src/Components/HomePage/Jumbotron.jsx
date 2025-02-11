import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import { FaPlus, FaPen, FaPaperPlane } from "react-icons/fa"; 
import "./Jumbotron.css"; 

const Jumbotron = () => {
  const [inputValue, setInputValue] = useState("");
  const navigate = useNavigate(); // Hook for navigation

  return (
    <div className="ask-section">
      <div className="icon-container"></div>
      <h3>What do you want to ask or share?</h3>
      <input
        type="text"
        placeholder="Type your question..."
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
      />
      <div className="buttons">
        <button className="ask-btn" onClick={() => navigate("/add-post")}>
          <FaPlus className="btn-icon" /> Ask
        </button>
        <button className="answer-btn" onClick={() => navigate("/post/1")}>
          <FaPen className="btn-icon" /> Answer
        </button>
        <button className="post-btn" onClick={() => navigate("/add-post")}>
          <FaPaperPlane className="btn-icon" /> Post
        </button>
      </div>
    </div>
  );
};

export default Jumbotron;

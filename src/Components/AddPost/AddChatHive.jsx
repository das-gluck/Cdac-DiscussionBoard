import { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../HomePage/Navbar"; // Import Navbar component
import MenuBar from "../HomePage/MenuBar";
 import "./AddChatHive.css"; // Import CSS file

const AddChatHive = () => {
  const [chatHive, setChatHive] = useState({
    question: "",
    content: "",
    user: {
      user_id: "1",
    },
  });

  useEffect(() => {
    // Simulate fetching user data after authentication
    const userId = localStorage.getItem("user_id");
    if (userId) {
      setChatHive((prevState) => ({
        ...prevState,
        user: { user_id: userId },
      }));
    } else {
      console.warn("User not authenticated. Redirect to login.");
    }
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setChatHive({ ...chatHive, [name]: value });
  };

  const submitHandler = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/posts", chatHive)
      .then((response) => {
        console.log("ChatHive added successfully:", response.data);
        alert("ChatHive added successfully");
        setChatHive({
          question: "",
          content: "",
          user: { user_id: chatHive.user.user_id },
        });
      })
      .catch((error) => {
        console.error("Error adding ChatHive:", error);
        alert("Error adding ChatHive");
      });
  };

  return (
    <div>
      <Navbar />
      <MenuBar />
      <div className="jumbotron-container">
        <div className="jumbotron">
          <form className="chat-form" onSubmit={submitHandler}>
            <div className="form-group">
              <label className="form-label">
                <h6>Question</h6>
              </label>
              <input
                type="text"
                className="chat-input"
                placeholder="Enter your question"
                name="question"
                value={chatHive.question}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="form-group">
              <label className="form-label">
                <h6>Description</h6>
              </label>
              <textarea
                className="chat-textarea"
                placeholder="Enter a description"
                name="content"
                value={chatHive.content}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="form-group btn-container">
              <button type="submit" className="chat-btn">
                Post Question
              </button>
            </div>
          </form>
        </div>
      </div>
     </div>
  );
};

export default AddChatHive;

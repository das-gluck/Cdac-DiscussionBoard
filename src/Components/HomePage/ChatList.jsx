import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./ChatList.css";

const ChatList = ({ searchQuery = "" }) => {
  const [chats, setChats] = useState([]);
  const [filteredChats, setFilteredChats] = useState([]);
  const [isError, setIsError] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchChats = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/posts");
        setChats(response.data);
        setFilteredChats(response.data);
      } catch (error) {
        console.error("Error fetching chats:", error);
        setIsError(true);
      }
    };

    fetchChats();
  }, []);

  useEffect(() => {
    if (!searchQuery.trim()) {
      setFilteredChats(chats);
    } else {
      const filtered = chats.filter(
        (chat) =>
          chat.question.toLowerCase().includes(searchQuery.toLowerCase()) ||
          chat.content.toLowerCase().includes(searchQuery.toLowerCase())
      );
      setFilteredChats(filtered);
    }
  }, [searchQuery, chats]);

  if (isError) {
    return (
      <h2 className="error-message">
        Something went wrong while fetching chats...
      </h2>
    );
  }

  return (
    <div className="chatlist-container">
      <div className="grid">
        {filteredChats.map((chat) => (
          <div
            className="card"
            key={chat.id}
            onClick={() => navigate(`/post/${chat.id}`)}
          >
            <div className="card-body">
              <h5 className="card-title">Question: {chat.question}</h5>
              <p className="card-text">Description: {chat.content}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ChatList;

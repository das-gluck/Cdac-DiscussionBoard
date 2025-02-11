import { useEffect, useState } from "react";
import axios from "axios";
import "./Dashboard.css";
import PieChart from "./PieChart";
 

const DataBoard = () => {
  const [users, setUsers] = useState([]);
  const [posts, setPosts] = useState([]);
  const [comments, setComments] = useState([]);
  const [error, setError] = useState(false);

  // Fetch data from API for Users
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/users/1");
        setUsers(response.data);
        console.log("follow:", response.data);
      } catch (error) {
        console.error("Error fetching users:", error);
        setError(true);
      }
    };

    fetchUsers();
  }, []);

  

  // Counts
  const TUCount = users.firstName;
  const TPCount = users.followersCount;
  const TCCount = users.followingCount;
  ;

  return (
    <>
      <div className="dashboard-container">
        <div className="total-users-container">
          <div className="total-users">
            <h2 className="title">Users</h2>
            <p className="count">{TUCount}</p>
          </div>
        </div>
        <div className="total-posts-container">
          <div className="total-posts">
            <h2 className="title">Followers</h2>
            <p className="count">{TPCount}</p>
          </div>
        </div>
        <div className="total-comments-container">
          <div className="total-comments">
            <h2 className="title">Following</h2>
            <p className="count">{TCCount}</p>
          </div>
        </div>
      </div>
    </>
  );
};

export default DataBoard;

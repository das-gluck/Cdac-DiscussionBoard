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
        const response = await axios.get("http://localhost:8080/api/users");
        setUsers(response.data);
        console.log("Users:", response.data);
      } catch (error) {
        console.error("Error fetching users:", error);
        setError(true);
      }
    };

    fetchUsers();
  }, []);

  // Fetch data from API for Posts
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/posts");
        setPosts(response.data);
        console.log("Post:", response.data);
      } catch (error) {
        console.error("Error fetching posts:", error);
        setError(true);
      }
    };

    fetchPosts();
  }, []);

  // Fetch data from API for Comments
  useEffect(() => {
    const fetchComments = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/comments");
        setComments(response.data);
        console.log("comments:", response.data);
      } catch (error) {
        console.error("Error fetching comments:", error);
        setError(true);
      }
    };

    fetchComments();
  }, []);

  // Counts
  const TUCount = users?.length || 0;
  const TPCount = posts?.length || 0;
  const TCCount = comments?.length || 0;

  return (
    <>
      <div className="dashboard-container">
        <div className="total-users-container">
          <div className="total-users">
            <h2 className="title">Total Users</h2>
            <p className="count">{TUCount}</p>
          </div>
        </div>
        <div className="total-posts-container">
          <div className="total-posts">
            <h2 className="title">Total Posts</h2>
            <p className="count">{TPCount}</p>
          </div>
        </div>
        <div className="total-comments-container">
          <div className="total-comments">
            <h2 className="title">Total Comments</h2>
            <p className="count">{TCCount}</p>
          </div>
        </div>
      </div>
    </>
  );
};

export default DataBoard;

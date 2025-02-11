import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Navbar from "./Navbar";
import MenuBar from "./MenuBar";
import Comments from "./Comments";
import "./SinglePost.css";

const SinglePost = () => {
  const { id } = useParams(); // Get post ID from URL
  const { faltu } = useParams(); // Get post ID from URL
  const [post, setPost] = useState(null);
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/posts/post/${id}`);
        if (response.data) {
           
          setPost(response.data);
        } else {
          setIsError(true);
        }
      } catch (error) {
        console.error("Error fetching post:", error);
        setIsError(true);
      }
    };

    fetchPost();
  }, [faltu]);

  if (isError) {
    return (
      <>
        <Navbar />
        <MenuBar />
        <h2 className="error-message">Post not found...</h2>
      </>
    );
  }

  if (!post) {
    return (
      <>
        <Navbar />
        <MenuBar />
        <h2 className="loading-message">Loading...</h2>
      </>
    );
  }

  return (
    <>
      <Navbar />
      <MenuBar />
      <div className="single-post-container">
        <h1>{post.question}</h1>
        <p>{post.content}</p>
        <hr /> {/* Horizontal line */}
        <Comments postId={id} /> {/* Render comments for the post */}
      </div>
    </>
  );
};

export default SinglePost;

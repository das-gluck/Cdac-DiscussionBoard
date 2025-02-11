import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Comment.css';

function Comment({ comment, addReply }) {
    const [replyText, setReplyText] = useState('');
    const [showReplyBox, setShowReplyBox] = useState(false);

    const handleReply = () => {
        setShowReplyBox(true);
    };

    const handleCancelReply = () => {
        setShowReplyBox(false);
        setReplyText('');
    };

    const handleReplySave = (commentId) => {
        addReply(commentId, replyText);
        setShowReplyBox(false);
        setReplyText('');
    };

    return (
        <li className="comment-item">
            <div className="comment-content">{comment.display}</div>
            {!showReplyBox && (
                <button onClick={handleReply} className='action-btn'>Reply</button>
            )}
            {showReplyBox && (
                <div className="reply-box">
                    <input
                        value={replyText}
                        type='text'
                        className='comment-input'
                        onChange={(e) => setReplyText(e.target.value)}
                    />
                    <button onClick={() => handleReplySave(comment.id)} className='action-btn'>Save</button>
                    <button onClick={handleCancelReply} className='action-btn'>Cancel</button>
                </div>
            )}
            {comment.children && comment.children.length > 0 && (
                <ul className="comment-list">
                    {comment.children.map((child) => (
                        <Comment key={child.id} comment={child} addReply={addReply} />
                    ))}
                </ul>
            )}
        </li>
    );
}

function Comments({ postId }) {
    const [input, setInput] = useState('');
    const [comments, setComments] = useState([]);

    useEffect(() => {
        const fetchComments = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/comments/byPost/${postId}`);
                
                const transformedComments = transformComments(response.data);
                const filteredComments = transformedComments.length > 0 ? [transformedComments[0]] : [];
                // const topLevelComments = transformedComments.filter(comment => comment.parent_id === null);
                // setComments(topLevelComments);
                console.log(response.data);
                setComments(filteredComments);
            } catch (error) {
                console.error('Error fetching comments:', error);
            }
        };

        fetchComments();
    }, [postId]);

    const transformComments = (data) => {
        return data.map((item) => ({
            id: item.id,
            display: item.content,
            children: transformComments(item.replies || []),
        }));
    };

    const addComment = async (text) => {
        try {
            const newComment = {
                content: text,
                post: { id: postId },
                user: { user_id: 1 },
                parent: null,
            };
            const response = await axios.post('http://localhost:8080/api/comments', newComment);
            console.log("posttststs : "+ response.data);
            const transformedComment = transformComments([response.data])[0];

            setComments((prevComments) => [transformedComment, ...prevComments]);
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };

    const addReply = async (parentId, text) => {
        try {
            const newReply = {
                content: text,
                post: { id: postId },
                user: { user_id: 3 },
                parent: { id: parentId },
            };
            const response = await axios.post('http://localhost:8080/api/comments/replies', newReply);
            const updatedComments = addReplyToTree(comments, parentId, response.data);
            setComments(updatedComments);
        } catch (error) {
            console.error('Error adding reply:', error);
        }
    };

    const addReplyToTree = (comments, parentId, newReply) => {
        return comments.map((comment) => {
            if (comment.id === parentId) {
                return {
                    ...comment,
                    children: [
                        {
                            id: newReply.id,
                            display: newReply.content,
                            children: [],
                        },
                        ...comment.children,
                    ],
                };
            }
            if (comment.children && comment.children.length > 0) {
                return {
                    ...comment,
                    children: addReplyToTree(comment.children, parentId, newReply),
                };
            }
            return comment;
        });
    };

    const handleNewComment = () => {
        if (input.trim()) {
            addComment(input);
            setInput('');
        }
    };

    return (
        <div className="comment-container">
            <div className='comment-box'>
                <input
                    className='comment-input'
                    placeholder='Your comment...'
                    value={input}
                    type='text'
                    onChange={(e) => setInput(e.target.value)}
                />
            </div>
            <div>
                <button className='action-btn' onClick={handleNewComment}>Comment</button>
            </div>
            <div className='comments'>
                <ul className="comment-list">
                    {comments.map((comment) => (
                        <Comment key={comment.id} comment={comment} addReply={addReply} />
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default Comments;
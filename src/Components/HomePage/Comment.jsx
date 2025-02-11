import React, { useRef, useState } from 'react';
import './Comment.css';


function Comment({ comment, addReply }) {
    const [replyText, setReplyText] = useState('');
    const [showReplyBox, setShowReplyBox] = useState(false);
    const inputRef = useRef(null);

    const handleReply = () => {
        setShowReplyBox(true);
        setTimeout(() => {
            inputRef.current.focus();
        }, 0);
    };

    const handleCancelComment = () => {
        setShowReplyBox(false);
    };

    const handleReplySave = (commentId) => {
        addReply(commentId, replyText);
        setShowReplyBox(false);
        setReplyText('');
    };

    const handleKeyDown = (e, commentId) => {
        if (e.key === 'Enter') {
            handleReplySave(commentId);
        } else if (e.key === 'Escape') {
            handleCancelComment();
        }
    };

    return (
        <li key={comment.id} className="comment-item">
            {comment.display}
            {!showReplyBox && (
                <button onClick={handleReply} className="action-btn">
                    Reply
                </button>
            )}
            {showReplyBox ? (
                <>
                    <br />
                    <input
                        value={replyText}
                        type="text"
                        ref={inputRef}
                        className="comment-input"
                        onKeyDown={(e) => handleKeyDown(e, comment.id)}
                        onChange={(e) => setReplyText(e.target.value)}
                    />
                    <br />
                    <button onClick={() => handleReplySave(comment.id)} className="action-btn">
                        Save
                    </button>
                    <button className="action-btn" onClick={handleCancelComment}>
                        Cancel
                    </button>
                </>
            ) : null}
            {comment.children.length ? (
                <ul className="comment-list">
                    {comment.children.map((item) => (
                        <Comment key={item.id} comment={item} addReply={addReply} />
                    ))}
                </ul>
            ) : null}
        </li>
    );
}

export default Comment;

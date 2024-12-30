package com.example.cdb.service;

import java.util.List;
import java.util.Optional;

import com.example.cdb.entity.Comment;

public interface CommentService {
	public Comment addComment(Comment comment);
	public Comment findCommentById(Long id);
	public List<Comment> getAllComments();
	public List<Comment> getCommentsByPost(Long postId);
	public Comment updateComment(Comment comment);
	public void deleteCommentByCommentId(Long commentId);
	public Optional<Comment> getCommentsById(Long commentId);

}

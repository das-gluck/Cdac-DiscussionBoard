package com.example.cdb.service;

import java.util.List;

import com.example.cdb.entity.Comment;

public interface CommentService {
	public List<Comment> getCommentsByPost(Long postId);
	public Comment saveComment(Comment comment);
	public Comment findCommentById(Long id);

}

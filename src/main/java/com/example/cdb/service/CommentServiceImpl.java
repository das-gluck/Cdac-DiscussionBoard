package com.example.cdb.service;

import org.springframework.stereotype.Service;

import com.example.cdb.entity.Comment;
import com.example.cdb.repository.CommentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CommentServiceImpl implements CommentService {

	
	
	@Autowired 
	private CommentRepository commentRepository;
	
	public List<Comment> getCommentsByPost(Long postId) {
		
		return commentRepository.findByPostIdAndParentIsNull(postId); 
	}
	
	public Comment saveComment(Comment comment) { 
		
		return commentRepository.save(comment); 
	}
	
	public Comment findCommentById(Long id) {
	    return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
	}
	
}

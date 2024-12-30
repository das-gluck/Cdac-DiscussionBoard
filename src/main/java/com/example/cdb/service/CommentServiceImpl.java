package com.example.cdb.service;

import org.springframework.stereotype.Service;

import com.example.cdb.entity.Comment;
import com.example.cdb.entity.Post;
import com.example.cdb.repository.CommentRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	
	
	@Autowired 
	private CommentRepository commentRepository;
	
	
	public Comment addComment(Comment comment) { 
		
		return commentRepository.save(comment); 
	}
	
	public Comment findCommentById(Long id) {
	    return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
	}
	
	public List<Comment> getAllComments() {
		
		return commentRepository.findAll();
	}
	
	

	@Override
	public List<Comment> getCommentsByPost(Long postId) {
		
		return commentRepository.findByPostId(postId);
	}
	
	public Comment updateComment(Comment comment) {
		Optional<Comment> existingComment = commentRepository.findById(comment.getId());
		if(existingComment.isPresent() ) {
			commentRepository.save(comment);
			return comment;
		}else {
			return null;
		}
		
	}
	
	public void deleteCommentByCommentId(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	@Override
	public Optional<Comment> getCommentsById(Long commentId) {
		return commentRepository.findById(commentId);
	}
	
}

package com.example.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdb.entity.Comment;
import com.example.cdb.service.CommentService;




@RestController
@RequestMapping("/api")
@CrossOrigin  // Allow requests from this origin
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comments")
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	@GetMapping("/comments/byPost/{postId}")
	public List<Comment> getCommentsByPost(@PathVariable("postId") Long postId) {
		return commentService.getCommentsByPost(postId);
	}
	
	@GetMapping("/comments/{commentId}")
	public Optional<Comment> getCommentsById(@PathVariable("commentId") Long commentId) {
		return commentService.getCommentsById(commentId); 
	}
	
	
	@PostMapping("/comments")
	public Comment addComment(@RequestBody Comment comment) {
		
		// Validate if the Post and User exist
//	    if (comment.getPost() == null || comment.getPost().getId() == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    }
//
//	    if (comment.getUser() == null || comment.getUser().getUser_id() == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    }
//	    return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
//	    
		return commentService.addComment(comment);
	}
	
	@PostMapping("/comments/replies")
	public Comment addReply(@RequestBody Comment reply) {
		
//		// Validate if the Post, User, and Parent Comment exist
//	    if (reply.getPost() == null || reply.getPost().getId() == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    }
//
//	    if (reply.getUser() == null || reply.getUser().getUser_id() == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    }
//
//	    if (reply.getParent() == null || reply.getParent().getId() == null) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    }
//	    return ResponseEntity.status(HttpStatus.CREATED).body(savedReply);
		
		return commentService.addComment(reply);
		//return reply;
		
	}
	
	@PutMapping("/comments")
	public Comment updateComment(@RequestBody Comment comment) {
		System.out.println(comment);
		return commentService.updateComment(comment);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public String deleteComment(@PathVariable("commentId") Long commentId) {
		commentService.deleteCommentByCommentId(commentId);
		return "comment deleted by id : "+ commentId;
	}
	

}
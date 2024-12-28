package com.example.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdb.entity.Comment;
import com.example.cdb.entity.Post;
import com.example.cdb.service.CommentService;
import com.example.cdb.service.PostService;




@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3001")  // Allow requests from this origin


public class CommentController {

	
	@Autowired 
	private CommentService commentService;
	
	 @Autowired
	private PostService postService;
	
	@GetMapping("/post/{postId}")
	public List<Comment> getCommentsByPost(@PathVariable("postId") Long postId) {
		
		return commentService.getCommentsByPost(postId);
	}
	
	
	
	@PostMapping(value = "/comment", consumes = "application/json")
	public Comment createComment(@RequestBody Comment comment) {
		
	    if (comment.getParent() != null && comment.getParent().getId() != null) {
	        // Ensure the parent comment exists
	        Comment parent = commentService.findCommentById(comment.getParent().getId());
	        comment.setParent(parent);
	    }

	    // Ensure the associated post exists
	    if (comment.getPost() != null && comment.getPost().getId() != null) {
	        Post post = postService.getPostById(comment.getPost().getId()).orElseThrow(() -> new IllegalArgumentException("Post not found"));
	        comment.setPost(post);
	    }

	    return commentService.saveComment(comment);
	}

}
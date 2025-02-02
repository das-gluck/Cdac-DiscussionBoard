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

import com.example.cdb.entity.Post;
import com.example.cdb.service.PostService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/posts")
	public List<Post> getAllPost() {
		return postService.getAllPosts();
	}
	
	
	@PostMapping("/posts")
	public Post addPost(@RequestBody Post post) {
		return postService.savePost(post);
	}
	
	@GetMapping("/posts/{userId}")
	public List<Post> getPostByUserId(@PathVariable("userId") Long userId) {
		return postService.getPostsByUserId(userId);
	}
	
	@GetMapping("/posts/post/{postId}")
	public Optional<Post> getPostByPostId(@PathVariable("postId") Long postId){
		return postService.getPostsByPostId(postId);
	}
	
	@DeleteMapping("/posts/{postId}")
	public String deletePostById(@PathVariable("postId") Long postId) {
		postService.deletePostByPostId(postId);
		return "post deleted by id : "+ postId;
	}
	
	@PutMapping("/posts")
	public Post updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}
}

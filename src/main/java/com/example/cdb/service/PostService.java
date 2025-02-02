package com.example.cdb.service;

import java.util.List;
import java.util.Optional;

import com.example.cdb.entity.Post;

public interface PostService {

	public List<Post> getAllPosts();
	public List<Post> getPostsByUserId(Long userId);
	public Optional<Post> getPostsByPostId(Long postId);
	public Post savePost(Post post);
	
	public void deletePostByPostId(Long postId);
	public Post updatePost(Post post);
	
}

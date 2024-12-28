package com.example.cdb.service;

import java.util.List;
import java.util.Optional;

import com.example.cdb.entity.Post;

public interface PostService {

	public List<Post> getAllPosts();
	public Optional<Post> getPostById(Long id);
	public Post savePost(Post post);
	
}

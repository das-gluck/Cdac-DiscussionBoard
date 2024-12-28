package com.example.cdb.service;

import org.springframework.stereotype.Service;
import com.example.cdb.entity.Post;
import com.example.cdb.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepository postRepository;
	
	
	public List<Post> getAllPosts() { 
		return postRepository.findAll();
	}
	
	public Optional<Post> getPostById(Long id) {
		return postRepository.findById(id);
	}
	
	public Post savePost(Post post) {
		return postRepository.save(post);
	}
	
	
}
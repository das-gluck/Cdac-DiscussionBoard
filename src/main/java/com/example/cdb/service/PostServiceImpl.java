package com.example.cdb.service;

import org.springframework.stereotype.Service;
import com.example.cdb.entity.Post;
import com.example.cdb.repository.PostRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Post> getAllPosts() { 
		return postRepository.findAll();
	}
	
	@Override
	public Post savePost(Post post) {
		postRepository.save(post);
		return post;
	}



	@Override
	public List<Post> getPostsByUserId(Long userId) {
		return postRepository.findPostByUserId(userId);
	}
	
	public void deletePostByPostId(Long postId) {
		postRepository.deleteById(postId);
	}
	
	public Post updatePost(Post post) {
		Optional<Post> existingPost = postRepository.findById(post.getId());
		if(existingPost.isPresent() ) {
			postRepository.save(post);
			return post;
		}else {
			return null;
		}
		
	}

	@Override
	public Optional<Post> getPostsByPostId(Long postId) {
		return postRepository.findById(postId);
	}
	
	
}
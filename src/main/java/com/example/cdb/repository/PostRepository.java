package com.example.cdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cdb.entity.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	@Query("SELECT p FROM Post p WHERE p.user.user_id = :userId")
	List<Post> findPostByUserId(@Param("userId") Long userId);
}

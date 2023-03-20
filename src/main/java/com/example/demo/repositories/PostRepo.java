package com.example.demo.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;



public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(UserRepo user);
	List<Post> findByCategory(Category category);
}

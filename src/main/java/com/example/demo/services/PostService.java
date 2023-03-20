package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto ,Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost();
	Post getPostById(Integer postId);
	List<PostDto> getAllPostByCategory(Integer categoryId);
	List<PostDto> getAllPostByUser(Integer userId);
	List<PostDto> searchAllPost(String keyword);
	
}

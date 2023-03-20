package com.example.demo.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		Date newdateDate=new Date();
		post.setAddedDate(newdateDate);
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost=this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchAllPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

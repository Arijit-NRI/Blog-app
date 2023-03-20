package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.PostDto;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//CREATE - create user
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer uid,@PathVariable("categoryId") Integer cid)
	{
		PostDto createPostDto= postService.createPost(postDto, uid, cid);
		//return new ResponseEntity<>(createPostDto,HttpStatus.CREATED);
		return new ResponseEntity<>(createPostDto,HttpStatus.CREATED);
		//return null;
	}

}

package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.services.FileService;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//CREATE - create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer uid,@PathVariable("categoryId") Integer cid)
	{
		PostDto createPostDto= postService.createPost(postDto, uid, cid);
		return new ResponseEntity<>(createPostDto,HttpStatus.CREATED);
	}
	
	//GET - get all posts by UserId
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Integer uid)
	{
		List<PostDto> allPostDtos=postService.getAllPostByUser(uid);
		return new ResponseEntity<>(allPostDtos,HttpStatus.OK);
	}
	
	//GET - get all posts by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("categoryId") Integer cid)
	{
		List<PostDto> allPostDtos=postService.getAllPostByCategory(cid);
		return new ResponseEntity<>(allPostDtos,HttpStatus.OK);
	}
	
	//GET - get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllposts(@RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
			@RequestParam(value="pageSize",defaultValue = "5",required=false) Integer psize,
			@RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir)
	{
		PostResponse postResponse=postService.getAllPost(pno,psize,sortBy,sortDir);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	//GET - get post by postId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer pid)
	{
		PostDto postDto=this.postService.getPostById(pid);
		return new ResponseEntity<>(postDto,HttpStatus.OK);
	}
	
	//DELETE - delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer pid)
	{
		this.postService.deletePostById(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted",true),HttpStatus.OK);
	}

	
	//UPDATE - update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer pid)
	{
		PostDto updatedpostDto=this.postService.updatePost(postDto, pid);
		return new ResponseEntity<>(updatedpostDto,HttpStatus.OK);
	}
	
	//SEARCH - search post
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword)
	{
		List<PostDto> postDtos=this.postService.searchAllPost(keyword);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
	}
	
	//POST - post image upload
	@PostMapping("/post/{postId}/image/upload")
	public ResponseEntity<PostDto> uploadImage(@PathVariable("postId") Integer pid,
						 @RequestParam("image") MultipartFile img) throws IOException
	{
		PostDto postDto= this.postService.getPostById(pid);
		String fileNameString= this.fileService.uploadImage(path, img);
		postDto.setImageName(fileNameString);
		PostDto updatePostDto= this.postService.updatePost(postDto, pid);
		return new ResponseEntity<>(updatePostDto,HttpStatus.OK);
	}
	
	// method to serve files
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}

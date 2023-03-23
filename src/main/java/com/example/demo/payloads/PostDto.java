package com.example.demo.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.example.demo.entities.Category;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>();
	
	
}

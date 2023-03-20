package com.example.demo.payloads;

import java.util.Date;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String title;
	private String content;
	private String imageName;
	private Date addeDate;
	private CategoryDto category;
	private UserDto user;
	
	
}

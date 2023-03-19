package com.example.demo.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotNull
	@NotEmpty(message = "Name cannot be Empty")
	private String name;
	
	@Email
	@Pattern(regexp = ".+@nrifintech\\.com$", message = "Email should end with @nrifintech.com")
	private String email;
	@NotNull
	@NotEmpty(message = "Password cannot be Empty")
	private String password;
	@NotNull
	private String about;
	
}

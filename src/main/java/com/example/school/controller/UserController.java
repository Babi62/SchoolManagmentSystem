package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.customResponse.CustomResponse;
import com.example.school.entity.User;
import com.example.school.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/add")
	public ResponseEntity<CustomResponse<User>> addUser(@RequestBody User user){
		User newUser=null;
		String userName=user.getUsername();
		String pass=user.getPassword();
		String message= uService.UserFound(user);
		
		try {
			if(userName==null || userName.isEmpty() && pass==null || pass.isEmpty()) {
				message = "Please fill the fields first!!";
				CustomResponse<User> response = new CustomResponse<>(message, newUser);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if(message!=null) {
				CustomResponse<User> response = new CustomResponse<>(message, newUser);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			newUser=uService.add(user);
			message= "User added sucessfully!!";
			CustomResponse<User> response = new CustomResponse<>(message, newUser);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);

			message = "An error occurred while creating the user";
			CustomResponse<User> response = new CustomResponse<>(message, newUser);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}

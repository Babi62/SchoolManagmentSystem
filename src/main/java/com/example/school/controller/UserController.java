package com.example.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.customResponse.CustomResponse;
import com.example.school.entity.Role;
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
	
	@PostMapping("/assign")
	public ResponseEntity<CustomResponse<User>> roleAssign(@RequestParam Long id, @RequestBody List<Role> role){
		User u=null;
		String message=null;
		try {
			u=uService.getUserById(id);
			
			if(u==null) {
				message = "User with id "+ id+ " not found!";
				CustomResponse<User> response = new CustomResponse<>(message, u);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
	        
			
			List<Role> validRoles = new ArrayList<>();
	    	Role existingRole=null;
	        for (Role roles : role) {
	            existingRole = uService.getRoleById(roles.getRoleId());
	            if (existingRole != null) {
	                validRoles.add(existingRole);	               
	            }      
	        }
	        
	        u.setRole(validRoles);
			uService.SaveRole(u);
			
//			message=uService.roleFound(role);
//			if(message!=null) {
//				CustomResponse<User> response = new CustomResponse<>(message, user);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//			user=uService.assignRole(id, role);
//			if(user==null) {
//				message = "User with id "+ id+ " not found!";
//				CustomResponse<User> response = new CustomResponse<>(message, user);
//				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//			}
//			
			message= "User role assigned sucessfully!!";
			CustomResponse<User> response = new CustomResponse<>(message, u);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);

			message = "An error occurred while assigning the user role";
			CustomResponse<User> response = new CustomResponse<>(message, u);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
}

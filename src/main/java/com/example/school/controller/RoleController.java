package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.customResponse.CustomResponse;
import com.example.school.entity.Role;
import com.example.school.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private RoleService roleServ;
	
	@PostMapping("/add")
	public ResponseEntity<CustomResponse<Role>> add(@RequestBody Role role){
		Role newRole=null;
		String name=role.getRoleName();
		String message=null;
		try {
			if(name == null || name.isEmpty()) {
				message = "Please fill the role name first!!";
				CustomResponse<Role> response = new CustomResponse<>(message, newRole);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			message=roleServ.roleFound(role);
			if(message!= null) {
				CustomResponse<Role> response = new CustomResponse<>(message, newRole);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			newRole=roleServ.addRole(role);
			message="Role added";
			CustomResponse<Role> response = new CustomResponse<>(message, newRole);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);

			message = "An error occurred while creating the role";
			CustomResponse<Role> response = new CustomResponse<>(message, newRole);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/view")
	public ResponseEntity<CustomResponse<List<Role>>> view(){
		List<Role> allroles=roleServ.viewRole();
		String message= null;
		if(allroles==null) {
			message="Opps!! role's empty. None to display";
			CustomResponse<List<Role>> response = new CustomResponse<>(message, allroles);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		message = "found!";
		CustomResponse<List<Role>> response = new CustomResponse<>(message, allroles);
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CustomResponse<Role>> delete(@RequestParam Long id){
		String message=roleServ.deleteRole(id);
		if (message != null) {
			CustomResponse<Role> response = new CustomResponse<>(message, null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		CustomResponse<Role> response = new CustomResponse<>("Deleted", null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

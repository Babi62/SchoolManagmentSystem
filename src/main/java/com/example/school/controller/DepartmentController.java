package com.example.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.entity.Department;
import com.example.school.service.DepartmentService;
import com.example.school.customResponse.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService depService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<CustomResponse<Department>> add(@RequestBody Department dep) {
		Department newDepartment = null; 
				
		try {
			if(dep.getDep_name()!=null) {
				
				String message= depService.nameFound(dep);
				if(message!=null) {
					CustomResponse<Department> response = new CustomResponse<>(message, newDepartment);
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				
				newDepartment = depService.createDepartment(dep);
				CustomResponse<Department> response = new CustomResponse<>(
		                "Department Created Successfully",
		                newDepartment
		        );
				
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}
			
			String message="Please fill the Department name first!!";
			CustomResponse<Department> response = new CustomResponse<>(message, newDepartment);
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e);
			String message="An error occurred while creating the department";
			CustomResponse<Department> response = new CustomResponse<>(message, newDepartment);
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/view")
	public ResponseEntity<CustomResponse<List<Department>>> view(){
		List<Department> foundDepartment=null;
		
		String message=depService.emptyDepartment();
		if(message!=null) {
			CustomResponse<List<Department>> response = new CustomResponse<>(message, foundDepartment);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		message="found!";
		foundDepartment=depService.viewDepartment();
		CustomResponse<List<Department>> response = new CustomResponse<>(message, foundDepartment);
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
	
}

package com.example.school.controller;

import java.util.List;

//import java.time.LocalDate;

//import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.customResponse.CustomResponse;
import com.example.school.entity.Department;
import com.example.school.entity.Student;
//import com.example.school.entity.StudentEnrollment;
/*import com.example.school.entity.StudentEnrollment;*/
import com.example.school.repository.DepartmentRepository;
/*import com.example.school.service.DepartmentService;*/
//import com.example.school.service.EnrollmentService;
import com.example.school.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studService;

	@Autowired
	private DepartmentRepository depRepo;

	// private EnrollmentService enrollService;

	/*
	 * public StudentController(StudentService studService, DepartmentRepository
	 * depRepo) { this.studService = studService; this.depRepo = depRepo; }
	 */

	@PostMapping("/add")
	public ResponseEntity<CustomResponse<Student>> register(@RequestBody Student stud) {
		Student newStudent = null;
		String fName = stud.getFirst_name();
		String lName = stud.getLast_name();
		int age = stud.getAge();
		Long id = stud.getDepartment().getDepid();

		try {
			if (fName.isEmpty() && lName.isEmpty()) {
				String message = "Please fill the field first!";
				CustomResponse<Student> response = new CustomResponse<>(message, newStudent);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Department department = depRepo.findById(id)
					.orElseThrow(() -> new RuntimeException("Department not found"));

			newStudent = studService.registerStudent(fName, lName, age, department);

			CustomResponse<Student> response = new CustomResponse<>(
					"Student added Successfully",
					newStudent);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);

			String message = "An error occurred while creating the student";
			CustomResponse<Student> response = new CustomResponse<>(message, newStudent);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/view")
	public ResponseEntity<CustomResponse<List<Student>>> view() {
		List<Student> allStudents = studService.getStudents();
		String message = null;
		
		try {
			if (allStudents == null) {
				message = "No student found!!";
				CustomResponse<List<Student>> response = new CustomResponse<>(message, allStudents);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			message = "student found!!";
			CustomResponse<List<Student>> response = new CustomResponse<>(message, allStudents);
			return new ResponseEntity<>(response, HttpStatus.FOUND);

		} catch (Exception e) {
			System.out.println(e);

			message = "An error occurred while finding students";
			CustomResponse<List<Student>> response = new CustomResponse<>(message, allStudents);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

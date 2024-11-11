package com.example.school.service;

import java.util.List;

import com.example.school.entity.Department;
import com.example.school.entity.Student;

public interface StudentService {
	Student registerStudent(String fName, String lName, int age, Department dep);

	List<Student> getStudents();

	Student updateStudent(Long id, String fName, String lName, int age, Department dep);

	String deleteStudent(Long id);
}

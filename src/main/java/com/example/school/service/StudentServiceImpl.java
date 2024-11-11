package com.example.school.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.entity.Department;
import com.example.school.entity.Student;
import com.example.school.entity.StudentEnrollment;
import com.example.school.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studRepo;
	
	@Autowired
	private final EnrollmentService enrollService;
	
    public StudentServiceImpl(StudentRepository studRepo, EnrollmentService enrollService) {
        this.studRepo = studRepo;
        this.enrollService=enrollService;
    }

	@Override
	public Student registerStudent(String fName, String lName, int age, Department dep) {
		Student newStudent = new Student();
		
		newStudent.setFirst_name(fName);
		newStudent.setLast_name(lName);
		newStudent.setAge(age);
		newStudent.setDepartment(dep);
		
		newStudent=studRepo.save(newStudent);
		
		StudentEnrollment enroll=new StudentEnrollment();
		enroll.setStudent(newStudent); 
		enroll.setDepartment(newStudent.getDepartment());
		enroll.setEnrollmentDate(LocalDate.now());
		  
		enrollService.enroll(enroll);
		
		return newStudent;
		
	}

	@Override
	public List<Student> getStudents() {
		List<Student> allStudents= studRepo.findAll();
		return allStudents;
	}

	@Override
	public Student updateStudent(Long id, String first_name, String last_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStudent(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

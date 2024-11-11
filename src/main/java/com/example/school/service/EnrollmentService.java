package com.example.school.service;

import org.springframework.stereotype.Service;

import com.example.school.entity.StudentEnrollment;
import com.example.school.repository.StudentEnrollmentRepository;

@Service
public class EnrollmentService {
	
	private final StudentEnrollmentRepository enrollRepo;
	
	public EnrollmentService(StudentEnrollmentRepository enrollRepo) {
		this.enrollRepo = enrollRepo;
	}
	
	public StudentEnrollment enroll(StudentEnrollment std) {
		StudentEnrollment newEnrollment=enrollRepo.save(std);
		return newEnrollment;
	}
	
	
	
}

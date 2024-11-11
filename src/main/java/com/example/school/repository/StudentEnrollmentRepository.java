package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.StudentEnrollment;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

}

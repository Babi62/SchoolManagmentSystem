package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

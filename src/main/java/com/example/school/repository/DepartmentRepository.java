package com.example.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.school.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	@Query("SELECT d FROM Department d WHERE d.dep_name=?1 ")
	Optional<Department> findName(String name);
	
}

package com.example.school.service;

import java.util.List;

import com.example.school.entity.Department;

public interface DepartmentService {
	String nameFound(Department Dep);
	Department createDepartment(Department Dep);
	String emptyDepartment();
	List<Department> viewDepartment();
	Department updateDepartment(Long id, String name );
	void deleteDepartment(Long id);
}

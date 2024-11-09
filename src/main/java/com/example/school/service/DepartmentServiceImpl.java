package com.example.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.school.entity.Department;
import com.example.school.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository depRepo;
	
	public DepartmentServiceImpl(DepartmentRepository depRepo) {
		super();
		this.depRepo=depRepo;
	}
	
	@Override
	public String nameFound(Department Dep) {
		Optional<Department> name = depRepo.findName(Dep.getDep_name());
		String message=null;
		if(name.isPresent()) {
			message = "Department already registerd";
		}
		return message;
	}
	
	@Override
	public String emptyDepartment() {
		List<Department> allDepartment=depRepo.findAll();
		String message=null;
		if(allDepartment.isEmpty()) {
			message="Opps!! Department's empty. None to display";
		}
		return message;
	}
	
	@Override
	public Department createDepartment(Department Dep) {
		Department newDepartment= depRepo.save(Dep);
		return newDepartment;
	}

	@Override
	public List<Department> viewDepartment() {
		List<Department> allDepartment=depRepo.findAll();
		return allDepartment;
	}

	@Override
	public Department updateDepartment(Long id, String name) {
		
		return null;
	}

	@Override
	public void deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		
	}
}

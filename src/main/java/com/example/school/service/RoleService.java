package com.example.school.service;

import java.util.List;

import com.example.school.entity.Role;

public interface RoleService {
	String roleFound(Role role);
	Role addRole(Role role);
	List<Role> viewRole();
	Role updateRole(Role role);
	String deleteRole(Long id);
}

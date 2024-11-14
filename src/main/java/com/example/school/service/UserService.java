package com.example.school.service;


import java.util.List;

import com.example.school.entity.Role;
import com.example.school.entity.UserEntity;

public interface UserService {
	String UserFound(UserEntity user);
	UserEntity add(UserEntity user);

	/*
	 * User assignRole(Long id, List<Role> role); String roleFound(List<Role> role);
	 */
	List<UserEntity> allUsers();
	UserEntity getUserById(Long id);
	UserEntity SaveRole(UserEntity u);
	Role getRoleById(Long id);
}

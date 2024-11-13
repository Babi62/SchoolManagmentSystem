package com.example.school.service;


//import java.util.List;

import com.example.school.entity.Role;
import com.example.school.entity.User;

public interface UserService {
	String UserFound(User user);
	User add(User user);

	/*
	 * User assignRole(Long id, List<Role> role); String roleFound(List<Role> role);
	 */
	User getUserById(Long id);
	User SaveRole(User u);
	Role getRoleById(Long id);
}

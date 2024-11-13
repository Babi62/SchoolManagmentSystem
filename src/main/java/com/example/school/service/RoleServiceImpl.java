package com.example.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.school.entity.Role;
import com.example.school.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepo;
	
	public RoleServiceImpl(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}
	
	@Override
	public String roleFound(Role role) {
		Optional<Role> name = roleRepo.findByRoleName(role.getRoleName());
		String message = null;
		if (name.isPresent()) {
			message = "Role already registerd";
		}
		return message;
	}
	
	@Override
	public Role addRole(Role role) {
		Role newRole=roleRepo.save(role);
		return newRole;
	}

	@Override
	public List<Role> viewRole() {
		List<Role> allRoles=roleRepo.findAll();
		return allRoles;
	}
	
	@Override
	public Role updateRole(Role role) {
		String name=role.getRoleName();
		role.setRoleName(name);
		Role newRole=roleRepo.save(role);
		return newRole;
	}

	@Override
	public String deleteRole(Long id) {
		Role role = roleRepo.findById(id).orElse(null);
		String message = null;
		if (role == null) {
			message = "role id not found";
			return message;
		}
		roleRepo.delete(role);
		return message;
	}

	
	
}

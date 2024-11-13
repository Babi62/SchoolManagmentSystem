package com.example.school.service;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.school.entity.Role;
import com.example.school.entity.User;
import com.example.school.repository.RoleRepository;
import com.example.school.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;

    UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo= roleRepo;
    }
    
    @Override
    public String UserFound(User user) {
    	List<User> userName=userRepo.findByUsername(user.getUsername());
    	String message=null;
    	if(!userName.isEmpty()) {
    		message= "User name already exists!!";
    		return message;
    	}
    	return null;
    }
    
	/*
	 * @Override public String roleFound(List<Role> role) { List<Role> validRoles =
	 * new ArrayList<>(); Role existingRole=null; String message=null; for (Role
	 * roles : role) { existingRole =
	 * roleRepo.findById(roles.getRoleId()).orElse(null); if (existingRole != null)
	 * { validRoles.add(existingRole); message= "role Id found"; } } return message;
	 * }
	 */

	@Override
	public User add(User user) {
		User newUser=userRepo.save(user);
		return newUser;
	}

	/*
	 * @Override public User assignRole(Long id, List<Role> role) { User
	 * u=userRepo.findById(id).orElse(null); if(u!=null) { u.setRole(role); User
	 * userInRole= userRepo.save(u); return userInRole; }
	 * 
	 * return null; }
	 */

	@Override
	public User getUserById(Long id) {
		
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User SaveRole(User u) {
		
		return userRepo.save(u);
	}

	@Override
	public Role getRoleById(Long id) {
		
		return roleRepo.findById(id).orElse(null);
	}

}

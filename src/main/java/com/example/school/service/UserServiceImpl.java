package com.example.school.service;

import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.school.entity.Role;
import com.example.school.entity.UserEntity;
import com.example.school.repository.RoleRepository;
import com.example.school.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	
	private PasswordEncoder encoder;

    UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo= roleRepo;
        this.encoder= encoder;
    }
    
    @Override
    public String UserFound(UserEntity user) {
    	Optional<UserEntity> userName=userRepo.findByUsername(user.getUsername());
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
	public UserEntity add(UserEntity user) {
		String username= user.getUsername();
		String pass=user.getPassword();
				
		user.setUsername(username);
		user.setPassword(encoder.encode(pass));
				
		UserEntity newUser = userRepo.save(user);
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
	public UserEntity getUserById(Long id) {
		
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public UserEntity SaveRole(UserEntity u) {
		
		return userRepo.save(u);
	}

	@Override
	public Role getRoleById(Long id) {
		
		return roleRepo.findById(id).orElse(null);
	}

	@Override
	public List<UserEntity> allUsers() {
		List<UserEntity> allusers= userRepo.findAll();
		return allusers;
	}

	@Override
	public Optional<UserEntity> getUserByUsername(String userName) {
		Optional<UserEntity> foundUser= userRepo.findByUsername(userName);
		return foundUser;
	}

}

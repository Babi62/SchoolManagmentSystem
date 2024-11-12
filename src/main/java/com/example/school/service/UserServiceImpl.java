package com.example.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.school.entity.User;
import com.example.school.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;

    UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
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

	@Override
	public User add(User user) {
		User newUser=userRepo.save(user);
		return newUser;
	}

}

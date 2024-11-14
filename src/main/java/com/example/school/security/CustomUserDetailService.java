package com.example.school.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.school.entity.Role;
import com.example.school.entity.UserEntity;
import com.example.school.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private final UserRepository userRepo;
	
	CustomUserDetailService(UserRepository userRepo){
		this.userRepo=userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity u=userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		return new User(u.getUsername(), u.getPassword(), rolesToAuthority(u.getRole()));
	}
	
	private Collection<GrantedAuthority> rolesToAuthority(List<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

}

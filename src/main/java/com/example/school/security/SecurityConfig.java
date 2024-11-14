package com.example.school.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
	 * private final CustomUserDetailService detailServ;
	 * 
	 * public SecurityConfig(CustomUserDetailService detailServ) { super();
	 * this.detailServ = detailServ; }
	 */
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest() .authenticated());
//        	.and()
//        	.httpBasic();
		
		return http.build();
	}
	
	/*
	 * @Bean public UserDetailsService user() { UserDetails admin= User.builder()
	 * .username("admin") .password("password") .roles("ADMIN") .build();
	 * 
	 * UserDetails student= User.builder() .username("student")
	 * .password("password") .roles("STUDENT") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin, student); }
	 */
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception {
		return authconfig.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}

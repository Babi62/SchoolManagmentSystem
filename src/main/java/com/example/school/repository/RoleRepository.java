package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

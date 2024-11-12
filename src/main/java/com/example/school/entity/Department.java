package com.example.school.entity;

import java.util.Set;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long depid;

	@Column(name = "department_name", nullable = false)
	private String dep_name;

	@OneToMany(mappedBy= "department", fetch = FetchType.LAZY)
	private Set<Student> student;
	 
}

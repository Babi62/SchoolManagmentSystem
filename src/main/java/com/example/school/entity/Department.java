package com.example.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Department")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long depid;
	
	@Column(name="department_name", nullable=false)
	private String dep_name;	
}

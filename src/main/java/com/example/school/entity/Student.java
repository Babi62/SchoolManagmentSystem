package com.example.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sid;
	private String first_name;
	private String last_name;
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Department department;
	
}

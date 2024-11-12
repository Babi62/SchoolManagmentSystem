package com.example.school.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sid;
	private String first_name;
	private String last_name;
	private int age;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "depid")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Department department;
	
}

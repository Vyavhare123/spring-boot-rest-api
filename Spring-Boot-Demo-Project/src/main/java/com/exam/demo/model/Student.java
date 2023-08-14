package com.exam.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLL_NUMBER")
	private int rollNumber;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SURNAME")
	private String surName;
    @Column(name = "DIVision")
	private char division;
	@Column(name = "PERCENTAGE")
	private Double percentage;
	@Column(name = "STANDARD")
	private int standard;
	
	

}

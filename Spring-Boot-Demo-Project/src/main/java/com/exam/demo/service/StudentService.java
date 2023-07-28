package com.exam.demo.service;

import java.util.List;

import com.exam.demo.model.Student;

public interface StudentService {
	
    //save StudentInfo
	public Student saveStudentInfo(Student student);
	
	//update Student Info
	public List<Student> getAllStudent();
	
	// get studentInfo 
	public Student updateStudentInfo(Student student,int rollNumber);
	
    // Delete Student Info
	public String deleteStudent(int rollNumber);
	// get student by id;
	public Student getStudentById(int rollNumber);
	
	
}

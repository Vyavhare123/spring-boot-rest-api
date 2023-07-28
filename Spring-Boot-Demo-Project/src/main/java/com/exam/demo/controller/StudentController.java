package com.exam.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.model.Student;
import com.exam.demo.service.StudentService;

@RestController
public class StudentController {

	private StudentService studentService;
	
	//create Student
	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		Student createdStudent=studentService.saveStudentInfo(student);
		return new ResponseEntity<>(createdStudent,HttpStatus.CREATED);
		
	}
	//update Student info
	@PutMapping("/student/{rollnumber}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("rollnumber") int rollnumber){
		Student updatededStudent=studentService.updateStudentInfo(student,rollnumber);
		return new ResponseEntity<>(updatededStudent,HttpStatus.OK);
		
	}
	
	// Get all student information
		@GetMapping("/student/get/{rollnumber}")
		public ResponseEntity<Student>getStudentById(@PathVariable("rollnumber")int rollnumber){
			Student getOneStudent=studentService.getStudentById(rollnumber);
			return new ResponseEntity<>(getOneStudent,HttpStatus.FOUND);
		}
	
	// Get all student information
	@GetMapping("/student")
	public ResponseEntity<List<Student>>getAllStudent(){
		 List<Student>allStudent=studentService.getAllStudent();
		return new ResponseEntity<>(allStudent,HttpStatus.OK);
	}
	// delete student from database
	@DeleteMapping("/student/{rollnumber}")
	public ResponseEntity<String>deleteById(@PathVariable("rollnumber")int rollnumber){
		 String str=studentService.deleteStudent(rollnumber);
		return new ResponseEntity<>(str,HttpStatus.OK);
		
	}
}

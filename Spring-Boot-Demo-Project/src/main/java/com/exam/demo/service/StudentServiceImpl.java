package com.exam.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.demo.exception.StudentNotFoundException;
import com.exam.demo.model.Student;
import com.exam.demo.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired  
	private StudentRepository studentRepository;
	
	@Autowired Student student1;
    
	
	@Override
	public Student saveStudentInfo(Student student) {
		// TODO Auto-generated method stub
		Student saveStudent=studentRepository.save(student);
		return saveStudent;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student>getStudent=studentRepository.findAll();
		return getStudent;
	}

	@Override
	public Student updateStudentInfo(Student student, int rollNumber) {
		
	    Student stud=studentRepository.findById(rollNumber).orElseThrow(()-> new StudentNotFoundException("Student not found with ID: " + rollNumber));
	    stud.setName(student.getName());
	    stud.setRollNumber(student.getRollNumber());
	    stud.setSurname(student.getSurname());
	    stud.setPercentage(student.getPercentage());
	    stud.setDiv(student.getDiv());
	    stud.setStandard(student.getStandard());
	    return stud;
	}

	@Override
	public String deleteStudent(int rollNumber) {
		studentRepository.deleteById(rollNumber);
		return "Student Deleted Seccusfuuly";
	}

}

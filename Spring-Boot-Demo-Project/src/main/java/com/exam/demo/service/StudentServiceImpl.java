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
    
	
	@Override
	public Student saveStudentInfo(Student student) {
		// TODO Auto-generated method stub
		Student saveStudent1=studentRepository.save(student);
		return saveStudent1;
	}
	
	@Override
	public Student getStudentById(int rollNumber) {
		 Student stududentByid=studentRepository.findById(rollNumber).orElseThrow(()-> new StudentNotFoundException("Student not found with ID: " + rollNumber));
		return stududentByid;
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
	    //stud.setRollNumber(student.getRollNumber());
	    stud.setSurName(student.getSurName());
	    stud.setPercentage(student.getPercentage());
	    stud.setDivision(student.getDivision());
	    stud.setStandard(student.getStandard());
	    studentRepository.save(stud);
	    return stud;
	}

	@Override
	public String deleteStudent(int rollNumber) {
		studentRepository.deleteById(rollNumber);
		return "Student Deleted Seccusfuly";
	}

	

}

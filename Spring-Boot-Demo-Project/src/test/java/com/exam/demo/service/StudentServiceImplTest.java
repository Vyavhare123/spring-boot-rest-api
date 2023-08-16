package com.exam.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.exam.demo.model.Student;
import com.exam.demo.repository.StudentRepository;

@SpringBootTest
class StudentServiceImplTest {

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@BeforeEach
	public void setup() {
		Optional<Student> student = Optional.of(new Student(1, "Surekha", "Surekha", 'A', 10.500, 20));
		Mockito.when(studentRepository.findById(1)).thenReturn(student);
	}

	public static Student studentInfo() {
		Student saveStudent = new Student();
		saveStudent.setName("Amol");
		saveStudent.setRollNumber(2);
		saveStudent.setStandard(10);
		saveStudent.setDivision('A');
		saveStudent.setPercentage(50.200);
		saveStudent.setSurName("Patil");
		return saveStudent;

	}

	// test case for getStudentById
	@Test
	public void testGetStudentById() {
		String str = "Surekha";
		Student stud = studentService.getStudentById(1);
		assertEquals(stud.getName(), str);
	}

	// Test Case for Create new Student
	@Test
	public void testsaveStudent() {
		String str = "Amol";

		Mockito.when(studentRepository.save(StudentServiceImplTest.studentInfo()))
				.thenReturn(StudentServiceImplTest.studentInfo());
		Student stud = studentService.saveStudentInfo(StudentServiceImplTest.studentInfo());
		assertEquals(stud.getName(), str);
	}
	
	// Test case for update Student ById
		@Test
		public void tesTUpdateStudent() {
			Student studentGetById= studentService.getStudentById(1);
			System.out.println(studentGetById);
			studentGetById.setName("Narayan");
			Student updateStudent= studentService.updateStudentInfo(studentGetById,1);
			 assertNotNull(updateStudent);
			 assertEquals("Narayan", updateStudent.getName());
			
		}
	

	// Test case for Delete StudentById
	@Test
	public void testDeleteStudent() {
		String str1 = "Student Deleted Seccusfuly";
		String stud = studentService.deleteStudent(StudentServiceImplTest.studentInfo().getRollNumber());
		assertEquals(stud, str1);
	}

}

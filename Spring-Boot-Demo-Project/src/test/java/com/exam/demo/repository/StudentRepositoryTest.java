/**
 * 
 */
package com.exam.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.exam.demo.exception.StudentNotFoundException;
import com.exam.demo.model.Student;

/**
 * @author Admin
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private StudentRepository studentRepository;

	public Student studenTest() {
		Student student = new Student();
		student.setDivision('A');
		student.setName("Surekha");
		student.setPercentage(95.9900);
		student.setRollNumber(1);
		student.setStandard(10);
		student.setSurName("Patil");
		return student;
	}

	// Test case for Save Repository Method
	@Test
	void testCreateStudent() {
		StudentRepositoryTest objstudent = new StudentRepositoryTest();

		Student saveUser = studentRepository.save(objstudent.studenTest());
//		
		Student retriveUser = studentRepository.findById(saveUser.getRollNumber()).orElse(null);
		// Student retriveUser=entityManager.find(Student.class,
		// saveUser.getRollNumber());
		// assertThat(retriveUser).isEqualTo(saveUser);

		assertThat(retriveUser).isEqualTo(saveUser);

	}
	
	
	// Test case findById Repository Method
	@Test
	void testGetStudent() {
		StudentRepositoryTest objstudent = new StudentRepositoryTest();

		Student saveUser = studentRepository.save(objstudent.studenTest());
//		
		Student retriveUser = studentRepository.findById(saveUser.getRollNumber()).orElse(null);
		// Student retriveUser=entityManager.find(Student.class,
		// saveUser.getRollNumber());
		// assertThat(retriveUser).isEqualTo(saveUser);

		assertThat(retriveUser.getName()).isEqualTo("Surekha");

	}
	
	@Test
	void testupdateStudent() {
		StudentRepositoryTest objstudent = new StudentRepositoryTest();

		Student saveUser = studentRepository.save(objstudent.studenTest());
 		saveUser.setName("Amol");
		Student retriveUser = studentRepository.findById(saveUser.getRollNumber()).orElse(null);
		// Student retriveUser=entityManager.find(Student.class,
		// saveUser.getRollNumber());
		// assertThat(retriveUser).isEqualTo(saveUser);

		assertThat(retriveUser.getName()).isEqualTo(saveUser.getName());

	}
	
	@Test
	void testStudentNotFound() {
		StudentRepositoryTest objstudent = new StudentRepositoryTest();
         int id=115;
		Student saveUser = studentRepository.save(objstudent.studenTest());
//		
		Student retriveUser = studentRepository.findById(5).orElse(null);
		// Student retriveUser=entityManager.find(Student.class,
		// saveUser.getRollNumber());
		// assertThat(retriveUser).isEqualTo(saveUser);

		//assertThat(retriveUser).isEqualTo("Surekha");
		
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class, () -> {
			 studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found with ID: " + id));
		});
		
		String expetedMassage="Student not found with ID: " + id;
        String actualMassage=exception.getMessage();
        
        assert(actualMassage.contains(expetedMassage));
	}

}

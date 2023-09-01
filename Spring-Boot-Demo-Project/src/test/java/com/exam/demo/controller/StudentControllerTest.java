/**
 * 
 */
package com.exam.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.exam.demo.model.Student;
import com.exam.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Admin
 *
 */
@WebMvcTest(StudentController.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@Autowired
	private ObjectMapper objectMapper;

	private Student student;

	@BeforeEach

	public void setUP() {
		student = new Student(1, "Surekha", "Patil", 'A', 55.1000, 10);
		// Mockito.when(studentService.saveStudentInfo(student)).thenReturn(student);
	}
   // Test case for create Student
	@Test
	void testCreateStudentInfo() throws Exception {
		Student student1 = new Student(1, "Surekha", "Patil", 'A', 55.1000, 10);
		Mockito.when(studentService.saveStudentInfo(student1)).thenReturn(student);
//		mockMvc.perform(MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON)
//				.content("{\r\n" + "  \"rollNumber\": 1,\r\n" + "  \"name\": \"Surekha\",\r\n" + "  \"surName\": \"Patil\",\r\n"
//						+ "  \"division\": \"A\"\r\n" + "  \"percentage\": 55.1000,\r\n" + "  \"standard\": 10,\r\n" + "}"))
//				.andExpect(status().isCreated());
		// }

		mockMvc.perform(post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
				.writeValueAsString(student1))).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.rollNumber", is(1)))
				.andExpect(jsonPath("$.name", is("Surekha")))
				.andExpect(jsonPath("$.surName", is("Patil")))
				.andExpect(jsonPath("$.division", is("A")))
				.andExpect(jsonPath("$.percentage", is(55.1000)))
				.andExpect(jsonPath("$.standard", is(10)));

	}

	// Test case for getStudentById
	@Test
	public void testGetStudentById() throws Exception {
		int studentId = 1;
		Student student2 = new Student(1, "Surekha", "Patil", 'A', 55.1000, 10);
		Mockito.when(studentService.getStudentById(studentId)).thenReturn(student2);

		mockMvc.perform(get("/student/get/{rollnumber}", studentId))
		         .andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.rollNumber", is(1)))
				.andExpect(jsonPath("$.name", is("Surekha")))
				.andExpect(jsonPath("$.surName", is("Patil")))
				.andExpect(jsonPath("$.division", is("A")))
				.andExpect(jsonPath("$.percentage", is(55.1000)))
				.andExpect(jsonPath("$.standard", is(10)));
	}
	
	//Test case for ListOfStudnt from 
	@Test
	public void testAllStudent() throws Exception {
		List <Student>getAllStudent=Arrays.asList( 
				new Student(1, "Surekha", "Patil", 'A', 55.1000, 10),
				new Student(1, "Amol", "Patil", 'A', 55.1000, 10),
				new Student(1, "Ganesh", "Patil", 'A', 55.1000, 10));

		Mockito.when(studentService.getAllStudent()).thenReturn(getAllStudent);

		mockMvc.perform(get("/student"))
		.andExpect(status().isOk())
				.andExpect(content()
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()", is(3)))
				.andExpect(jsonPath("$[0].name", is("Surekha")))
				.andExpect(jsonPath("$[1].name", is("Amol")))
				.andExpect(jsonPath("$[2].name", is("Ganesh")));
				
	}
   
	// Test case for UpdateStudentInfo
	@Test
	public void testUpdateStudent() throws Exception {
		int studentId = 1;
		Student existingStudent = new Student(studentId, "Surekha", "Patil", 'A', 55.1000, 10);
		Student updatedStudent = new Student(studentId, "AMOL", "NALAGE", 'A', 55.1000, 10);
		Mockito.when(studentService.getStudentById(studentId)).thenReturn(existingStudent);
		Mockito.when(studentService.updateStudentInfo(updatedStudent,studentId)).thenReturn(updatedStudent);

		mockMvc.perform(put("/student/{rollnumber}",studentId)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(updatedStudent)))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$.rollNumber", is(studentId)))
	            .andExpect(jsonPath("$.name", is("AMOL"))).andExpect(jsonPath("$.surName", is("NALAGE")))
				.andExpect(jsonPath("$.division", is("A"))).andExpect(jsonPath("$.percentage", is(55.1000)))
				.andExpect(jsonPath("$.standard", is(10)));
	}
	
	// Test Case for Delete Student

	@Test
	public void testDeleteStudentById() throws Exception {
		int studentId = 1;
		String str = "Student Deleted Seccusfuly";
		Mockito.when(studentService.deleteStudent(studentId)).thenReturn(str);
		mockMvc.perform(delete("/student/{rollnumber}", studentId))
		.andExpect(status().isOk());
		verify(studentService, times(1)).deleteStudent(studentId);
	}
}

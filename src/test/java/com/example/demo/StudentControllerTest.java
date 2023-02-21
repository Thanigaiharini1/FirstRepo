package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.StudentController.StudentController;
import com.example.demo.StudentEntity.Student;
import com.example.demo.StudentService.StudentService;

@SpringBootTest
public class StudentControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentService;

	@Test
	public void GetStudentWithIdTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		Student student = new Student();
		student.setId(90);
		when(studentService.getStudentById(90)).thenReturn(new Student());

		mockMvc.perform(get("/getstudent/{id}", 90)).andExpect(status().isOk());
	}

	@Test
	public void addStudentTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		when(studentService.addstudent(any(Student.class))).thenReturn(new Student());

		mockMvc.perform(post("/addStudent").contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\":\"1\" ,\"name\":\"Harini\" ,\"emailId\":\"harini@gmail.com\",\"department\":\"CSE\",\"location\":\"Chennai\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteStudentWithIdTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		int id = 90;
		when(studentService.deleteStudent(id)).thenReturn("student deleted");

		mockMvc.perform(delete("/deleteStudent/{id}", id)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateMethodWithId() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		when(studentService.updateStudent(new Student(), 1)).thenReturn("updated Student");

		mockMvc.perform(
				MockMvcRequestBuilders.put("/updateStudent/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"name\":\"Harini\" ,\"emailId\":\"harini@gmail.com\",\"department\":\"CSE\",\"location\":\"Chennai\"}"))
				.andExpect(status().isOk());
	}
	
	private List<Student> expectedStudents;

	  @Test
	  public void testGetUsers() throws Exception {
		  expectedStudents = new ArrayList<>();
		  expectedStudents.add(new Student(67, "gowri", "gowri@gmail.com", "IT", "Chennai"));
		  expectedStudents.add(new Student(78, "gowri", "gowri@gmail.com", "IT", "Chennai"));
		 mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	    when(studentService.getAllStudent()).thenReturn(expectedStudents);
	    
	    List<Student> actualStudents = studentService.getAllStudent();
	    mockMvc.perform(MockMvcRequestBuilders.get("/allstudents"))
	    .andExpect(status().isOk());
	    assertEquals(expectedStudents, actualStudents);
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

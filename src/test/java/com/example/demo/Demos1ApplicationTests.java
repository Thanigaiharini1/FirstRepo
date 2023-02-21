package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.StudentEntity.Student;
import com.example.demo.StudentRepositry.StudentRepositry;
import com.example.demo.StudentService.StudentService;

@SpringBootTest
public class Demos1ApplicationTests {

	@InjectMocks
	private StudentService studentService;

	@Mock
	StudentRepositry studentRepository;

	

	@Test
	public void getAllStudentTest() {
		when(studentRepository.findAll())
				.thenReturn(Stream
						.of(new Student(34, "Harini", "harini@gmail.com", "CSE", "Chennai"),
								new Student(56, "Jana", "jana@gmail.com", "CSE", "Bangalore"))
						.collect(Collectors.toList()));
		assertEquals(2, studentService.getAllStudent().size());
	}

	@Test
	public void saveStudentTest() {
		Student student = new Student(34, "Harini", "harini@gmail.com", "CSE", "Chennai");
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentService.addstudent(student));
	}

	@Test
	public void getStudentByIdTest() {
		Student student = new Student();
		student.setId(90);
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		assertEquals(student, studentService.getStudentById(student.getId()));//excepted,actual
		}

	@Test
	public void deleteUserTest() {
		Student student = new Student();
		student.setId(10);
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		String actual = studentService.deleteStudent(student.getId());
		assertNotEquals(10,actual);
		verify(studentRepository,times(1)).findById(student.getId());
		}
	
	@Test
	public void updateStudentByIdTest() {
		Student student = new Student(34, "Harini", "harini@gmail.com", "CSE", "Chennai");
		Student editstudent = new Student(34, "gowri", "gowri@gmail.com", "IT", "Chennai");
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		 studentRepository.save(student);
		assertEquals("updated Student",studentService.updateStudent(editstudent, editstudent.getId()));
		
		
		
		
		}
	}
	

		

		

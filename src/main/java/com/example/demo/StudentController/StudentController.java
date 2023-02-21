package com.example.demo.StudentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StudentEntity.Student;
import com.example.demo.StudentException.StudentNotFoundException;
import com.example.demo.StudentService.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Operation(summary = "Add Student",
    responses = {
            @ApiResponse(description = "Student Details Added",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student not found")})
    @PostMapping("/addStudent")
	
	public Student addstudent(@Valid @RequestBody Student student) {
		return studentService.addstudent(student);
	
	}

	    @Operation(summary = "Get all students",
	            responses = {
	                    @ApiResponse(description = " List of all the Students available",
	                            content = @Content(mediaType = "application/json")),
	                    @ApiResponse(responseCode = "404", description = "Student not found")})
	@GetMapping("/allstudents")
	public ResponseEntity<List<Student>> getAllStudent() throws StudentNotFoundException {
		return new ResponseEntity<List<Student>>(studentService.getAllStudent(), HttpStatus.OK);
	}

	// get student by id
	    @Operation(summary = "Get students with id ",
	            responses = {
	                    @ApiResponse(description = "Getting students with id ",
	                            content = @Content(mediaType = "application/json")),
	                    @ApiResponse(responseCode = "404", description = "Student not found with that id")})
	@GetMapping("/getstudent/{id}")
	public Student getStudentById(@PathVariable(value = "id") Integer id) {
		return studentService.getStudentById(id);
	}

	// update student
	    @Operation(summary = "Update Student details with id",
	            responses = {
	                    @ApiResponse(description = "Updated student details",
	                            content = @Content(mediaType = "application/json")),
	                    @ApiResponse(responseCode = "404", description = "Student not found with that id")})
	@PutMapping("/updateStudent/{id}")
	public String updateStudent(@RequestBody Student student, @PathVariable(value = "id") Integer id) {
		return studentService.updateStudent(student, id);

	}

	    @Operation(summary = "Delete Student details with id",
	            responses = {
	                    @ApiResponse(description = "Deleted Successfully",
	                            content = @Content(mediaType = "application/json")),
	                    @ApiResponse(responseCode = "404", description = "Student not found with that id")})
	@DeleteMapping("/deleteStudent/{id}")
	//public String deleteStudent(@RequestParam Integer id) {
	public String deleteStudent(@PathVariable(value = "id") Integer id) {
		  studentService.deleteStudent(id);
		 return "Student deleted";
	}
}

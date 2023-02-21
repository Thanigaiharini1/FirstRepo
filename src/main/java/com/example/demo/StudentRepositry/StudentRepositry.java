package com.example.demo.StudentRepositry;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.StudentEntity.Student;

public interface StudentRepositry extends JpaRepository<Student, Integer> {





}

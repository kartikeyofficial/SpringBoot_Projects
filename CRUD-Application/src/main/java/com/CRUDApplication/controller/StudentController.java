package com.CRUDApplication.controller;

import com.CRUDApplication.entity.Student;
import com.CRUDApplication.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping("/create")
    public String createStudent(@RequestBody Student student){
       Student createdStudent = studentService.createStudent(student);
       return "Student Created";
    }

}

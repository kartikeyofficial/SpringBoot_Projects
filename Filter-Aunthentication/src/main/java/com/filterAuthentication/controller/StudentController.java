package com.filterAuthentication.controller;

import com.filterAuthentication.dto.Student;
import com.filterAuthentication.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq){
       Student createdStudent =  studentService.createStudent(studentReq);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);

    }
}

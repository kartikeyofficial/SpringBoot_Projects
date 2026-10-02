package com.CRUDApplication.controller;

import com.CRUDApplication.entity.Student;
import com.CRUDApplication.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

       Student createdStudent = studentService.createStudent(student);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(createdStudent);
    }

    // Read One
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
         Student studentResp = studentService.getStudent(id);
         if (studentResp == null){
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok(studentResp);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentsList = studentService.getAllStudent();
         if (studentsList.isEmpty()){
             return ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(studentsList);


    }

}

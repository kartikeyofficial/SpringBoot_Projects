package com.DTOsValidations.controller;


import com.DTOsValidations.dto.CreateStudentRequestDTO;
import com.DTOsValidations.dto.CreateStudentResponseDTO;
import com.DTOsValidations.dto.UpdateStudentRequestDTO;
import com.DTOsValidations.dto.UpdateStudentResponseDTO;
import com.DTOsValidations.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO){
        CreateStudentResponseDTO createdStudent = studentService.createStudent(createStudentRequestDTO);

        return ResponseEntity.ok(createdStudent);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long id){
        CreateStudentResponseDTO studentResp = studentService.getStudent(id);

        return ResponseEntity.ok(studentResp);

    }
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
        List<CreateStudentResponseDTO> studentsList = studentService.getAllStudent();

        return ResponseEntity.ok(studentsList);


    }
    // update
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> Update(@PathVariable Long id,@Valid @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO){
        UpdateStudentResponseDTO studentResp = studentService.updateStudent(id,updateStudentRequestDTO);


        return ResponseEntity.ok(studentResp);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);


        return ResponseEntity
                .noContent()
                .build();
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllStudents(){
        studentService.deleteAllStudents();

        return ResponseEntity.ok("All Records Are Deleted");
    }
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
       studentService.deleteStudentSoftly(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

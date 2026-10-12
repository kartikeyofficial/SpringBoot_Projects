package com.DTOsValidations.controller;


import com.DTOsValidations.dto.CreateStudentRequestDTO;
import com.DTOsValidations.dto.CreateStudentResponseDTO;
import com.DTOsValidations.entity.Student;
import com.DTOsValidations.service.StudentService;
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

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@RequestBody CreateStudentRequestDTO createStudentRequestDTO){
//        studentRequestDTO.setDeleted(false);
        CreateStudentResponseDTO createdStudent = studentService.createStudent(createStudentRequestDTO);

        return ResponseEntity.ok(createdStudent);
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
    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> Update(@PathVariable Long id, @RequestBody Student studentReq){
        Student studentResp = studentService.updateStudent(id,studentReq);
        if (studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record is Deleted");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllStudents(){
        studentService.deleteAllStudents();

        return ResponseEntity.ok("All Records Are Deleted");
    }
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Student data Deleted Softly Successfully");
    }
}

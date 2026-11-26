package com.AOP.service;

import com.AOP.entity.Student;
import com.AOP.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
   private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        System.out.println("Student Saved");
        Student studentResp = studentRepository.save(studentReq);

        return studentResp;
    }
}

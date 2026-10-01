package com.CRUDApplication.service;

import com.CRUDApplication.entity.Student;
import com.CRUDApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        System.out.println("Student Service");
        Student studentResp = studentRepository.saveStudent(studentReq);
        System.out.println("Exit Student service");
        return studentResp;

    }

}

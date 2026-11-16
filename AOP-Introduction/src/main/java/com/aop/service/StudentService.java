package com.aop.service;

import com.aop.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public void createStudent(Student student){
        System.out.println("Student is saved successfully");
    }

}

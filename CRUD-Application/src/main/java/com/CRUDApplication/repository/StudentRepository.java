package com.CRUDApplication.repository;

import com.CRUDApplication.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentRepository {
    public Student saveStudent(Student studentReq){
        System.out.println("Student Repository");
        return  null;
    }
}

package com.CRUDApplication.service;

import com.CRUDApplication.entity.Student;
import com.CRUDApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;

    }
    public Student getStudent(Long id){
       Optional<Student> studentResp = studentRepository.findById(id);
       if (studentResp.isPresent()){
           return studentResp.get();
       }
       return null;
    }

    public List<Student> getAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        return studentList;
    }

}

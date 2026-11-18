package com.aop.service;

import com.aop.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LoggingDecorator implements StudentService{

    StudentServiceImpl studentServiceimpl;

    public LoggingDecorator(StudentServiceImpl studentServiceimpl) {
        this.studentServiceimpl = studentServiceimpl;
    }

    @Override
    public void createStudent(Student student) {
        // Logging Related Logic
        LoggingServiceUtil.logStart("StudentServiceImpl","createStudent");

        studentServiceimpl.createStudent(student);

        LoggingServiceUtil.logEnd("StudentServiceImpl","createStudent");
    }
}

package com.AOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
// Advice
    @Before("execution(Student com.AOP.service.StudentService.createStudent())")
    public void logBeforeMethod(){
        System.out.println("Student is Going to be Saved");
    }

}

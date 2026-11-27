package com.AOP.aspect;

import com.AOP.entity.Student;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
// Advice
    @AfterReturning(
            value = "execution(* com.AOP.service.StudentService." +
                    "createStudent(..))",
            returning = "result")
    public void logBeforeMethod(Student result){

        System.out.println("Student is Going to be Saved");

        result.setName("Kumar Kartikey");
        result.setEmail("rajputlartikey10@gmail.com");
        result.setCourse("CSE");
        result.setRollNo(73);

        System.out.println("intercepted createStudent()");
    }

}

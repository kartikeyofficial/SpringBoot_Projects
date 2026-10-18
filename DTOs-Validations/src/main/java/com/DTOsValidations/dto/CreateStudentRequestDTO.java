package com.DTOsValidations.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name can't be Empty or Blank")
    @Size(min = 2,max = 50, message = "Student name must be within 2 to 50 character Long")
    private String name;
    @Min(value = 18, message = "Age must be 18 or above")
    private int age;
    @NotBlank(message = "Please Enter the Email")
    @Email(message = "Please Enter Valid Email")
    private String email;
    @NotNull(message = "Please Enter Roll No.")
    private Integer rollNo;
    @NotBlank(message = "Please Enter Subject Name")
    private String subject;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

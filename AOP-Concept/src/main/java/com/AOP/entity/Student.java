package com.AOP.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "studentAOP")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int rollNo;
    private String email;
    private String course;

//    public Student(long id, String name, int rollNo, String email, String course) {
//        this.id = id;
//        this.name = name;
//        this.rollNo = rollNo;
//        this.email = email;
//        this.course = course;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

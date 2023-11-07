package com.example.studentcourse.model;

import lombok.Data;

@Data
public class StudentModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long age;
    private String cardNumber;
}

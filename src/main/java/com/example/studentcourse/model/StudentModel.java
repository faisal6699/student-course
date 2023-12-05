package com.example.studentcourse.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long age;
    private List<CourseModel> courseModels;
}

package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String SQL_INSERT_STUDENT = "insert into student(first_name, last_name, email, age) values (?, ?, ?, ?)";

    @Autowired
    public StudentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createStudent(Student student) {
        return jdbcTemplate.update(
                SQL_INSERT_STUDENT,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAge()
        );
    }
}

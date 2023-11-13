package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT distinct s, b, ic FROM Student s " +
            "LEFT JOIN s.books b " +
            "LEFT JOIN s.idCard ic where s.id = :id")
    Student getStudent(Long id);
}

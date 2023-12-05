package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Enrollment;
import com.example.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
//    @Query("select distinct s from Student s join Enrollment e on e.student.id = s.id where s.id = :id")
//    List<?> getStudentCourse(Long id);
//
//    List<Course> findCourseBy
}

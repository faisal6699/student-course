package com.example.studentcourse.service;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.exception.NotFoundException;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("course with " + id + " not found");
        });
    }

//    public List<?> getEnrolledCourses(Long id) {
//        List<?> studentCourses = enrollmentRepository.getEnrollmentByStudentId(id);
//        return studentCourses;
//    }
}

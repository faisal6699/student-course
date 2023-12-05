package com.example.studentcourse.service;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Enrollment;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Course> getAllEnrollmentOfStudent(Long id) {
        List<Course> courses = new ArrayList<>();
        List<Enrollment> enrollments = this.enrollmentRepository.findEnrollmentByStudentId(id);
        if (!enrollments.isEmpty()) {
            enrollments.stream().forEach(enrollment -> {
                courses.add(enrollment.getCourse());
            });
        }
        return courses;
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Student> getAllEnrolledStudent(Long courseId) {
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = this.enrollmentRepository.findEnrollmentByCourseId(courseId);
        if (!enrollments.isEmpty()) {
            enrollments.stream().forEach(enrollment -> {
                students.add(enrollment.getStudent());
            });
        }
        return students;
    }
}

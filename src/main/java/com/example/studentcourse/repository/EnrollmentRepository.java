package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentByStudentId(Long studentId);
    List<Enrollment> findEnrollmentByCourseId(Long courseId);
}

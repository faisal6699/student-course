package com.example.studentcourse.service;

import com.example.studentcourse.entity.Student;
import com.example.studentcourse.exception.NotFoundException;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("student with " + id + " not found");
        });
    }

    public Student getStudentAlongIdCardAndBooks(Long id) {
        Optional<Student> student = studentRepository.findById(id);

//        return studentRepository.getStudent(id);
        return student.get();
    }

//    public void removeBookOfStudent(Long bookId, Long studentId) {
//        studentRepository.
//    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

}

package com.example.studentcourse.service;

import com.example.studentcourse.entity.IdCard;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.exception.NotFoundException;
import com.example.studentcourse.model.StudentModel;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Student save(Student student) {
        return studentRepository.save(student);
    }

}

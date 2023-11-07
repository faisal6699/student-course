package com.example.studentcourse.controller;

import com.example.studentcourse.entity.IdCard;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.exception.ApiRequestException;
import com.example.studentcourse.exception.DuplicateKeyException;
import com.example.studentcourse.model.StudentModel;
import com.example.studentcourse.service.IdCardService;
import com.example.studentcourse.service.StudentService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final IdCardService idCardService;

    @Autowired
    public StudentController(StudentService studentService, IdCardService idCardService) {
        this.studentService = studentService;
        this.idCardService = idCardService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping()
    public ResponseEntity<?> createNewStudent(@RequestBody @Valid StudentModel studentModel) {
        try {
            Student student = new Student();
            student.setFirstName(studentModel.getFirstName());
            student.setLastName(studentModel.getLastName());
            student.setEmail(studentModel.getEmail());
            student.setAge(studentModel.getAge());

            IdCard idCard = new IdCard();
            idCard.setCardName(studentModel.getCardNumber());
            idCard.setStudent(student);
            idCardService.save(idCard);
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException(e.getMessage());
        } catch (ConstraintViolationException e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/idCards")
    public ResponseEntity<?> getAllIdCardWithStudent() {
            return ResponseEntity.ok(idCardService.getAllIDCards());
    }
}

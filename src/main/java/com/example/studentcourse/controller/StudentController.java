package com.example.studentcourse.controller;

import com.example.studentcourse.entity.Book;
import com.example.studentcourse.entity.IdCard;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.model.BookModel;
import com.example.studentcourse.model.IdCardModel;
import com.example.studentcourse.model.StudentModel;
import com.example.studentcourse.service.BookService;
import com.example.studentcourse.service.IdCardService;
import com.example.studentcourse.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final IdCardService idCardService;
    private final BookService bookService;

    @Autowired
    public StudentController(StudentService studentService, IdCardService idCardService, BookService bookService) {
        this.studentService = studentService;
        this.idCardService = idCardService;
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping()
    public ResponseEntity<?> createNewStudent(@RequestBody @Valid StudentModel studentModel) {
            Student student = new Student();
            student.setFirstName(studentModel.getFirstName());
            student.setLastName(studentModel.getLastName());
            student.setEmail(studentModel.getEmail());
            student.setAge(studentModel.getAge());
            studentService.save(student);
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @GetMapping("/idCards")
    public ResponseEntity<?> getAllIdCardWithStudent() {
            return ResponseEntity.ok(idCardService.getAllIDCards());
    }

    @PostMapping(path = "/addBook/{studentId}")
    public ResponseEntity<?> addNewBook(@PathVariable Long studentId, @RequestBody @Valid BookModel bookModel) {
        Student student = studentService.getStudentById(studentId);
        Book book = new Book();
        book.setBookName(bookModel.getBookName());
        book.setCreatedAt(LocalDateTime.now());
        book.setStudent(student);
        bookService.save(book);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping(path = "addIdCard/{studentId}")
    public ResponseEntity<?> addNewIdCard(@PathVariable Long studentId, @RequestBody @Valid IdCardModel idCardModel) {
        Student student = studentService.getStudentById(studentId);
        IdCard idCard = new IdCard();
        idCard.setCardName(idCardModel.getCardName());
        idCard.setStudent(student);

        return new ResponseEntity<>(idCardService.save(idCard), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<?> getStudentWithIdCardAndBooks(@PathVariable Long studentId) {
        return new  ResponseEntity<>(studentService.getStudentAlongIdCardAndBooks(studentId), HttpStatus.FOUND);
    }
}

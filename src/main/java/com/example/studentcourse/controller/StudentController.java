package com.example.studentcourse.controller;

import com.example.studentcourse.entity.*;
import com.example.studentcourse.model.BookModel;
import com.example.studentcourse.model.CourseModel;
import com.example.studentcourse.model.IdCardModel;
import com.example.studentcourse.model.StudentModel;
import com.example.studentcourse.repository.StudentJdbcRepository;
import com.example.studentcourse.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final IdCardService idCardService;
    private final BookService bookService;

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final StudentJdbcRepository studentJdbcRepository;

    @Autowired
    public StudentController(StudentService studentService, IdCardService idCardService, BookService bookService, CourseService courseService, EnrollmentService enrollmentService, StudentJdbcRepository studentJdbcRepository) {
        this.studentService = studentService;
        this.idCardService = idCardService;
        this.bookService = bookService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.studentJdbcRepository = studentJdbcRepository;
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
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
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

    @PostMapping(path = "addCourse")
    public ResponseEntity<?> addCourse(@RequestBody @Valid CourseModel courseModel) {
        Course course = new Course();
        course.setName(courseModel.getName());
        course.setDepartment(courseModel.getDepartment());
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED) ;
    }

    @PostMapping(path = "enrollCourse/{studentId}/{courseId}")
    public ResponseEntity<?> enrollCourseToStudent(@PathVariable Long studentId,
                                                @PathVariable Long courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(new EnrollmentId(student.getId(), course.getId()));
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        return new ResponseEntity<>(enrollmentService.save(enrollment), HttpStatus.CREATED);
    }

    @GetMapping(path = "enrolledCourses/{studentId}")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Long studentId) {
        HashMap<String, Object> studentWithCourses = new HashMap<>();
        List<Course> courses = enrollmentService.getAllEnrollmentOfStudent(studentId);
        Student student = studentService.getStudentById(studentId);
        studentWithCourses.put("student", student);
        studentWithCourses.put("courses", courses);
        return new ResponseEntity<>(studentWithCourses, HttpStatus.FOUND);
    }

    @GetMapping(path = "studentsEnrolledToCourse/{courseId}")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Long courseId) {
        return new ResponseEntity<>(enrollmentService.getAllEnrolledStudent(courseId), HttpStatus.FOUND);
    }

    @PutMapping(path = "bulkStudentUpload")
    public ResponseEntity<?> uploadBulkStudent(@RequestBody @Valid StudentModel[] studentModels) {
        Arrays.stream(studentModels).forEach(studentModel -> {
            Student student = new Student();
            student.setFirstName(studentModel.getFirstName());
            student.setLastName(studentModel.getLastName());
            student.setEmail(studentModel.getEmail());
            student.setAge(studentModel.getAge());
            studentJdbcRepository.createStudent(student);
        });

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
}

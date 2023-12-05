package com.example.studentcourse.entity;

import com.example.studentcourse.model.IdCardModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email"
        )
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false
    )
    @NotBlank(message = "Name is required")
    private String firstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Long age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Column(
            nullable = false
    )
    private String lastName;
    @Column(
            nullable = false
    )
    private String email;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Column(
            nullable = false
    )
    private Long age;

//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }
//
//    public void addBook(Book book) {
//        if(!this.books.contains(book)) {
//            this.books.add(book);
//        }
//    }
//
//    public void removeBook(Book book) {
//        if(this.books.contains(book)) {
//            this.books.remove(book);
//        }
//    }
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(
//            name = "book_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "book_id_fk"
//            )
//    )
//    private List<Book> books = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(
//            name = "id_card_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "id_card_fk"
//            )
//    )
//    private IdCard idCard;
//
//    public IdCard getIdCard() {
//        return idCard;
//    }
//
//    public void setIdCard(IdCard idCard) {
//        this.idCard = idCard;
//    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "enrollment_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "enrollment_id_fk"
            )
    )
    @JsonIgnore
    private List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        if(!enrollments.contains(enrollment)) {
            this.enrollments.add(enrollment);
        }
    }

    public void removeEnrollment(Enrollment enrollment) {
            this.enrollments.remove(enrollment);
    }
}

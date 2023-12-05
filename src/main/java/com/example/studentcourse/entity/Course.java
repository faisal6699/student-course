package com.example.studentcourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "enrollment_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "enrollment_id_fk"
            )
    )
    @JsonIgnore
    private List<Enrollment> enrolled = new ArrayList<>();

    public List<Enrollment> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Enrollment> enrolled) {
        this.enrolled = enrolled;
    }
}

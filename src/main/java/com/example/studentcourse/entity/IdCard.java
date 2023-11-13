package com.example.studentcourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "IdCard")
public class IdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false
    )
    private String cardName;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "studentId",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "id_fk")
    )
    @JsonIgnore
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "idCard{" +
                "id=" + id +
                ", cardName='" + cardName + '\'' +
                '}';
    }

    public IdCard() {
    }
}

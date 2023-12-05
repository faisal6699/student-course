package com.example.studentcourse.repository;

import com.example.studentcourse.entity.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdCardRepository extends JpaRepository<IdCard, Long> {

}

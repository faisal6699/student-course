package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {
    Long countByBookAndReturned(Book book, Integer returned);
}

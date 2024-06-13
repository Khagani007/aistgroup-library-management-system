package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.IssuedBook;
import com.example.librarymanagementsystem.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedBookService {
    private final IssuedBookRepository issuedBookRepository;

    @Autowired
    public IssuedBookService(IssuedBookRepository issuedBookRepository) {
        this.issuedBookRepository = issuedBookRepository;
    }

    public List<IssuedBook> getAll() {
        return issuedBookRepository.findAll();
    }

    public IssuedBook get(Long id) {
        return issuedBookRepository.findById(id).get();
    }

    public Long getCountByBook(Book book) {
        return issuedBookRepository.countByBookAndReturned(book, AppConstants.BOOK_NOT_RETURNED);
    }

    public IssuedBook save(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    public IssuedBook addNew(IssuedBook issuedBook) {
        issuedBook.setReturned(AppConstants.BOOK_NOT_RETURNED);
        return issuedBookRepository.save(issuedBook);
    }

}

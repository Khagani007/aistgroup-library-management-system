package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final IssuedBookService issuedBookService;

    @Autowired
    public BookService(BookRepository bookRepository, IssuedBookService issuedBookService) {
        this.bookRepository = bookRepository;
        this.issuedBookService = issuedBookService;
    }

    public Long getTotalCount() {
        return bookRepository.count();
    }

    public Long getTotalIssuedBooks() {
        return bookRepository.countByStatus(AppConstants.BOOK_STATUS_ISSUED);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book get(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book getByTag(String tag) {
        return bookRepository.findByTag(tag);
    }

    public List<Book> get(List<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    public List<Book> getByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> getAvailableByCategory(Category category) {
        return bookRepository.findByCategoryAndStatus(category, AppConstants.BOOK_STATUS_AVAILABLE);
    }

    public Book addNew(Book book) {
        book.setCreateDate(new Date());
        book.setStatus(AppConstants.BOOK_STATUS_AVAILABLE);
        return bookRepository.save(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean hasUsage(Book book) {
        return issuedBookService.getCountByBook(book) > 0;
    }
}

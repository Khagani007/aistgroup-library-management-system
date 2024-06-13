package com.example.librarymanagementsystem.restcontroller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/book")
public class BookRestController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookRestController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"/", "/list"})
    public List<Book> all() {
        return bookService.getAll();
    }

    @GetMapping(value = "/{id}/list")
    public List<Book> get(@PathVariable(name = "id") Long id) {
        Category category = categoryService.get(id);
        return bookService.getByCategory(category);
    }

    @GetMapping(value = "/{id}/available")
    public List<Book> getAvailableBooks(@PathVariable(name = "id") Long id) {
        Category category = categoryService.get(id);
        return bookService.getAvailableByCategory(category);
    }

}

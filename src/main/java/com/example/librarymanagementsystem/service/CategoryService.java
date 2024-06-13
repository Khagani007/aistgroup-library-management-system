package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Long getTotalCount() {
        return categoryRepository.count();
    }

    public List<Category> getAllBySort() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category addNew(Category category) {
        category.setCreateDate(new Date());
        return categoryRepository.save(category);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public boolean hasUsage(Category category) {
        return !category.getBooks().isEmpty();
    }
}

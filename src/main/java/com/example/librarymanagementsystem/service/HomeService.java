package com.example.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public HomeService(MemberService memberService, CategoryService categoryService, BookService bookService) {
        this.memberService = memberService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    public Map<String, Long> getTopTilesMap() {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("totalMembers", memberService.getTotalCount());
        map.put("totalStudents", memberService.getStudentsCount());
        map.put("totalParents", memberService.getParentsCount());
        map.put("totalCategories", categoryService.getTotalCount());
        map.put("totalBooks", bookService.getTotalCount());
        map.put("totalIssuedBooks", bookService.getTotalIssuedBooks());
        return map;
    }

}

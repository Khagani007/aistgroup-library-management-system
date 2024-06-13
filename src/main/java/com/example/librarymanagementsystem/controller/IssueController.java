package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.service.CategoryService;
import com.example.librarymanagementsystem.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {
    private final IssueService issueService;
    private final CategoryService categoryService;

    @Autowired
    public IssueController(IssueService issueService, CategoryService categoryService) {
        this.issueService = issueService;
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "memberTypes")
    public List<String> memberTypes() {
        return AppConstants.MEMBER_TYPES;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getAllBySort();
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listIssuePage(Model model) {
        model.addAttribute("issues", issueService.getAllUnreturned());
        return "/issue/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newIssuePage(Model model) {
        return "/issue/form";
    }

}

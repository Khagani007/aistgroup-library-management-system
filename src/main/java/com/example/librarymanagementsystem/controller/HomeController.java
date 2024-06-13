package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("topTiles", homeService.getTopTilesMap());
        return "home";
    }

}

package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.service.CategoryService;
import com.blogapp.Scribble_Hub.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


}

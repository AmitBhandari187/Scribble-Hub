package com.blogapp.Scribble_Hub.service;

import com.blogapp.Scribble_Hub.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO , Long categoryId);
    CategoryDTO getCategoryById(Long categoryId);
    List<CategoryDTO> getAllCategory();
}

package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.payloads.ApiResponse;
import com.blogapp.Scribble_Hub.payloads.CategoryDTO;
import com.blogapp.Scribble_Hub.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // create
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createdCategoryDto=this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> allCategories=this.categoryService.getAllCategory();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId){
        CategoryDTO getCategoryById=this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(getCategoryById,HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId , @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updatecategoryDto=this.categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(updatecategoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully!!",true),
                HttpStatus.OK);
    }

}

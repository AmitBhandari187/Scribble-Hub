package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.Category;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.CategoryDTO;
import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.repositories.CategoryRepository;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=this.dtoToCategory(categoryDTO);
        Category savedCategory=this.categoryRepository.save(category);

        return categoryToDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "category",
                        "categoryId",
                        categoryId));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updatedCategory = this.categoryRepository.save(category);

        return categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories=this.categoryRepository.findAll();
        List <CategoryDTO> categoryDTOS=categories.stream().map(this::categoryToDto).toList();
        return categoryDTOS;
    }

    private Category dtoToCategory(CategoryDTO categoryDTO){
        Category category=this.modelMapper.map(categoryDTO,Category.class);
        return category;
    }

    private CategoryDTO categoryToDto(Category category){
        CategoryDTO categoryDTO=this.modelMapper.map(category,CategoryDTO.class);

        return categoryDTO;
    }
}

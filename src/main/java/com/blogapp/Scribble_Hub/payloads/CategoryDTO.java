package com.blogapp.Scribble_Hub.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDTO {

    private Long categoryId;

    @NotBlank(message = "Category title cannot be empty")
    @Size(
            min = 3,
            max = 50,
            message = "Category title must be between 3 and 50 characters"
    )
    private String categoryTitle;

    @NotBlank(message = "Category description cannot be empty")
    @Size(
            min = 10,
            max = 200,
            message = "Category description must be between 10 and 200 characters"
    )
    private String categoryDescription;
}
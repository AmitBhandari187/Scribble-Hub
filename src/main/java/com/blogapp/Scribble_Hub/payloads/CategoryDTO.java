package com.blogapp.Scribble_Hub.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryTitle;
    private String categoryDescription;
}

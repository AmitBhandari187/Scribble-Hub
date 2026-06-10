package com.blogapp.Scribble_Hub.repositories;

import com.blogapp.Scribble_Hub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

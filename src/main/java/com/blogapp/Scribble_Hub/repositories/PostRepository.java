package com.blogapp.Scribble_Hub.repositories;

import com.blogapp.Scribble_Hub.entity.Category;
import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

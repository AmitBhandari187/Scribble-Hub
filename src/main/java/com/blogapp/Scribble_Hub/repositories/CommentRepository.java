package com.blogapp.Scribble_Hub.repositories;


import com.blogapp.Scribble_Hub.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}

package com.blogapp.Scribble_Hub.repositories;

import com.blogapp.Scribble_Hub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
}

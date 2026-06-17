package com.blogapp.Scribble_Hub.service;

import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO ,Long userId,Long categoryId);
    Post updatePost(PostDTO postDTO , Long postId);
    void deletePost(Long postId);
    List<PostDTO> getAllPosts();
    Post getPostById(Long postId);

    // Now get posts by category
    List<PostDTO> findByCategory(Long categoryId);

    // Now get all posts by user
    List<PostDTO> findByUser(Long userId);

    // Search posts by keyword
    List<PostDTO> searchPosts(String keyword);
}

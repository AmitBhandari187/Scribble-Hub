package com.blogapp.Scribble_Hub.service;

import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO ,Long userId,Long categoryId);
    PostDTO updatePost(PostDTO postDTO , Long postId);
    void deletePost(Long postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize);
    PostDTO getPostById(Long postId);

    // Now get posts by category
    PostResponse findByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

    // Now get all posts by user
    PostResponse findByUser(Long userId,Integer pageNumber , Integer pageSize);

    // Search posts by keyword
    List<PostDTO> searchPosts(String keyword);
}

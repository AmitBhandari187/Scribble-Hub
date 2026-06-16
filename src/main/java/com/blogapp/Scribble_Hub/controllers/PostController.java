package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.service.CategoryService;
import com.blogapp.Scribble_Hub.service.Impl.PostServiceImpl;
import com.blogapp.Scribble_Hub.service.PostService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {

        this.postService = postService;
    }

    // create posts
    @PostMapping("/userId/{userId}/categoryId/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Long userId , @PathVariable Long categoryId){
        PostDTO createdPost=this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<PostDTO>(createdPost, HttpStatus.CREATED);
    }

}

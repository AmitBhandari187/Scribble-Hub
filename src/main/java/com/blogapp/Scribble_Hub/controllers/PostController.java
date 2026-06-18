package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.payloads.ApiResponse;
import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.service.CategoryService;
import com.blogapp.Scribble_Hub.service.Impl.PostServiceImpl;
import com.blogapp.Scribble_Hub.service.PostService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Get user posts
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Long userId){
        List<PostDTO> userPosts=this.postService.findByUser(userId);
        return new ResponseEntity<List<PostDTO>>(userPosts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Long categoryId){
        List<PostDTO> categoryPosts=this.postService.findByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(categoryPosts,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId){
        PostDTO postDTO=this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> allPosts=this.postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(allPosts,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO ,@PathVariable Long postId){
        PostDTO updatedPost=this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

}

package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.config.AppConstants;
import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.payloads.ApiResponse;
import com.blogapp.Scribble_Hub.payloads.ImageResponse;
import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.payloads.PostResponse;
import com.blogapp.Scribble_Hub.service.CategoryService;
import com.blogapp.Scribble_Hub.service.FileService;
import com.blogapp.Scribble_Hub.service.Impl.PostServiceImpl;
import com.blogapp.Scribble_Hub.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    private PostService postService;
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    public PostController(PostService postService, FileService fileService) {

        this.postService = postService;
        this.fileService=fileService;
    }

    // create posts
    @PostMapping("/userId/{userId}/categoryId/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Long userId , @PathVariable Long categoryId){
        PostDTO createdPost=this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<PostDTO>(createdPost, HttpStatus.CREATED);
    }

    //Get user posts
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(
            @PathVariable Long userId,
            @RequestParam (value="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize
    ){
        PostResponse userPosts=this.postService.findByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(userPosts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(
            @PathVariable Long categoryId,
            @RequestParam (value="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize

    ){
        PostResponse categoryPosts=this.postService.findByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(categoryPosts,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId){
        PostDTO postDTO=this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam (value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue =AppConstants.SORT_BY,required = false )String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false)String sortDirection
            ){
        PostResponse postResponse=this.postService.getAllPosts(pageNumber , pageSize, sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO ,@PathVariable Long postId){
        PostDTO updatedPost=this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable("keywords") String keywords){
        List<PostDTO> result=this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<ImageResponse> uploadPostImage(@RequestParam("image")MultipartFile image) throws IOException {
        String fileName =this.fileService.uploadImage(path,image);

        return new ResponseEntity<>(new ImageResponse(fileName,"Image is succesfully uploaded"),HttpStatus.OK);
    }
}

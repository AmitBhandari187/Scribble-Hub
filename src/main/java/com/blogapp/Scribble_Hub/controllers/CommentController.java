package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.entity.Comment;
import com.blogapp.Scribble_Hub.payloads.ApiResponse;
import com.blogapp.Scribble_Hub.payloads.CommentDTO;
import com.blogapp.Scribble_Hub.service.CommentService;
import com.blogapp.Scribble_Hub.service.FileService;
import com.blogapp.Scribble_Hub.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment , @PathVariable Long userId, @PathVariable Long postId ){
        CommentDTO createdComment= this.commentService.createComment(comment,userId,postId);
        return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!",true),HttpStatus.OK);
    }
}

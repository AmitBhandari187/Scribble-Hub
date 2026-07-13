package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.entity.Comment;
import com.blogapp.Scribble_Hub.payloads.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    public ResponseEntity<CommentDTO> createComment(@RequestBody Comment comment)
}

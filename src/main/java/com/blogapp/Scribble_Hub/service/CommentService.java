package com.blogapp.Scribble_Hub.service;

import com.blogapp.Scribble_Hub.payloads.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO , Long userId , Long postId);
    void deleteComment(Long commentId);
}

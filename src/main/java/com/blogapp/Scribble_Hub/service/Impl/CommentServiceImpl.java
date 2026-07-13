package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.Comment;
import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.CommentDTO;
import com.blogapp.Scribble_Hub.repositories.CategoryRepository;
import com.blogapp.Scribble_Hub.repositories.CommentRepository;
import com.blogapp.Scribble_Hub.repositories.PostRepository;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository,
                              ModelMapper modelMapper) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId, Long userId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "user id", userId));

        Comment comment=modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        comment.setUser(user);

        Comment savedComment=commentRepository.save(comment);
        return this.modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepository.delete(comment);
    }
}

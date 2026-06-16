package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.Category;
import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.repositories.CategoryRepository;
import com.blogapp.Scribble_Hub.repositories.PostRepository;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,UserRepository userRepository,CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
    }


    @Override
    public PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId) {

        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id",categoryId));

        Post post=this.modelMapper.map(postDTO,Post.class);
        post.setImageName("default.png");
        post.setCreatedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post newPost=this.postRepository.save(post);
        return this.modelMapper.map(newPost,PostDTO.class);
    }

    @Override
    public Post updatePost(PostDTO postDTO, Long postId) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post getPostById(Long postId) {
        return null;
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public List<Post> findByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }


}

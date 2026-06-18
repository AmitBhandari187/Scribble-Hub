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
import java.util.stream.Collectors;

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
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post updatedPost=this.postRepository.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> getAllPost=this.postRepository.findAll();
        List<PostDTO> allPosts=getAllPost.stream().map((post )-> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post postById=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
        return this.modelMapper.map(postById,PostDTO.class);
    }

    @Override
    public List<PostDTO> findByCategory(Long categoryId) {
        Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id",categoryId));
        List<Post> posts= this.postRepository.findByCategory(cat);

        List<PostDTO> postDTOS=posts.stream().map((post )-> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> findByUser(Long userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));

        List<Post> posts=this.postRepository.findByUser(user);

        List<PostDTO> postDTOS=posts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return List.of();
    }


}

package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.Category;
import com.blogapp.Scribble_Hub.entity.Post;
import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.PostDTO;
import com.blogapp.Scribble_Hub.payloads.PostResponse;
import com.blogapp.Scribble_Hub.repositories.CategoryRepository;
import com.blogapp.Scribble_Hub.repositories.PostRepository;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {

        Sort sort=sortDirection.equalsIgnoreCase("dsc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort );

        Page<Post> pagePost = this.postRepository.findAll(pageable);

        List<Post> allPosts = pagePost.getContent();

        List<PostDTO> postDTOs = allPosts.stream()
                .map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOs);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post postById=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id",postId));
        return this.modelMapper.map(postById,PostDTO.class);
    }

    @Override
    public PostResponse findByCategory(Long categoryId, Integer pageNumber , Integer pageSize) {
        Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id",categoryId));
        Pageable pageable=PageRequest.of(pageNumber , pageSize);

        Page<Post> pagePost=this.postRepository.findAll(pageable);
        List<Post> posts= this.postRepository.findByCategory(cat);

        List<PostDTO> postDTOS=posts.stream().map((post )-> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse findByUser(Long userId, Integer pageNumber, Integer pageSize) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost=this.postRepository.findAll(pageable);
        List<Post> posts=this.postRepository.findByUser(user);

        List<PostDTO> postDTOS=posts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return List.of();
    }


}

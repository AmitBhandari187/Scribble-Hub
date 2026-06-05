package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user=this.dtoToUser(userDTO);
        User savedUser= this.userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User",
                        "id",
                        userId
                ));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updatedUser=this.userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users=this.userRepository.findAll();
        List <UserDTO> userDtos=users.stream().map(this::userToDto).toList();

        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        this.userRepository.delete(user);
    }

//    For user data to database
    private User dtoToUser(UserDTO userDTO){
        User user=new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;
    }
//    For Database to User Response
    private UserDTO userToDto(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        return null;
    }


}

package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.exceptions.ResourceNotFoundException;
import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.repositories.UserRepository;
import com.blogapp.Scribble_Hub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;



    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
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
        User user=this.modelMapper.map(userDTO,User.class);

//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }
//    For Database to User Response
private UserDTO userToDto(User user){
    UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
    return userDTO;
}


}

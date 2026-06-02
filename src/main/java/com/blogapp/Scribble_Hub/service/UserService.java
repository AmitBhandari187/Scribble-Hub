package com.blogapp.Scribble_Hub.service;

import com.blogapp.Scribble_Hub.entity.User;
import com.blogapp.Scribble_Hub.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Long userId);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUser();
    void deleteUser(Long userId);
}

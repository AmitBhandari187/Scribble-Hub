package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Create User -----
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createduserDto=this.userService.createUser(userDTO);
        return new ResponseEntity<>(createduserDto, HttpStatus.CREATED);
    }
}
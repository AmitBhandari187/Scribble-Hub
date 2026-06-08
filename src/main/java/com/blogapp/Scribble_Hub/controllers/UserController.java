package com.blogapp.Scribble_Hub.controllers;

import com.blogapp.Scribble_Hub.payloads.UserDTO;
import com.blogapp.Scribble_Hub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    //Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO , @PathVariable Long userId){
        UserDTO updatedUser=this.userService.updateUser(userDTO , userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {

        this.userService.deleteUser(userId);

        return new ResponseEntity<>(
                Map.of("message", "User Deleted Successfully!!"),
                HttpStatus.OK
        );
    }

    //Get mapping
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> users = this.userService.getAllUser();

        return ResponseEntity.ok(users);
    }

    //Get by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {

        UserDTO user = this.userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
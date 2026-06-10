package com.blogapp.Scribble_Hub.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4 , message = "Username must be minimum 4 characters long")
    private String name;
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3 , max = 10 , message = "Password must be minimum 3 character long and must be 10 character long")
    private String password;
    @NotBlank(message = "Password cannot be empty")
    private String about;
}

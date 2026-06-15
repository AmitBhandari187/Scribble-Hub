package com.blogapp.Scribble_Hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Post> posts=new ArrayList<>();
}

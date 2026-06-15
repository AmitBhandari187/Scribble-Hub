package com.blogapp.Scribble_Hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column()
    private String categoryTitle;
    @Column()
    private String categoryDescription;

    // Created because one category can contain multiple posts
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();
}

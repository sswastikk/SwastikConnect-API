package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@Getter
@Setter
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Long categoryId;

    @Column(name = "CategoryName", nullable = false, unique = true, length = 50)
    private String categoryName;

    @Column(name = "CategoryDescription", nullable = true, length = 256)
    private String categoryDescription;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}

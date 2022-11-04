package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Getter
@Setter
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostId")
    private Long postId;

    @Column(name = "Title", length = 200)
    private String postTitle;

    @Column(name = "Content", length = 2000)
    private String postContent;

    @Column(name = "CreatedOn")
    private Date postCreatedDateTime;

    @Column(name = "ModifiedOn")
    private Date postUpdatedDateTime;

    @Column(name = "Image")
    private String postImage;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();
}

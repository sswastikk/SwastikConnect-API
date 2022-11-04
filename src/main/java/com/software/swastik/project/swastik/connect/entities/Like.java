package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Likes")
@Getter
@Setter
@NoArgsConstructor
public class Like
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LikeId")
    private Long likeId;

    @Column(name = "LikedOn")
    private Date likeDateTime;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PostId")
    private Post post;
}

package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CommentId")
    private Long commentId;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "CreatedOn")
    private Date commentCreatedDateTime;

    @Column(name = "ModifiedOn")
    private Date commentModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PostId")
    private Post post;
}

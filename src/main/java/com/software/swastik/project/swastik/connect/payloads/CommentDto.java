package com.software.swastik.project.swastik.connect.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto
{
    private Long commentId;
    private String comment;
    private Date commentCreatedDateTime;
    private Date commentModifiedDateTime;
    private UserDto user;
    private PostDto post;
}

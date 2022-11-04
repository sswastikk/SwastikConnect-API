package com.software.swastik.project.swastik.connect.payloads;

import com.software.swastik.project.swastik.connect.entities.Post;
import com.software.swastik.project.swastik.connect.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto
{
    private Long likeId;
    private Date likeDateTime;
    private User user;
    private Post post;
}

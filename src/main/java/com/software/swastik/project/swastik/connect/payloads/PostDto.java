package com.software.swastik.project.swastik.connect.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto
{
    private Long postId;
    @NotEmpty
    @Size(max = 200, message = "Post Title cannot be more than 200 Characters !!!")
    private String postTitle;

    @NotEmpty
    @Size(max = 2000, message = "Post Content cannot be more than 2000 Characters !!!")
    private String postContent;
    private Date postCreatedDateTime;
    private Date postUpdatedDateTime;
    private String postImage;
    private UserDto user;
    private CategoryDto category;
}

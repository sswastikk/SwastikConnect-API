package com.software.swastik.project.swastik.connect.services;

import com.software.swastik.project.swastik.connect.payloads.CommentDto;
import java.util.List;

public interface CommentService
{
    //Post Comment
    CommentDto createComment(CommentDto commentDto, Long postId, Long userId);

    //Delete Comment
    void deleteComment(Long commentId);

    //Edit Comment
    CommentDto updateComment(CommentDto commentDto, Long commentId);

    //Get All Comments on Post
    List<CommentDto> allCommentsOnPost(Long postId);
}

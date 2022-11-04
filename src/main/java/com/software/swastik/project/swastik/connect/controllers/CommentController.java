package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import com.software.swastik.project.swastik.connect.payloads.CommentDto;
import com.software.swastik.project.swastik.connect.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comments/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId)
    {
        CommentDto createdComment  = this.commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsOnPost(@PathVariable("postId") Long postId)
    {
        return new ResponseEntity<>(this.commentService.allCommentsOnPost(postId), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity(new ApiResponse("Comment Deleted Successfully", true, "Ok"), HttpStatus.OK);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable("commentId") Long commentId)
    {
        return new ResponseEntity<>(this.commentService.updateComment(commentDto, commentId), HttpStatus.OK);
    }
}

package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import com.software.swastik.project.swastik.connect.payloads.LikeDto;
import com.software.swastik.project.swastik.connect.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class LikeController
{
    @Autowired
    private LikeService likeService;

    @PostMapping("/user/{userId}/post/{postId}/likes/")
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto likeDto, @PathVariable  Long userId, @PathVariable  Long postId)
    {
        return new ResponseEntity<>(this.likeService.postLike(likeDto, userId, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/like/{likeId}")
    public ResponseEntity<?> removeLike(@PathVariable Long likeId)
    {
        this.likeService.removeLike(likeId);
        return new ResponseEntity(new ApiResponse("Like Deleted Successfully", true, "Ok"), HttpStatus.OK);
    }
}

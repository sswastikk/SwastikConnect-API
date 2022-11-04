package com.software.swastik.project.swastik.connect.services;

import com.software.swastik.project.swastik.connect.payloads.LikeDto;

public interface LikeService
{
    //Post Like
    LikeDto postLike(LikeDto likeDto, Long userId, Long postId);

    //Remove Like
    void removeLike(Long likeId);
}

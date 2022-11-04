package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.entities.Like;
import com.software.swastik.project.swastik.connect.entities.Post;
import com.software.swastik.project.swastik.connect.entities.User;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.payloads.LikeDto;
import com.software.swastik.project.swastik.connect.repositories.LikeRepository;
import com.software.swastik.project.swastik.connect.repositories.PostRepository;
import com.software.swastik.project.swastik.connect.repositories.UserRepository;
import com.software.swastik.project.swastik.connect.services.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LikeServiceImplementation implements LikeService
{
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postReporsitory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LikeDto postLike(LikeDto likeDto, Long userId, Long postId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        Like like = this.dtoToLike(likeDto);
        like.setPost(post);
        like.setUser(user);
        like.setLikeDateTime(new Date());
        Like postLike = this.likeRepository.save(like);
        return this.likeToDto(postLike);
    }

    @Override
    public void removeLike(Long likeId) {
        Like like = this.likeRepository.findById(likeId).orElseThrow(() -> new ResourceNotFoundException("Like", "LikeId", likeId));
        this.likeRepository.deleteById(likeId);
    }

    //Conversion LikeDto To Like
    public Like dtoToLike(LikeDto likeDto)
    {
        Like like = this.modelMapper.map(likeDto, Like.class);
        return like;
    }

    //Conversion Like to LikeDto
    public LikeDto likeToDto(Like like)
    {
        LikeDto likeDto  = this.modelMapper.map(like, LikeDto.class);
        return likeDto;
    }
}

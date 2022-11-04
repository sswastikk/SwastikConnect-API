package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.entities.Comment;
import com.software.swastik.project.swastik.connect.entities.Post;
import com.software.swastik.project.swastik.connect.entities.User;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.payloads.CommentDto;
import com.software.swastik.project.swastik.connect.repositories.CommentRepository;
import com.software.swastik.project.swastik.connect.repositories.PostRepository;
import com.software.swastik.project.swastik.connect.repositories.UserRepository;
import com.software.swastik.project.swastik.connect.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService
{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postReporsitory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        Comment comment = this.dtoToComment(commentDto);
        Date commentDate = new Date();
        comment.setCommentCreatedDateTime(commentDate);
        comment.setCommentModifiedDateTime(commentDate);
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = this.commentRepository.save(comment);
        return this.commentToDto(createdComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

        comment.setComment(commentDto.getComment());
        Date modifiedTime = new Date();
        comment.setCommentModifiedDateTime(modifiedTime);

        Comment updatedComment  = this.commentRepository.save(comment);
        return this.commentToDto(updatedComment);
    }

    @Override
    public List<CommentDto> allCommentsOnPost(Long postId) {
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        List<Comment> comments = this.commentRepository.findByPost(post);
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.commentToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    //Conversion Classes Comment to CommentDto
    public CommentDto commentToDto(Comment comment)
    {
        CommentDto dto = this.modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    //Conversion Classes CommentDto to Comment
    public Comment dtoToComment(CommentDto commentDto)
    {
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}

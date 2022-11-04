package com.software.swastik.project.swastik.connect.services;

import com.software.swastik.project.swastik.connect.payloads.PostDto;
import com.software.swastik.project.swastik.connect.payloads.PostResponse;

import java.util.List;

public interface PostService
{
    //Create a New Post
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    //GetSinglePost
    PostDto getPostById(Long postId);

    //UpdatePost
    PostDto updatePost(PostDto postDto, Long postId);

    //Get All Posts By User
    List<PostDto> getAllPostsByUser(Long userId);

    //Get All Posts By Category
    List<PostDto> getAllPostsByCategory(Long categoryId);

    //Delete Post By Id
    void deletePost(Long postId);

    //Get All Posts
    PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDirection);

    //Search Post By Keyword
    List<PostDto> searchPost(String keyword);
}

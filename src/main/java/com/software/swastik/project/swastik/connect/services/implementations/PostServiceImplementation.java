package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.entities.Category;
import com.software.swastik.project.swastik.connect.entities.Post;
import com.software.swastik.project.swastik.connect.entities.User;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.payloads.PostDto;
import com.software.swastik.project.swastik.connect.payloads.PostResponse;
import com.software.swastik.project.swastik.connect.repositories.CategoryRepository;
import com.software.swastik.project.swastik.connect.repositories.PostRepository;
import com.software.swastik.project.swastik.connect.repositories.UserRepository;
import com.software.swastik.project.swastik.connect.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService
{
    @Autowired
    private PostRepository postReporsitory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        Date postDate = new Date();

        Post post = this.dtoToPost(postDto);
        post.setPostImage("default.png");
        post.setPostCreatedDateTime(postDate);
        post.setPostUpdatedDateTime(postDate);
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postReporsitory.save(post);
        return this.postToDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        return this.postToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());
        Date modifiedTime = new Date();
        post.setPostUpdatedDateTime(modifiedTime);
        Post updatedPost = this.postReporsitory.save(post);
        return this.postToDto(updatedPost);
    }

    @Override
    public List<PostDto> getAllPostsByUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts = this.postReporsitory.findByUser(user);
        List<PostDto> allPostDto = posts.stream().map(allPost -> this.postToDto(allPost)).collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        List<Post> posts = this.postReporsitory.findByCategory(category);
        List<PostDto> allPostDto = posts.stream().map(allPost -> this.postToDto(allPost)).collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postReporsitory.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        this.postReporsitory.deleteById(postId);
    }

    @Override
    public PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDirection) {
        Sort sort = null;

        if(sortDirection.equals("ascending"))
        {
            sort = Sort.by(sortBy).ascending();
        }else if(sortDirection.equals("descending")){
            sort = Sort.by(sortBy).descending();
        }

        Pageable paging = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postReporsitory.findAll(paging);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> allPostDto = allPosts.stream().map(allPost -> this.postToDto(allPost)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setIsLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }

    //Conversion Method PostDto to Post
    public Post dtoToPost(PostDto postDto)
    {
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    //Conversion Method Post to Postdto
    public PostDto postToDto(Post post)
    {
        PostDto dto = this.modelMapper.map(post, PostDto.class);
        return dto;
    }
}

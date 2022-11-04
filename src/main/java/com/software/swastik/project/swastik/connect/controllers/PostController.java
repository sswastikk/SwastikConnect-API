package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.configurations.AppConstants;
import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import com.software.swastik.project.swastik.connect.payloads.PostDto;
import com.software.swastik.project.swastik.connect.payloads.PostResponse;
import com.software.swastik.project.swastik.connect.services.FileService;
import com.software.swastik.project.swastik.connect.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController
{
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<PostDto> createNewPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId)
    {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Long userId)
    {
        List<PostDto> allPostsByUser =  this.postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(allPostsByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("categoryId") Long categoryId)
    {
        List<PostDto> allPostsByCategory =  this.postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<>(allPostsByCategory, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection)
    {
        PostResponse allPosts =  this.postService.getAllPosts(pageSize, pageNumber, sortBy, sortDirection);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto > getSinglePostById(@PathVariable("postId") Long postId)
    {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable("postId") Long postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity(new ApiResponse("Post Deleted Successfully", true, "Ok"), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") Long postId)
    {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword)
    {
        List<PostDto> allPostsBySearch =  this.postService.searchPost(keyword);
        return new ResponseEntity<>(allPostsBySearch, HttpStatus.OK);
    }

    //Post Image Upload
    @PostMapping("/posts/{postId}/image/upload")
    public ResponseEntity<PostDto> uploadImagePost(@RequestParam("image") MultipartFile image, @PathVariable("postId") Long postId) throws IOException
    {
        String fileToUpload = this.fileService.uploadImage(path, image);
        PostDto post = this.postService.getPostById(postId);
        post.setPostImage(fileToUpload);
        PostDto updatedPost = this.postService.updatePost(post, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    //Fetch Image
    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void fetchImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}

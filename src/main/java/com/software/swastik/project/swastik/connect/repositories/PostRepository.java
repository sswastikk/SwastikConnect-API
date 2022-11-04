package com.software.swastik.project.swastik.connect.repositories;

import com.software.swastik.project.swastik.connect.entities.Category;
import com.software.swastik.project.swastik.connect.entities.Post;
import com.software.swastik.project.swastik.connect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByPostTitleContaining(String postTitle);
}

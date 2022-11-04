package com.software.swastik.project.swastik.connect.repositories;

import com.software.swastik.project.swastik.connect.entities.Comment;
import com.software.swastik.project.swastik.connect.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findByPost(Post post);
}

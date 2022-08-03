package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Nu folosim adnotarea @Repository aici deoarece ea se afla deja in clasa SimpleJpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //Standard format to write the methods in Jpa repository
    List<Comment> findByPostId(long postId);

}

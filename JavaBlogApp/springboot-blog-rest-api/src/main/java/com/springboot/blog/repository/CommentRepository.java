package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

//Nu folosim adnotarea @Repository aici deoarece ea se afla deja in clasa SimpleJpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //
}

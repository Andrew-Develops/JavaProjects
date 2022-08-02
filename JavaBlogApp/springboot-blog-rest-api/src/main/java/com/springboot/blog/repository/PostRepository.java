package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository furnizeaza intern toate metodele CRUD
//Nu folosim adnotarea @Repository aici deoarece ea se afla deja in clasa SimpleJpaRepository
public interface PostRepository extends JpaRepository<Post, Long> {
    //
}

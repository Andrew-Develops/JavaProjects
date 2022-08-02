package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Folosim adnotarea deoarece dezvoltam un REST APIs
@RequestMapping(path = "/api/posts") //pathul metodei
public class PostController {

    private PostService postService;

    //Daca clasa contine un singur constructor putem omite @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Cream un blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        //ResponseEntity are 2 parametri, unul este raspunsul creat de metoda createPost si celalalt este HTTP status code creat
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
}

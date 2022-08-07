package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value = "CRUD Rest APIs for Post resources")
@RestController //Folosim adnotarea deoarece dezvoltam un REST APIs
@RequestMapping() //pathul metodei
public class PostController {

    private PostService postService;

    //Daca clasa contine un singur constructor putem omite @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create blog post rest api
    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")   //Numai cei cu rol de ADMIN pot folosi acest REST API
    @PostMapping(path = "/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        //ResponseEntity accepta 2 argumente, unul este corpul trimis catre client si celalalt este HTTP status code creat
        PostDto postResponse = postService.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    //Get all posts rest api
    @ApiOperation(value = "Get All Posts REST API")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //Get post by id rest api
    @ApiOperation(value = "Get Post by ID REST API")
    @GetMapping(path = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //Update post by id rest api
    @ApiOperation(value = "Update Post by ID REST API")
    @PreAuthorize("hasRole('ADMIN')")   //Numai cei cu rol de ADMIN pot folosi acest REST API
    @PutMapping(path = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePostById(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post by ID REST API")
    @PreAuthorize("hasRole('ADMIN')")   //Numai cei cu rol de ADMIN pot folosi acest REST API
    @DeleteMapping(path = "/api/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}

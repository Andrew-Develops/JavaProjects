package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Comment resources")
@RestController
@RequestMapping(path = "/api/v1")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable(name = "postId") long postId) {
        CommentDto postComment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(postComment, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Comments by Post ID REST API")
    @GetMapping(path = "/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") long postId) {
        List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);
        return commentDtoList;
    }
    @ApiOperation(value = "Get Single Comment by Post ID REST API")
    @GetMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,
                                                     @PathVariable(name = "commentId") long commentId) {
        CommentDto postComment = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(postComment, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Comment by ID REST API")
    @PutMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(name = "postId") long postId,
                                                        @PathVariable(name = "commentId") long commentId,
                                                        @Valid @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Comment by ID REST API")
    @DeleteMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name = "postId") long postId,
                                                    @PathVariable(name = "commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment successfully deleted", HttpStatus.OK);
    }
}

package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        //convertim din CommentDto in Entity
        Comment comment = mapToEntity(commentDto);
        //Recuperam PostEntity by ID din DB
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Setam post pe comment entity
        comment.setPost(post);
        //Salvam comment entity in db
        Comment newComment = commentRepository.save(comment);
        CommentDto commentResponse = mapToDto(newComment);
        return commentResponse;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        //convertim lista de comments in commentDto
        List<CommentDto> commentsDto = comments.stream()
                .map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return commentsDto;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //Recuperam PostEntity by ID din DB
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Recuperam CommentEntity by ID din DB
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Daca commentul nu apartine acelui post atunci aruncam exceptia
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        CommentDto returnComment = mapToDto(comment);
        return returnComment;
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentDto) {
        //Recuperam PostEntity by ID din DB
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Recuperam CommentEntity by ID din DB
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Daca commentul nu apartine acelui post atunci aruncam exceptia
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        //Setam noul CommentDto pe Comment si il salvam in baza de date
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);

        CommentDto returnComment = mapToDto(updatedComment);
        return returnComment;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        //Recuperam PostEntity by ID din DB
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //Recuperam CommentEntity by ID din DB
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Daca commentul nu apartine acelui post atunci aruncam exceptia
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }


    //Convertim CommentEntity in CommentDto
    private CommentDto mapToDto(Comment comment) {
        //primul argument este sursa, al doilea este destinatia
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    //Convertim CommentDto in CommentEntity
    private Comment mapToEntity(CommentDto commentDto) {
        //primul argument este sursa, al doilea este destinatia
        Comment comment = modelMapper.map(commentDto,Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}

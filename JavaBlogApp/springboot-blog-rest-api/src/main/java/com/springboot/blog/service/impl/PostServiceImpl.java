package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service    //Fiecare clasa Service trebuie adnotata
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    //Daca clasa contine un singur constructor putem omite @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert DTO in Entity
        Post post = mapToEntity(postDto);

        //Am salvat obiectul in baza de date
        Post newPost = postRepository.save(post);

        //Convert Entity in DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
//        //Cream un sort object in functie de input(asc sau desc)
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
        //Cream Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        //Luam toate posts din DB
        Page<Post> posts = postRepository.findAll(pageable);
        //Get content from page object
        List<Post> listOfPosts = posts.getContent();
        //Convertim PostsEntity in PostDto folosind stream
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        //get post by id din DB sau arunca exceptia ResourceNotFoundException
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        //get post by id din DB sau arunca exceptia ResourceNotFoundException
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        //Returnam PostDto catre controller layer
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        //get post by id din DB sau arunca exceptia ResourceNotFoundException
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    //Convertim un Post Entity intr-un PostDto
    private PostDto mapToDTO(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        return postDto;
    }

    //Convertim PostDto in Post Entity
    private Post mapToEntity(PostDto postDto) {
        Post postEntity = new Post();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setDescription(postDto.getDescription());
        return postEntity;
    }

}

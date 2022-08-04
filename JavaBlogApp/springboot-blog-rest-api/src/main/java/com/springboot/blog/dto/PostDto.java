package com.springboot.blog.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data   //genereaza Getter, Setter, toString, hashCode
public class PostDto {
    private long id;
    //Pentru a implementa validarile trebuie sa furnizam @Valid cand folosim PostDto
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}

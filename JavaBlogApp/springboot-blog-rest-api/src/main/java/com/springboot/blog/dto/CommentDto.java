package com.springboot.blog.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "This is not a valid email address")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Body should have at least 10 characters")
    private String body;
}

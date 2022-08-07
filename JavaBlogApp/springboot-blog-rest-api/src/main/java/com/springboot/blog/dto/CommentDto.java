package com.springboot.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {
    @ApiModelProperty(value = "Comment id")
    private long id;
    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "This is not a valid email address")
    private String email;
    @ApiModelProperty(value = "Comment body")
    @NotEmpty
    @Size(min = 10, message = "Body should have at least 10 characters")
    private String body;
}

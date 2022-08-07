package com.springboot.blog.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post model information")
@Data   //genereaza Getter, Setter, toString, hashCode
public class PostDto {
    @ApiModelProperty(value = "Blog post id")
    private long id;
    //Pentru a implementa validarile trebuie sa furnizam @Valid cand folosim PostDto
    @ApiModelProperty(value = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @ApiModelProperty(value = "Blog post content")
    @NotEmpty
    private String content;
    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDto> comments;
}

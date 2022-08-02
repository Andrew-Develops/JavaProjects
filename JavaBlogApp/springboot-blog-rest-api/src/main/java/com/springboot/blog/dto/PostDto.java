package com.springboot.blog.dto;

import lombok.Data;

@Data   //genereaza Getter, Setter, toString
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;

}

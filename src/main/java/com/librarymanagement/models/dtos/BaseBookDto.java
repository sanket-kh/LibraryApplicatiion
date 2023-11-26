package com.librarymanagement.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBookDto {
    private Long isbn;
    private String title;
    private String author;
    private Integer copies;
}

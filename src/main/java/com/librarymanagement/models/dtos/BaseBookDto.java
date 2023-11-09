package com.librarymanagement.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBookDto {
    private Integer isbn;
    private String title;
    private Integer copies;
}

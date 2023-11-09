package com.librarymanagement.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookDto {
    private Integer isbn;
    private String title;
}

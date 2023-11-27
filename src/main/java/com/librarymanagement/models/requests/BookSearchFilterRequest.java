package com.librarymanagement.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchFilterRequest {
    private Long isbn;
    private String title;
    private String author;
}

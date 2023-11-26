package com.librarymanagement.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBookRequest {

    private Long isbn;
    private String title;
    private Integer copies;
}

package com.librarymanagement.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBookRequest {

    private Integer isbn;
    private String title;
    private Integer copies;
}

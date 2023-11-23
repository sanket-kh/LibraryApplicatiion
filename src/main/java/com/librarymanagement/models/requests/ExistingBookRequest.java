package com.librarymanagement.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExistingBookRequest {
    private Integer isbn;
    private Integer copies;
}

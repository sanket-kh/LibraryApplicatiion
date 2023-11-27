package com.librarymanagement.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BurrowRequest {
    private String username;
    private Long isbn;

}

package com.librarymanagement.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveRequest {
    private String username;
    private Long isbn;
}

package com.librarymanagement.models.responses;

import com.librarymanagement.models.dtos.bookdtos.BookDto;

import java.util.List;

public class UserResponse {
    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Long phone;

    private String address;

    private Boolean status;

    private List<BookDto> bookList;
}

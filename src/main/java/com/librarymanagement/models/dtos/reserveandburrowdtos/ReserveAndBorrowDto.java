package com.librarymanagement.models.dtos.reserveandburrowdtos;

import com.librarymanagement.models.dtos.bookdtos.BookDto;
import com.librarymanagement.models.dtos.finedtos.FineDto;
import com.librarymanagement.models.dtos.userdtos.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ReserveAndBorrowDto {

    private Boolean reserved;

    private Boolean isIssued;

    private LocalDate issueDate;

    private LocalDate returnDate;

    private UserDto user;

    private BookDto book;

    private FineDto fine;
}

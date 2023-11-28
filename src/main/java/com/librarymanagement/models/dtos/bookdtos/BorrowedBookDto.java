package com.librarymanagement.models.dtos.bookdtos;

import java.time.LocalDate;

public interface BorrowedBookDto {
    Long getIsbn();
    String getAuthor();
    String getTitle();
    LocalDate getIssuedDate();
}

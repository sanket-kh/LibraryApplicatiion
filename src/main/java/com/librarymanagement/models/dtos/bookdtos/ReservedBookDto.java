package com.librarymanagement.models.dtos.bookdtos;

import java.time.LocalDate;

public interface ReservedBookDto {
    Long getIsbn();
    String getAuthor();
    String getTitle();

}

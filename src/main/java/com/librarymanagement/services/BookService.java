package com.librarymanagement.services;

import com.librarymanagement.models.dtos.BaseBookDto;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Object> saveBook(BaseBookDto baseBookDto);

    ResponseEntity<Object> getBookByIsbn(Integer isbn);

    ResponseEntity<Object> getAllBooks();

    ResponseEntity<Object> updateBookById(Long isbn, BaseBookDto baseBookDto);
}

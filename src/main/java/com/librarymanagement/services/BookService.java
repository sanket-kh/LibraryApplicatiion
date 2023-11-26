package com.librarymanagement.services;

import com.librarymanagement.models.requests.BookSearchFilterRequest;
import com.librarymanagement.models.requests.ExistingBookRequest;
import com.librarymanagement.models.requests.SaveBookRequest;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Object> saveBook(SaveBookRequest saveBookRequest);

    ResponseEntity<Object> getBookByIsbn(Long isbn);

    ResponseEntity<Object> getAllBooks(Integer pageNumber);

    ResponseEntity<Object> updateBookById(Long isbn, SaveBookRequest saveBookRequest);

    ResponseEntity<Object> addExistingBookByIsbn(ExistingBookRequest existingBookRequest);

    ResponseEntity<Object> searchBook(BookSearchFilterRequest bookSearchFilterRequest);
}

package com.librarymanagement.controllers;

import com.librarymanagement.models.dtos.BaseBookDto;
import com.librarymanagement.services.BookService;
import com.librarymanagement.utils.ResponseConstants;
import com.librarymanagement.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
@ControllerAdvice
@RequestMapping("api/v1/books")
public class BookController {
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<Object> saveBook(@RequestBody BaseBookDto baseBookDto) {
        try {
            return bookService.saveBook(baseBookDto);
        } catch (Exception e) {
            log.error("BookController :: saveBook", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR, "Server Error"), HttpStatus.OK);

        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Object> getBookByIsbn(@PathVariable Integer isbn) {
        try {
            return bookService.getBookByIsbn(isbn);
        } catch (Exception e) {
            log.error("BookController :: getBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR, "Server Error"), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllBooks() {
        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            log.error("BookController :: getAllBooks", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR, "Server Error"), HttpStatus.OK);
        }
    }

    @PostMapping("-update")
    public ResponseEntity<Object> updateBookById(@RequestParam Long id, @RequestBody BaseBookDto baseBookDto) {
        try {
            return bookService.updateBookById(id, baseBookDto);
        } catch (Exception e) {
            log.error("BookController :: updateBookById", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR, "Server Error"), HttpStatus.OK);
        }
    }
}

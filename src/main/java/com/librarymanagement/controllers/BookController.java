package com.librarymanagement.controllers;


import com.librarymanagement.models.requests.BookSearchFilterRequest;
import com.librarymanagement.models.requests.ExistingBookRequest;
import com.librarymanagement.models.requests.SaveBookRequest;
import com.librarymanagement.services.BookService;
import com.librarymanagement.utils.ResponseConstants;
import com.librarymanagement.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/books")
public class BookController {
    private BookService bookService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveBook(@RequestBody SaveBookRequest saveBookRequest) {
        try {
            return bookService.saveBook(saveBookRequest);
        } catch (Exception e) {
            log.error("BookController :: saveBook", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR,
                    "Server Error"), HttpStatus.OK);

        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Object> getBookByIsbn(@PathVariable Long isbn) {
        try {
            return bookService.getBookByIsbn(isbn);
        } catch (Exception e) {
            log.error("BookController :: getBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR,
                    "Server Error"), HttpStatus.OK);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllBooks(@RequestParam(defaultValue = "0") Integer page) {
        try {
            return bookService.getAllBooks(page);
        } catch (Exception e) {
            log.error("BookController :: getAllBooks", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR,
                    "Server Error"), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateBookById(@RequestParam Long id, @RequestBody SaveBookRequest saveBookRequest) {
        try {
            return bookService.updateBookById(id, saveBookRequest);
        } catch (Exception e) {
            log.error("BookController :: updateBookById", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR,
                    "Server Error"), HttpStatus.OK);
        }
    }

    @PostMapping("/add-existing")
    public ResponseEntity<Object> addExistingBookByIsbn(@RequestBody ExistingBookRequest existingBookRequest) {
        try {
            return bookService.addExistingBookByIsbn(existingBookRequest);
        } catch (Exception e) {
            log.error("BookController :: addExistingBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.SERVER_ERROR,
                    "Server Error"), HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchBook(@RequestBody BookSearchFilterRequest bookSearchFilterRequest) {
        if ((bookSearchFilterRequest.getTitle() == null || bookSearchFilterRequest.getTitle().isBlank()) &&
            bookSearchFilterRequest.getIsbn() == null &&
            (bookSearchFilterRequest.getAuthor() == null || bookSearchFilterRequest.getAuthor().isBlank())
            ) {
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.BAD_REQUEST,
                    "Invalid search parameters"), HttpStatus.OK);
        }
        return bookService.searchBook(bookSearchFilterRequest);
    }
}

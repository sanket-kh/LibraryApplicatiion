package com.librarymanagement.controllers;

import com.librarymanagement.models.dtos.BaseBookDto;
import com.librarymanagement.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<Object> saveBook(@RequestBody BaseBookDto baseBookDto) {
        return bookService.saveBook(baseBookDto);
    }

    @GetMapping("/{{isbn}}")
    public ResponseEntity<Object> getBookByIsbn(@PathVariable Integer isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("")
    public ResponseEntity<Object> updateBookById(@RequestParam Long id, @RequestBody BaseBookDto baseBookDto) {
        return bookService.updateBookById(id, baseBookDto);
    }
}

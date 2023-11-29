package com.librarymanagement.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@RestController
@RequestMapping("/api/v1/report")
public class ReportingStatisticController {
    @GetMapping("/all-books")
    public ResponseEntity<Object> getListOfAllBooks(){
        return null;
    }
    @GetMapping("/available-books")
    public ResponseEntity<Object> getListOfAvailableBooks(){
        return null;
    }

    @GetMapping("/reserved-books")
    public ResponseEntity<Object> getListOfAllReservedBooks(){
        return null;
    }

    @GetMapping("/book-count/available")
    public ResponseEntity<Object> getTotalNumberOfAvailableBooks(){
        return null;
    } @GetMapping("/book-count/total")
    public ResponseEntity<Object> getTotalNumberOfTotalBooks(){
        return null;
    }

}

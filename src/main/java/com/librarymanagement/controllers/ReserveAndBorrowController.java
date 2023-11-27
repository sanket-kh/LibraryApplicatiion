package com.librarymanagement.controllers;

import com.librarymanagement.models.requests.BorrowRequest;
import com.librarymanagement.services.ReserveAndBorrowService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ReserveAndBorrowController {
private ReserveAndBorrowService reserveAndBorrowService;

    @PostMapping("/borrow")
    public ResponseEntity<Object> burrowBook(@RequestBody BorrowRequest borrowRequest){
    return reserveAndBorrowService.burrowBook(borrowRequest);
    }
    @PostMapping("/return")
    public ResponseEntity<Object> returnBook(@RequestBody BorrowRequest returnRequest){
        return reserveAndBorrowService.returnBook(returnRequest);
    }
}

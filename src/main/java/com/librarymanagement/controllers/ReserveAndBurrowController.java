package com.librarymanagement.controllers;

import com.librarymanagement.models.requests.BurrowRequest;
import com.librarymanagement.services.ReserveAndBorrowService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ReserveAndBurrowController {
private ReserveAndBorrowService reserveAndBorrowService;

    @PostMapping("/burrow")
    public ResponseEntity<Object> burrowBook(@RequestBody BurrowRequest burrowRequest){
    return reserveAndBorrowService.burrowBook(burrowRequest);
    }
    @PostMapping("/return")
    public ResponseEntity<Object> returnBook(@RequestBody BurrowRequest returnRequest){
        return reserveAndBorrowService.returnBook(returnRequest);
    }
}

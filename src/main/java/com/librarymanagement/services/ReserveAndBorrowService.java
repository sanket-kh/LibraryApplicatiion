package com.librarymanagement.services;

import com.librarymanagement.models.requests.BurrowRequest;
import org.springframework.http.ResponseEntity;

public interface ReserveAndBorrowService {
    ResponseEntity<Object> burrowBook(BurrowRequest burrowRequest);
    ResponseEntity<Object> returnBook(BurrowRequest returnRequest);
    ResponseEntity<Object> reserveUnavailableBook();
    ResponseEntity<Object> cancelReservationOfBook();
    ResponseEntity<Object> viewBurrowedBooksByUser();
    ResponseEntity<Object> viewReservedBooksByUser();

}

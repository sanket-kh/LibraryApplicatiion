package com.librarymanagement.services;

import com.librarymanagement.models.requests.BorrowRequest;
import com.librarymanagement.models.requests.ReserveRequest;
import org.springframework.http.ResponseEntity;

public interface ReserveAndBorrowService {
    ResponseEntity<Object> burrowBook(BorrowRequest borrowRequest);
    ResponseEntity<Object> returnBook(BorrowRequest returnRequest);
    ResponseEntity<Object> reserveUnavailableBook(ReserveRequest reserveRequest);
    ResponseEntity<Object> cancelReservationOfBook(ReserveRequest cancleReserveRequest);
    ResponseEntity<Object> viewBurrowedBooksByUser(String username);
    ResponseEntity<Object> viewReservedBooksByUser(String username);

}

package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.entities.Book;
import com.librarymanagement.entities.ReserveAndBorrow;
import com.librarymanagement.entities.User;
import com.librarymanagement.mappers.FineMapper;
import com.librarymanagement.mappers.ReserveAndBorrowMapper;
import com.librarymanagement.models.dtos.finedtos.FineDto;
import com.librarymanagement.models.requests.BorrowRequest;
import com.librarymanagement.models.requests.ReserveRequest;
import com.librarymanagement.repositories.BookRepo;
import com.librarymanagement.repositories.ReserveAndBurrowRepo;
import com.librarymanagement.repositories.UserRepo;
import com.librarymanagement.services.FineService;
import com.librarymanagement.services.ReserveAndBorrowService;
import com.librarymanagement.utils.Constants;
import com.librarymanagement.utils.ResponseConstants;
import com.librarymanagement.utils.ResponseUtility;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor
@Transactional
public class ReserveAndBorrowServiceImpl implements ReserveAndBorrowService {
    private BookRepo bookRepo;
    private UserRepo userRepo;
    private ReserveAndBurrowRepo reserveAndBurrowRepo;
    private FineService fineService;
    @Override
    public ResponseEntity<Object> burrowBook(BorrowRequest borrowRequest) {
        User user = userRepo.findUserByUsername(borrowRequest.getUsername());
        if(user==null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "Invalid username"), HttpStatus.OK);
        }
        if(!user.getStatus()){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "User is disabled"), HttpStatus.OK);
        }

        Book book = bookRepo.getBookByIsbn(borrowRequest.getIsbn());
        if(book == null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "The book doesnt exist in the library"), HttpStatus.OK);
        }
        if(book.getCopies() == 0){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "The book is unavailable"), HttpStatus.OK);
        }
        ReserveAndBorrow existingTransaction = reserveAndBurrowRepo.findExistingTransactionByBookAndUser(book, user);
        if(existingTransaction != null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.FORBIDDEN,
                    "User cannot issue same book more than once"), HttpStatus.OK);
        }
        if(user.getReserveAndBorrowList().size() >= Constants.BORROW_LIMIT_PER_USER){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.FORBIDDEN,
                    "User has maximum number of books burrowed"), HttpStatus.OK);
        }
        ReserveAndBorrow reserveAndBorrow = new ReserveAndBorrow();
        reserveAndBorrow.setBook(book);
        reserveAndBorrow.setUser(user);
        reserveAndBorrow.setIssueDate(LocalDate.now());
        reserveAndBorrow.setIsIssued(Boolean.TRUE);
        book.setCopies(book.getCopies()-1);
        bookRepo.save(book);
        reserveAndBurrowRepo.save(reserveAndBorrow);

         return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.OK,
                "The book has been issued "), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> returnBook(BorrowRequest returnRequest) {
        User user = userRepo.findUserByUsername(returnRequest.getUsername());
        if(user==null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "Invalid username"), HttpStatus.OK);
        }
        Book book = bookRepo.getBookByIsbn(returnRequest.getIsbn());
        if(book == null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "The book doesnt exist in the library"), HttpStatus.OK);
        }
        ReserveAndBorrow existingTransaction = reserveAndBurrowRepo.findExistingTransactionByBookAndUser(book, user);
        if(existingTransaction == null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.FORBIDDEN,
                    "User has not burrowed this book"), HttpStatus.OK);
        }

        existingTransaction.setReturnDate(LocalDate.now());
        existingTransaction.setIsIssued(Boolean.FALSE);
        book.setCopies(book.getCopies()+1);
        bookRepo.save(book);

        Period period = Period.between(existingTransaction.getIssueDate(), existingTransaction.getReturnDate());
        int difference = period.getDays();
        if(difference>Constants.MAX_BORROW_DURATION_PER_BOOK){
           FineDto fineDto = fineService.calculateFine(ReserveAndBorrowMapper.mapToReserveAndBorrowDto(existingTransaction),difference);
           existingTransaction.setFine(FineMapper.mapToFine(fineDto));
        }
        reserveAndBurrowRepo.save(existingTransaction);

        return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.OK,
                "Book returned"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> reserveUnavailableBook(ReserveRequest reserveRequest) {
        User user = userRepo.findUserByUsername(reserveRequest.getUsername());
        if(user==null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "Invalid username"), HttpStatus.OK);
        }
        Book book = bookRepo.getBookByIsbn(reserveRequest.getIsbn());
        if(book == null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "The book doesnt exist in the library"), HttpStatus.OK);
        }
        if(book.getCopies()!= 0){
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.FORBIDDEN,
                    "Cannot reserve available book. Borrow it instead"), HttpStatus.OK);
        }

        ReserveAndBorrow reserveAndBorrow = new ReserveAndBorrow();
        reserveAndBorrow.setBook(book);
        reserveAndBorrow.setUser(user);
        reserveAndBorrow.setIsIssued(Boolean.FALSE);
        reserveAndBurrowRepo.save(reserveAndBorrow);

        return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.OK,
                "The book has been reserved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> cancelReservationOfBook(ReserveRequest cancleReserveRequest) {

        return null;
    }

    @Override
    public ResponseEntity<Object> viewBurrowedBooksByUser(String username) {
        User user = userRepo.findUserByUsername(username);
        if(user==null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "Invalid username"), HttpStatus.OK);
        }

        return null;
    }

    @Override
    public ResponseEntity<Object> viewReservedBooksByUser() {
        return null;
    }
}

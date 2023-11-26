package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.entities.Book;
import com.librarymanagement.mappers.bookmappers.BookMapper;

import com.librarymanagement.models.dtos.UserBookDto;
import com.librarymanagement.models.requests.ExistingBookRequest;
import com.librarymanagement.models.requests.SaveBookRequest;
import com.librarymanagement.repositories.BookRepo;
import com.librarymanagement.services.BookService;
import com.librarymanagement.utils.ResponseConstants;
import com.librarymanagement.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.librarymanagement.mappers.bookmappers.BookMapper.mapBookToUserBookDto;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;

    @Override
    public ResponseEntity<Object> saveBook(SaveBookRequest saveBookRequest) {

        try {

            Book existingBook = bookRepo.getBookByIsbn(saveBookRequest.getIsbn());
            if (existingBook != null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.ALREADY_EXISTS,
                        "Book record already exists, please add to existing record"), HttpStatus.OK);
            }

            Book book = BookMapper.mapBookRequestToBook(saveBookRequest);
            bookRepo.save(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.CREATED,
                    "Book saved"), HttpStatus.OK);

        } catch (Exception e) {
            log.error("BookServiceImpl :: saveBook ", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR,
                    "Unable to save book"), HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<Object> getBookByIsbn(Long isbn) {
        try {
            Book book = bookRepo.getBookByIsbn(isbn);
            if (book == null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                        "Book not found"), HttpStatus.OK);
            }
            UserBookDto userBookDto = mapBookToUserBookDto(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.OK,
                    "Book found", userBookDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: getBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR,
                    "Internal server error"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> getAllBooks() {
        try {
            List<Book> bookList = bookRepo.findAll();
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                        "No books in the library"), HttpStatus.OK);
            }

            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.OK,
                    "Books found", BookMapper.mapBookListToBaseBookDtoList(bookList)), HttpStatus.OK);

        } catch (Exception e) {
            log.error("BookService :: getAllBooks", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR,
                    "Internal server error"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> updateBookById(Long id, SaveBookRequest saveBookRequest) {
        try {
            Book book = bookRepo.getBookById(id);
            if (book == null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                        "Book doesnt exist with id:" + id), HttpStatus.OK);
            }
            book.setTitle(saveBookRequest.getTitle());
            book.setCopies(saveBookRequest.getCopies());
            book.setIsbn(saveBookRequest.getIsbn());
            bookRepo.save(book);
            book = bookRepo.getBookById(id);
            SaveBookRequest updatedBookDto = BookMapper.mapBookToSaveBookRequest(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.UPDATED,
                    "Book updated", updatedBookDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: updateBookById", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR,
                    "Failed to update book"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> addExistingBookByIsbn(ExistingBookRequest existingBookRequest) {
        try {
            Book book = bookRepo.getBookByIsbn(existingBookRequest.getIsbn());
            if (book == null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                        "Book with isbn:" + existingBookRequest.getIsbn() + " doesnt exist"), HttpStatus.OK);
            }
            book.setCopies(book.getCopies() + existingBookRequest.getCopies());
            bookRepo.save(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.UPDATED,
                    "Quantity of books updated"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: addExistingBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR,
                    "Failed to update book's copies"), HttpStatus.OK);
        }
    }

}

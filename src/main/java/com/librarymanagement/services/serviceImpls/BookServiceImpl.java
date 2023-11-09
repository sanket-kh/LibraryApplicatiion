package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.entities.Book;
import com.librarymanagement.mappers.bookmappers.BookMapper;
import com.librarymanagement.models.dtos.BaseBookDto;
import com.librarymanagement.models.dtos.UserBookDto;
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

import static com.librarymanagement.mappers.bookmappers.BookMapper.baseBookDtoToBook;
import static com.librarymanagement.mappers.bookmappers.BookMapper.bookToUserBookDto;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;

    @Override
    public ResponseEntity<Object> saveBook(BaseBookDto baseBookDto) {

        try {
            Book book = baseBookDtoToBook(baseBookDto);
            bookRepo.save(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.CREATED, "Book saved"), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookServiceImpl :: saveBook ", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_CREATED, "Failed to save book"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> getBookByIsbn(Integer isbn) {
        try {
            Book book = bookRepo.getBookByIsbn(isbn);
            if (book == null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND, "Book not found"), HttpStatus.OK);
            }
            UserBookDto userBookDto = bookToUserBookDto(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.OK, "Book found", userBookDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: getBookByIsbn", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR, "Internal server error"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> getAllBooks() {
        try {
            List<Book> bookList = bookRepo.findAll();
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND, "No books in the library"), HttpStatus.OK);
            }
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.OK, "Books found", bookList), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: getAllBooks", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR, "Internal server error"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> updateBookById(Long id, BaseBookDto baseBookDto) {
        try {
            Book book = bookRepo.getBookById(id);
            if (book == null) {
                return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND, "Book doesnt exist with id:" + id), HttpStatus.OK);
            }
            book.setTitle(baseBookDto.getTitle());
            book.setCopies(baseBookDto.getCopies());
            book.setIsbn(baseBookDto.getIsbn());
            bookRepo.save(book);
            BaseBookDto baseBookDto1 = BookMapper.bookToBaseBookDto(book);
            return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.UPDATED, "Book updated", baseBookDto1), HttpStatus.OK);
        } catch (Exception e) {
            log.error("BookService :: updateBookById", e);
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.INTERNAL_ERROR, "Failed to update book"), HttpStatus.OK);
        }
    }
}

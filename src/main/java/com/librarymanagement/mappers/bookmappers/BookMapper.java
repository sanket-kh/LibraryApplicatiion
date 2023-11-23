package com.librarymanagement.mappers.bookmappers;

import com.librarymanagement.entities.Book;
import com.librarymanagement.models.dtos.BaseBookDto;
import com.librarymanagement.models.dtos.UserBookDto;
import com.librarymanagement.models.requests.SaveBookRequest;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static BaseBookDto mapBookToBaseBookDto(Book book) {
        BaseBookDto baseBookDto = new BaseBookDto();
        baseBookDto.setCopies(book.getCopies());
        baseBookDto.setIsbn(book.getIsbn());
        baseBookDto.setTitle(book.getTitle());
        return baseBookDto;
    }

    public static Book mapBaseBookDtoToBook(BaseBookDto baseBookDto) {
        Book book = new Book();
        book.setCopies(baseBookDto.getCopies());
        book.setIsbn(baseBookDto.getIsbn());
        book.setTitle(baseBookDto.getTitle());
        return book;
    }

    public static UserBookDto mapBookToUserBookDto(Book book) {
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setIsbn(book.getIsbn());
        userBookDto.setTitle(book.getTitle());
        return userBookDto;
    }

    public static Book mapUserBookDtoToBook(UserBookDto userBookDto) {
        Book book = new Book();
        book.setIsbn(userBookDto.getIsbn());
        book.setTitle(userBookDto.getTitle());
        return book;
    }

    public static Book mapBookRequestToBook(SaveBookRequest saveBookRequest) {
        Book book = new Book();
        book.setIsbn(saveBookRequest.getIsbn());
        book.setTitle(saveBookRequest.getTitle());
        book.setCopies(saveBookRequest.getCopies());
        return book;
    }

    public static SaveBookRequest mapBookToSaveBookRequest(Book book) {
        SaveBookRequest saveBookRequest = new SaveBookRequest();
        saveBookRequest.setTitle(book.getTitle());
        saveBookRequest.setIsbn(book.getIsbn());
        saveBookRequest.setCopies(book.getCopies());
        return saveBookRequest;
    }

    public static List<BaseBookDto> mapBookListToBaseBookDtoList(List<Book> bookList) {
        List<BaseBookDto> baseBookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            BaseBookDto baseBookDto = BookMapper.mapBookToBaseBookDto(book);
            baseBookDtoList.add(baseBookDto);
        }
        return baseBookDtoList;
    }

}

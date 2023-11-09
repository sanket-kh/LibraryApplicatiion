package com.librarymanagement.mappers.bookmappers;

import com.librarymanagement.entities.Book;
import com.librarymanagement.models.dtos.BaseBookDto;
import com.librarymanagement.models.dtos.UserBookDto;

public class BookMapper {

    public static BaseBookDto bookToBaseBookDto(Book book) {
        BaseBookDto baseBookDto = new BaseBookDto();
        baseBookDto.setCopies(book.getCopies());
        baseBookDto.setIsbn(book.getIsbn());
        baseBookDto.setTitle(book.getTitle());
        return baseBookDto;
    }

    public static Book baseBookDtoToBook(BaseBookDto baseBookDto) {
        Book book = new Book();
        book.setCopies(baseBookDto.getCopies());
        book.setIsbn(baseBookDto.getIsbn());
        book.setTitle(baseBookDto.getTitle());
        return book;
    }

    public static UserBookDto bookToUserBookDto(Book book) {
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setIsbn(book.getIsbn());
        userBookDto.setTitle(book.getTitle());
        return userBookDto;
    }

    public static Book userBookDtoToBook(UserBookDto userBookDto) {
        Book book = new Book();
        book.setIsbn(userBookDto.getIsbn());
        book.setTitle(userBookDto.getTitle());
        return book;
    }

}

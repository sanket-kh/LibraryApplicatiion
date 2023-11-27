package com.librarymanagement.Daos;

import com.librarymanagement.entities.Book;
import com.librarymanagement.models.requests.BookSearchFilterRequest;

import java.util.List;

public interface BookDao {
    List<Book> bookSearchFilter(BookSearchFilterRequest bookSearchFilterRequest);
}

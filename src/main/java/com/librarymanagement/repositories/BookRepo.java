package com.librarymanagement.repositories;

import com.librarymanagement.Daos.BookDao;
import com.librarymanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>, BookDao {

    Book getBookByIsbn(Long isbn);

    Book getBookById(Long id);
}

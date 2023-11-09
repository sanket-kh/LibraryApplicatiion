package com.librarymanagement.repositories;

import com.librarymanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    Book getBookByIsbn(Integer isbn);

    Book getBookById(Long id);
}

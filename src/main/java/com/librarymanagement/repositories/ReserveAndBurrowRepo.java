package com.librarymanagement.repositories;

import com.librarymanagement.entities.Book;
import com.librarymanagement.entities.ReserveAndBorrow;
import com.librarymanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveAndBurrowRepo extends JpaRepository<ReserveAndBorrow, Long> {

List<ReserveAndBorrow> findReserveAndBurrowByBookAndUser(Book book, User user);

@Query( value = "" +
                "SELECT R from ReserveAndBorrow R " +
                "WHERE R.book = :book " +
                "AND R.user = :user" +
                " AND R.isIssued = TRUE ")
ReserveAndBorrow findExistingTransactionByBookAndUser(Book book, User user);

    @Query( value = "" +
            "SELECT R from ReserveAndBorrow R " +
            "WHERE R.user = :user" +
            " AND R.isIssued = TRUE ")
List<ReserveAndBorrow> findIssuedBooksByUser(User user);
}

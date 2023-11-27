package com.librarymanagement.mappers;

import com.librarymanagement.entities.ReserveAndBorrow;
import com.librarymanagement.models.dtos.reserveandburrowdtos.ReserveAndBorrowDto;

public class ReserveAndBorrowMapper {
    public static ReserveAndBorrowDto mapToReserveAndBorrowDto(ReserveAndBorrow reserveAndBorrow) {
        ReserveAndBorrowDto reserveAndBorrowDto = new ReserveAndBorrowDto();
        reserveAndBorrowDto.setBook(BookMapper.mapBookToBaseBookDto(reserveAndBorrow.getBook()));
        reserveAndBorrowDto.setUser(UserMapper.mapUserToBaseUserDto(reserveAndBorrow.getUser()));
        reserveAndBorrowDto.setReserved(reserveAndBorrow.getReserved());
        reserveAndBorrowDto.setFine(FineMapper.mapToFineDto(reserveAndBorrow.getFine()));
        reserveAndBorrowDto.setIsIssued(reserveAndBorrow.getIsIssued());
        reserveAndBorrowDto.setIssueDate(reserveAndBorrow.getIssueDate());
        reserveAndBorrowDto.setReturnDate(reserveAndBorrow.getReturnDate());
        return reserveAndBorrowDto;
    }
}

package com.librarymanagement.services;

import com.librarymanagement.entities.Fine;
import com.librarymanagement.models.dtos.finedtos.FineDto;
import com.librarymanagement.models.dtos.reserveandburrowdtos.ReserveAndBorrowDto;

public interface FineService {
    public FineDto calculateFine(ReserveAndBorrowDto reserveAndBorrowDto,int difference);
}

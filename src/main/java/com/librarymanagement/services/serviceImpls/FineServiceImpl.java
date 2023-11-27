package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.models.dtos.finedtos.FineDto;
import com.librarymanagement.models.dtos.reserveandburrowdtos.ReserveAndBorrowDto;
import com.librarymanagement.services.FineService;
import com.librarymanagement.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class FineServiceImpl implements FineService {


    @Override
    public FineDto calculateFine(ReserveAndBorrowDto reserveAndBorrowDto, int difference){
        FineDto fineDto = new FineDto();
        fineDto.setOverDue( Constants.MAX_BORROW_DURATION_PER_BOOK - difference);
        fineDto.setAmount(fineDto.getOverDue() * Constants.FINE_PER_DAY);
        fineDto.setIsPaid(Boolean.FALSE);
        return fineDto;

    };
}

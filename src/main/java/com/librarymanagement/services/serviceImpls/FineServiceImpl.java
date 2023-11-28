package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.entities.Fine;
import com.librarymanagement.services.FineService;
import com.librarymanagement.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class FineServiceImpl implements FineService {


    @Override
    public Fine calculateFine(int difference){
        Fine fine = new Fine();
        fine.setOverDue( difference - Constants.MAX_BORROW_DURATION_PER_BOOK );
        fine.setAmount(fine.getOverDue() * Constants.FINE_PER_DAY);
        fine.setIsPaid(Boolean.FALSE);
        return fine;
    };
}

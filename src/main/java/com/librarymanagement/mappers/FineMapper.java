package com.librarymanagement.mappers;

import com.librarymanagement.entities.Fine;
import com.librarymanagement.models.dtos.finedtos.FineDto;

public class FineMapper {

    public static FineDto mapToFineDto(Fine fine){
        FineDto fineDto = new FineDto();
        fineDto.setAmount(fine.getAmount());
        fineDto.setOverDue(fine.getOverDue());
        fineDto.setIsPaid(fine.getIsPaid());
        return fineDto;
    }
    public static Fine mapToFine(FineDto fineDto){
        Fine fine = new Fine();
        fine.setAmount(fineDto.getAmount());
        fine.setOverDue(fineDto.getOverDue());
        fine.setIsPaid(fineDto.getIsPaid());
        return fine;
    }
}

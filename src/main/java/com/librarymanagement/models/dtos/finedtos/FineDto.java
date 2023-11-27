package com.librarymanagement.models.dtos.finedtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FineDto {
    private Integer overDue;
    private Integer amount;
    private Boolean isPaid;

}

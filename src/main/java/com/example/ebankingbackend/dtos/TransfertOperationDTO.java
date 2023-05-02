package com.example.ebankingbackend.dtos;


import lombok.Data;

@Data
public class TransfertOperationDTO extends AccountOperationDTO {
    private String destinationAccountId;

}

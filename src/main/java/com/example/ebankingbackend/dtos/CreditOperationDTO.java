package com.example.ebankingbackend.dtos;


import lombok.Data;

@Data
public class CreditOperationDTO extends AccountOperationDTO {
    private String accountId;
}

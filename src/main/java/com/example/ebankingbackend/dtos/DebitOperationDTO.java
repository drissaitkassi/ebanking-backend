package com.example.ebankingbackend.dtos;


import lombok.Data;

@Data
public class DebitOperationDTO extends AccountOperationDTO {
    private String accountId;
}

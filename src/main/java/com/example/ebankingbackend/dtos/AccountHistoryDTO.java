package com.example.ebankingbackend.dtos;


import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {

    private String accId;
    private double balance;
    private String accountType;
    List<AccountOperationDTO> accountOperationDTOList;

}

package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.enums.OperationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long Id;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private BankAccountDTO bankAccountDTO;
    private String description;
}

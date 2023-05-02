package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.enums.OperationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private String accountId;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}

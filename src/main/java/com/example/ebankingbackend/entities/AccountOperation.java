package com.example.ebankingbackend.entities;


import com.example.ebankingbackend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date   operationDate;
    private double amount;
    private OperationType operationType;
    @ManyToOne
    private  BankAccount bankAccount;
    private String description;
}

package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.BankAccount;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SavingAcountDTO extends BankAccountDTO {
    private double interestRate;
    private CustomerDTO customerDTO;
}

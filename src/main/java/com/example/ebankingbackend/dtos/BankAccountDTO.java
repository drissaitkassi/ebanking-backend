package com.example.ebankingbackend.dtos;
import com.example.ebankingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data
public class BankAccountDTO {


    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus accountStatus;
    private CustomerDTO customerDTO;


}

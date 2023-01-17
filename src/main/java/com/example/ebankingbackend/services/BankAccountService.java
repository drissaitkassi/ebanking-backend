package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.CurrentAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.SavingAccountDTO;

import java.util.List;

public interface BankAccountService {
    // Customers
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> listCustomer();
    // Bank accounts
    CurrentAccountDTO saveCurrentAccount();
    SavingAccountDTO saveSavingAccount();

    CurrentAccountDTO getCurrentAccount();
    SavingAccountDTO getSavingAccount();

    // Account Operation

    void debit();
    void credit();
    void transfert();





}

package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.BankAccountDTO;

import java.util.List;

public interface BankAccountServiceDTOs {
    // Customers
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> listCustomer();
    // Bank accounts
    BankAccountDTO saveBankAccount(double initialBalance ,String type,Long customerId);

    BankAccountDTO getBankAccount(String accountID);

    // Account Operation
    void debit(double amount,String accountID,String description);
    void credit(double amount,String accountID,String description);
    void transfert(double amount,String accountSourceID,String accountDestinationId);




}

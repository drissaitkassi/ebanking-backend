package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.Customer;

import java.util.List;

public interface BankAccountService {
    // Customers
    Customer saveCustomer(Customer customer);
    List<Customer> listCustomer();
    // Bank accounts
    BankAccount saveBankAccount(double initialBalance , String type, Long customerId);

    BankAccount getBankAccount(String accountID);


    // Account Operation

    void debit(double amount,String accountID,String description);
    void credit(double amount,String accountID,String description);
    void transfert(double amount,String accountSourceID,String accountDestinationId);




}

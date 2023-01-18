package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAcount;
import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.entities.SavingAcount;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.exceptions.InsuffitientBalanceExeption;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    // Customers
    Customer saveCustomer(Customer customer);
    List<Customer> listCustomer();
    // Bank accounts

    CurrentAcount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption;

    SavingAcount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundExeption;

    BankAccount getBankAccount(String accountID) throws BankAccountNotFound;


    // Account Operation

    void debit(double amount,String accountID,String description) throws BankAccountNotFound, InsuffitientBalanceExeption;
    void credit(double amount,String accountID,String description) throws BankAccountNotFound;
    void transfert(double amount,String accountSourceID,String accountDestinationId) throws InsuffitientBalanceExeption, BankAccountNotFound;




}

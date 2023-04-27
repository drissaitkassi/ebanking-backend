package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.exceptions.InsuffitientBalanceExeption;

import java.util.List;

public interface BankAccountService {
    // Customers
    CustomerDTO saveCustomer(CustomerDTO customer);

    //updatecustomer take in a customerDTO  saves a customer to the entity  and return customer DTO
    CustomerDTO updateCustomer(CustomerDTO customer);

    void deleteCustomer(Long customerId);

    List<CustomerDTO> listCustomer();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundExeption;
    // Bank accounts

    CurrentAcountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption;

    SavingAcountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundExeption;

    BankAccountDTO getBankAccount(String accountID) throws BankAccountNotFound;


    // Account Operation

    void debit(double amount,String accountID,String description) throws BankAccountNotFound, InsuffitientBalanceExeption;
    void credit(double amount,String accountID,String description) throws BankAccountNotFound;
    void transfert(double amount,String accountSourceID,String accountDestinationId) throws InsuffitientBalanceExeption, BankAccountNotFound;


    List<BankAccountDTO> getBankAccounts();


    List<AccountOperationDTO> getAccountOperationsHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, BankAccountDTO bankAccountDTO);
}

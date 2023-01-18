package com.example.ebankingbackend.services;


import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, String type, Long customerId) {
        return null;
    }

    @Override
    public BankAccount getBankAccount(String accountID) {
        return null;
    }

    @Override
    public void debit(double amount, String accountID, String description) {

    }

    @Override
    public void credit(double amount, String accountID, String description) {

    }

    @Override
    public void transfert(double amount, String accountSourceID, String accountDestinationId) {

    }
}

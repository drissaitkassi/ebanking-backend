package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.CurrentAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.SavingAccountDTO;

import java.util.List;

public class BankAccountServiceImpl implements BankAccountService{
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return null;

    }

    @Override
    public List<CustomerDTO> listCustomer() {
        return null;
    }

    @Override
    public CurrentAccountDTO saveCurrentAccount() {
        return null;
    }

    @Override
    public SavingAccountDTO saveSavingAccount() {
        return null;
    }

    @Override
    public CurrentAccountDTO getCurrentAccount() {
        return null;
    }

    @Override
    public SavingAccountDTO getSavingAccount() {
        return null;
    }

    @Override
    public void debit() {

    }

    @Override
    public void credit() {

    }

    @Override
    public void transfert() {

    }
}

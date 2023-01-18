package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.mappers.BankServiceMapper;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAcountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceDTOImpl implements BankAccountServiceDTOs {


    private AccountOperationRepository accountOperationRepository;
    private BankAcountRepository bankAcountRepository;
    private CustomerRepository customerRepository;
    private BankServiceMapper bankServiceMapper;


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public List<CustomerDTO> listCustomer() {
        return null;
    }

    @Override
    public BankAccountDTO saveBankAccount(double initialBalance, String type, Long customerId) {
        return null;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountID) {
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

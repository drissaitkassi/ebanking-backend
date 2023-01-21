package com.example.ebankingbackend.services;


import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enums.AccountStatus;
import com.example.ebankingbackend.enums.OperationType;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.exceptions.InsuffitientBalanceExeption;
import com.example.ebankingbackend.mappers.BankServiceMapper;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAcountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private AccountOperationRepository accountOperationRepository;
    private BankAcountRepository bankAcountRepository;
    private CustomerRepository customerRepository;
    private BankServiceMapper bankServiceMapper;
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> customerList= customerRepository.findAll();
        return customerList.stream().map(customer -> bankServiceMapper.fromCustomer(customer)).toList();

    }





    @Override
    public CurrentAcount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)throw new CustomerNotFoundExeption("customer not found ");
        CurrentAcount currentBankAccount=new CurrentAcount();
        currentBankAccount.setOverDraft(overDraft);
        currentBankAccount.setId(UUID.randomUUID().toString());
        currentBankAccount.setBalance(initialBalance);
        currentBankAccount.setCustomer(customer);
        currentBankAccount.setCreatedAt(new Date());
        currentBankAccount.setAccountStatus(Math.random()>0.? AccountStatus.CREATED:AccountStatus.ACTIVATED);

        return bankAcountRepository.save(currentBankAccount);


    }

    @Override
    public SavingAcount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundExeption {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)throw new CustomerNotFoundExeption("customer not found ");
        SavingAcount savingAcount=new SavingAcount();
        savingAcount.setInterestRate(interestRate);
        savingAcount.setId(UUID.randomUUID().toString());
        savingAcount.setBalance(initialBalance);
        savingAcount.setCustomer(customer);
        savingAcount.setCreatedAt(new Date());
        savingAcount.setAccountStatus(Math.random()>0.? AccountStatus.CREATED:AccountStatus.ACTIVATED);

        return bankAcountRepository.save(savingAcount);
    }


    @Override
    public BankAccount getBankAccount(String accountID) throws BankAccountNotFound{
        BankAccount bankAccount= bankAcountRepository.findById(accountID).orElse(null);
        if (bankAccount==null)throw new BankAccountNotFound("BankAccount Not Found");

        return bankAccount;

    }

    @Override
    public void debit(double amount, String accountID, String description) throws BankAccountNotFound, InsuffitientBalanceExeption {
        BankAccount bankAccount = getBankAccount(accountID);

        if(amount >bankAccount.getBalance())throw new InsuffitientBalanceExeption("Inssuffitient Funds you Asshole") ;
        AccountOperation accountOperation=new AccountOperation();

        accountOperation.setBankAccount(bankAccount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription("retrait");
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        accountOperationRepository.save(accountOperation);
        bankAcountRepository.save(bankAccount);




    }

    @Override
    public void credit(double amount, String accountID, String description) throws BankAccountNotFound {
        BankAccount bankAccount = getBankAccount(accountID);

        AccountOperation accountOperation=new AccountOperation();

        accountOperation.setBankAccount(bankAccount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription("versement");
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        //todo
        //save the operation
        accountOperationRepository.save(accountOperation);
        //todo
        //save the bank account to reflect the changes on the database
        bankAcountRepository.save(bankAccount);

    }

    @Override
    public void transfert(double amount, String accountSourceID, String accountDestinationId) throws InsuffitientBalanceExeption, BankAccountNotFound {
        BankAccount bankAccountSource = getBankAccount(accountSourceID);
        if(amount >bankAccountSource.getBalance())throw new InsuffitientBalanceExeption("Inssuffitient Funds you Asshole") ;
        AccountOperation accountOperation=new AccountOperation();
        debit(amount,accountSourceID,"transfert from");
        credit(amount,accountDestinationId,"transfert to");


        accountOperationRepository.save(accountOperation);


    }
}

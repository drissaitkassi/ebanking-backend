package com.example.ebankingbackend;


import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enums.AccountStatus;
import com.example.ebankingbackend.enums.OperationType;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.exceptions.InsuffitientBalanceExeption;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAcountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import com.example.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);


    }
    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService , BankAcountRepository bankAcountRepository){
        return args -> {
            //create Customers

            Stream.of("hassan","mohamed","yassin")
                    .forEach(name-> {
                        CustomerDTO customer = new CustomerDTO();
                        customer.setName(name);
                        customer.setEmail(name+"@gmail.com");
                        bankAccountService.saveCustomer(customer);
                    });


            //Create Account for these Customers
            List<CustomerDTO> customers = bankAccountService.listCustomer();
            customers.forEach(cus->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*20000,4000,cus.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*14000,4.5,cus.getId());
                } catch (CustomerNotFoundExeption e) {
                    throw new RuntimeException(e);
                }
            });


            // Create Operations for these Accounts
            List<BankAccount> allAccounts = bankAcountRepository.findAll();
            allAccounts.forEach(bankAccount -> {
                for (int i = 0; i <10 ; i++) {


                try {
                    bankAccountService.credit(Math.random()*140000,bankAccount.getId(),"versement");
                } catch (BankAccountNotFound e) {
                    throw new RuntimeException(e);
                }
                try {
                    bankAccountService.debit(Math.random()*140,bankAccount.getId(),"retrait");
                } catch (BankAccountNotFound | InsuffitientBalanceExeption exception) {
                   exception.printStackTrace();
                }

                }

            });


        };
    }
   // @Bean
    CommandLineRunner start(AccountOperationRepository accountOperationRepository
    , CustomerRepository customerRepository, BankAcountRepository bankAcountRepository){
        return args -> {
            Stream.of("hassan","yassin","aicha","mohammed","mustapha",
                    "abdelilah","souhail","adil","abdelghaffar").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@email.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAcount currentAcount =new CurrentAcount();
                currentAcount.setId(UUID.randomUUID().toString());
                currentAcount.setBalance(Math.random()*4000);
                currentAcount.setOverDraft(9000);
                currentAcount.setCreatedAt(new Date());
                currentAcount.setAccountStatus(AccountStatus.CREATED);
                currentAcount.setCustomer(customer);
                bankAcountRepository.save(currentAcount);

                SavingAcount savingAcount =new SavingAcount();
                savingAcount.setId(UUID.randomUUID().toString());
                savingAcount.setBalance(Math.random()*4000);
                savingAcount.setInterestRate(4.5);
                savingAcount.setCreatedAt(new Date());
                savingAcount.setAccountStatus(AccountStatus.ACTIVATED);
                savingAcount.setCustomer(customer);
                bankAcountRepository.save(savingAcount);




                bankAcountRepository.findAll().forEach(bankAccount -> {


                    for (int i = 0; i <20 ; i++) {
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setOperationDate(new Date());
                        accountOperation.setBankAccount(bankAccount);
                        accountOperation.setOperationType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                        accountOperation.setAmount(Math.random()*3000);
                        accountOperationRepository.save(accountOperation);
                    }

                });


            });


        };
    }
}

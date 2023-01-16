package com.example.ebankingbackend;


import com.example.ebankingbackend.entities.AccountOperation;
import com.example.ebankingbackend.entities.CurrentAcount;
import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.entities.SavingAcount;
import com.example.ebankingbackend.enums.AccountStatus;
import com.example.ebankingbackend.enums.OperationType;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAcountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);


    }
    @Bean
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

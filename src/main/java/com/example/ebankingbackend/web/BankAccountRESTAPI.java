package com.example.ebankingbackend.web;


import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRESTAPI {

    private BankAccountService bankAccountService;

    @GetMapping(path = "/api/v1/bank")
    public List<BankAccountDTO>getBank(){
        return bankAccountService.getBankAccounts();

    }

    @GetMapping(path = "/api/v1/bank/{id}")
    public BankAccountDTO getBankAcount(@PathVariable(name = "id") String accId) throws BankAccountNotFound {
        return bankAccountService.getBankAccount(accId);

    }

    @GetMapping(path = "/api/v1/bank/operations/{accountId}")
    public AccountHistoryDTO getAccountOperationsHistory(@PathVariable(name = "accountId") String accId) throws BankAccountNotFound {
        BankAccountDTO bk= bankAccountService.getBankAccount(accId);
        return bankAccountService.getAccountHistory(accId,bk);
    }

    @PostMapping(path ="/api/v1/bank/accounts/saving" )
    public SavingAcountDTO saveSavingAccount(@RequestBody SavingAcountDTO savingAcountDTO) throws CustomerNotFoundExeption {
        return bankAccountService.saveSavingBankAccount(savingAcountDTO.getBalance(),savingAcountDTO.getInterestRate(),savingAcountDTO.getCustomerDTO().getId());
    }
    /*@PostMapping(path ="/api/v1/bank/accounts/current" )

    public CurrentAcountDTO saveCurrentAccount(){

    }*/

}

package com.example.ebankingbackend.web;


import com.example.ebankingbackend.dtos.AccountOperationDTO;
import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<AccountOperationDTO> getAccountOperationsHistory(@PathVariable(name = "accountId") String accId){
        return bankAccountService.getAccountOperationsHistory(accId);
    }


}

package com.example.ebankingbackend.web;


import com.example.ebankingbackend.dtos.AccountOperationDTO;
//import com.example.ebankingbackend.dtos.CreditOperationDTO;
import com.example.ebankingbackend.dtos.CreditOperationDTO;
import com.example.ebankingbackend.dtos.DebitOperationDTO;
import com.example.ebankingbackend.dtos.TransfertOperationDTO;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.exceptions.InsuffitientBalanceExeption;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AccountOperationRESTAPI {

    private BankAccountService bankAccountService;

    @PostMapping(path = "/api/v1/operations/credit")
    public void setCredit(@RequestBody CreditOperationDTO creditOperationDTO) throws BankAccountNotFound {
        bankAccountService.credit(creditOperationDTO.getAmount(),creditOperationDTO.getAccountId(),creditOperationDTO.getDescription());

    }

    @PostMapping(path = "/api/v1/operations/debit")
    public void setDebit(@RequestBody DebitOperationDTO debitOperationDTO) throws InsuffitientBalanceExeption, BankAccountNotFound {
        bankAccountService.debit(debitOperationDTO.getAmount(), debitOperationDTO.getAccountId(), debitOperationDTO.getDescription());
    }

    @PostMapping(path = "/api/v1/operations/transfert")
    public void setTransfer(@RequestBody TransfertOperationDTO transfertOperationDTO) throws InsuffitientBalanceExeption, BankAccountNotFound {
        bankAccountService.transfert(transfertOperationDTO.getAmount(), transfertOperationDTO.getAccountId(), transfertOperationDTO.getDestinationAccountId());

    }
}

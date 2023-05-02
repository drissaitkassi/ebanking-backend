package com.example.ebankingbackend.web;


import com.example.ebankingbackend.dtos.AccountOperationDTO;
//import com.example.ebankingbackend.dtos.CreditOperationDTO;
import com.example.ebankingbackend.dtos.CreditOperationDTO;
import com.example.ebankingbackend.exceptions.BankAccountNotFound;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountOperationRESTAPI {

    private BankAccountService bankAccountService;

    @PostMapping(path = "/api/v1/operations/credit")
    public void setCredit(@RequestBody CreditOperationDTO creditOperationDTO) throws BankAccountNotFound {
        System.out.println("===========================================================");
        System.out.println(creditOperationDTO.getAmount());
        System.out.println("===========================================================");
        System.out.println(creditOperationDTO.getAccountId());
        System.out.println("===========================================================");
        System.out.println(creditOperationDTO.getDescription());
        System.out.println("===========================================================");
        bankAccountService.credit(creditOperationDTO.getAmount(),creditOperationDTO.getAccountId(),creditOperationDTO.getDescription());

    }

   /* @PostMapping(path = "/api/v1/operations/debit")
    public void setDebit(@RequestBody  )*/
}

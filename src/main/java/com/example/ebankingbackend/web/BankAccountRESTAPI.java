package com.example.ebankingbackend.web;

import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRESTAPI {

    BankAccountService bankAccountService;

    @GetMapping(path = "/customers")
    public List<Customer> customers(){
        return bankAccountService.listCustomer();
    }

}

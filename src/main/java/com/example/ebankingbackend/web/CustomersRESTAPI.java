package com.example.ebankingbackend.web;

import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.exceptions.CustomerNotFoundExeption;
import com.example.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomersRESTAPI {

    BankAccountService bankAccountService;

    @GetMapping(path = "/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomer();
    }

    @GetMapping(path = "/customers/{id}")
    public CustomerDTO customer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundExeption {
        return bankAccountService.getCustomer(customerId);
    }


    @PostMapping(path = "/customers")
    public CustomerDTO saveCus(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping(path = "/customers/{id}")
    public CustomerDTO updatecus(@PathVariable(name = "id")Long customerId ,@RequestBody CustomerDTO customerDTO){
        //set the id of that customerDTO that we got from client to the value of the path variable
        customerDTO.setId(customerId);
        // call update methode from the service which does the same thins as save
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping(path = "/customers/{id}")
    public void deleteCus(@PathVariable(name = "id") Long customerId){
         bankAccountService.deleteCustomer(customerId);
    }
}

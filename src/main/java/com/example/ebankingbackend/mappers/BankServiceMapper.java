package com.example.ebankingbackend.mappers;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceMapper {


    public CustomerDTO fromCustomer(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;

    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;

    }


    public BankAccountDTO fromBankAccount(BankAccount bankAccount){

        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
        bankAccountDTO.setCustomerDTO(fromCustomer(bankAccount.getCustomer()));
        return bankAccountDTO;

    }

    public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO){

        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        bankAccount.setCustomer(fromCustomerDTO(bankAccountDTO.getCustomerDTO()));
        return bankAccount;

    }


    public SavingAcountDTO fromSavingBankAccount(SavingAcount savingAcount){

     SavingAcountDTO savingAcountDTO = new SavingAcountDTO();
        BeanUtils.copyProperties(savingAcount,savingAcountDTO);
        savingAcountDTO.setCustomerDTO(fromCustomer(savingAcount.getCustomer()));
        savingAcountDTO.setAccountType(savingAcount.getClass().getSimpleName());
        return savingAcountDTO;

    }

    public SavingAcount fromSavingBankAccountDTO(SavingAcountDTO savingAcountDTO){

        SavingAcount savingAcount = new SavingAcount();
        BeanUtils.copyProperties(savingAcountDTO,savingAcount);
        savingAcount.setCustomer(fromCustomerDTO(savingAcountDTO.getCustomerDTO()));
        return savingAcount;

    }


    public CurrentAcountDTO fromCurrentBankAccount(CurrentAcount currentAcount){

        CurrentAcountDTO currentAcountDTO = new CurrentAcountDTO();
        BeanUtils.copyProperties(currentAcount,currentAcountDTO);
        currentAcountDTO.setCustomerDTO(fromCustomer(currentAcount.getCustomer()));
        currentAcountDTO.setAccountType(currentAcount.getClass().getSimpleName());
        return currentAcountDTO;

    }

    public CurrentAcount fromCurrentBankAccountDTO(CurrentAcountDTO currentAcountDTO){

        CurrentAcount currentAcount = new CurrentAcount();
        BeanUtils.copyProperties(currentAcountDTO,currentAcount);
        currentAcount.setCustomer(fromCustomerDTO(currentAcountDTO.getCustomerDTO()));
        return currentAcount;

    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){

        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
       // accountOperationDTO.setBankAccountDTO(fromBankAccount(accountOperation.getBankAccount()));
        return accountOperationDTO;

    }

    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO){

        AccountOperation accountOperation = new AccountOperation();
        BeanUtils.copyProperties(accountOperationDTO,accountOperation);
       //accountOperation.setBankAccount(fromBankAccountDTO(accountOperationDTO.getBankAccountDTO()));
        return accountOperation;

    }

    public AccountHistoryDTO fromAccountHistoryOperations(List<AccountOperationDTO> accountOperationDTO,BankAccountDTO bankAccountDTO){


        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        accountHistoryDTO.setAccId(bankAccountDTO.getId());
        accountHistoryDTO.setAccountType(bankAccountDTO.getAccountType());
        accountHistoryDTO.setBalance(bankAccountDTO.getBalance());
        accountHistoryDTO.setAccountOperationDTOList(accountOperationDTO);

        return accountHistoryDTO;

    }
}

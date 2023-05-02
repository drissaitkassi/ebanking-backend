package com.example.ebankingbackend.services;


import com.example.ebankingbackend.dtos.*;
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
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private AccountOperationRepository accountOperationRepository;
    private BankAcountRepository bankAcountRepository;
    private CustomerRepository customerRepository;
    private BankServiceMapper bankServiceMapper;

    //Customers
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {

        Customer customer1 = bankServiceMapper.fromCustomerDTO(customer);
        Customer savedCustomer =customerRepository.save(customer1);
        return bankServiceMapper.fromCustomer(savedCustomer);

    }

    //updateCustomer take in a customerDTO  saves a customer to the entity  and return customer DTO
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customer) {
        Customer customer1 = bankServiceMapper.fromCustomerDTO(customer);
        Customer savedCustomer =customerRepository.save(customer1);
        return bankServiceMapper.fromCustomer(savedCustomer);

    }

    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);

    }

    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> customerList= customerRepository.findAll();
        return customerList.stream().map(customer -> bankServiceMapper.fromCustomer(customer)).toList();

    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundExeption {

        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundExeption("customer not found"));
        return bankServiceMapper.fromCustomer(customer);
    }


    // BankAccount

  /*  @Override
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
*/

    @Override
    public CurrentAcountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption {

        // even though it returns CurrentAccountDTO, but it takes in normal CurrentAccount from the parameter
        // that's why we still need a normal Customer Object  that we can use on the normal CurrentAccount object
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)throw new CustomerNotFoundExeption("customer not found ");
        CurrentAcount currentBankAccount=new CurrentAcount();
        currentBankAccount.setOverDraft(overDraft);
        currentBankAccount.setId(UUID.randomUUID().toString());
        currentBankAccount.setBalance(initialBalance);
        currentBankAccount.setCustomer(customer);
        currentBankAccount.setCreatedAt(new Date());
        currentBankAccount.setAccountStatus(Math.random()>0.? AccountStatus.CREATED:AccountStatus.ACTIVATED);
        CurrentAcount savedCurrentAccount = bankAcountRepository.save(currentBankAccount);
        //after saving the normal  CurrentAccount object ,then we can return the  CurrentAccountDTO object using the mapper
        //****************** but we still need to map the normal Customer Object to CustomerDTO Object  inside the CurrentAccount Mapper itself **********************
        return bankServiceMapper.fromCurrentBankAccount(savedCurrentAccount);


    }

    @Override
    public SavingAcountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundExeption {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)throw new CustomerNotFoundExeption("customer not found ");
        SavingAcount savingAcount=new SavingAcount();
        savingAcount.setInterestRate(interestRate);
        savingAcount.setId(UUID.randomUUID().toString());
        savingAcount.setBalance(initialBalance);
        savingAcount.setCustomer(customer);
        savingAcount.setCreatedAt(new Date());
        savingAcount.setAccountStatus(Math.random()>0.? AccountStatus.CREATED:AccountStatus.ACTIVATED);

        SavingAcount savedSavingAccount = bankAcountRepository.save(savingAcount);
        return bankServiceMapper.fromSavingBankAccount(savedSavingAccount);
    }

    @Override
    public BankAccountDTO getBankAccount(String accountID) throws BankAccountNotFound{
        BankAccount bankAccount= bankAcountRepository.findById(accountID).orElse(null);
        if (bankAccount==null)throw new BankAccountNotFound("BankAccount Not Found");
       return bankServiceMapper.fromBankAccount(bankAccount);


    }

    @Override
    public BankAccount getRawBankAccount(String accountID) throws BankAccountNotFound{
        BankAccount bankAccount= bankAcountRepository.findById(accountID).orElse(null);
        if (bankAccount==null)throw new BankAccountNotFound("BankAccount Not Found");
        return bankAccount;


    }

    // AccountOperations

    @Override
    public void debit(double amount, String accountID, String description) throws BankAccountNotFound, InsuffitientBalanceExeption {
        //BankAccountDTO bankAccountDTO = getBankAccount(accountID);
        BankAccount bankAccount= bankAcountRepository.findById(accountID).orElseThrow(()->new BankAccountNotFound("Bank Account not found"));
        if(amount >bankAccount.getBalance())throw new InsuffitientBalanceExeption("Inssuffitient Funds you Asshole") ;
        //AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        AccountOperation accountOperation=new AccountOperation();

        accountOperation.setBankAccount(bankAccount);
        //accountOperation.setAccountId(accountID);
        accountOperation.setOperationDate(new Date());
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
       //map to AccountOperation to save on repository
        //AccountOperation accountOperationToBeSaved=bankServiceMapper.fromAccountOperationDTO(accountOperationDTO);
        accountOperationRepository.save(accountOperation);
        //map to bank account to save on repository
       //BankAccount bankAccountToBeSaved = bankServiceMapper.fromBankAccountDTO(bankAccount);
        bankAcountRepository.save(bankAccount);




    }

    @Override
    public void credit(double amount, String accountId, String description) throws BankAccountNotFound {
        BankAccount bankAccount = getRawBankAccount(accountId);
        AccountOperation accountOperation=new AccountOperation();

        //accountOperationDTO.setBankAccountDTO(bankAccount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        //map to AccountOperation to save on repository
        //AccountOperation accountOperationToBeSaved = bankServiceMapper.fromAccountOperationDTO(accountOperationDTO);
        //save the operation
        accountOperationRepository.save(accountOperation);
        ///map to bank account to save on repository
       // BankAccount bankAccountToBeSaved = bankServiceMapper.fromBankAccountDTO(bankAccount);
        //save the bank account to reflect the changes on the database
        bankAcountRepository.save(bankAccount);

    }

    @Override
    public void transfert(double amount, String accountSourceID, String accountDestinationId) throws InsuffitientBalanceExeption, BankAccountNotFound {
        BankAccountDTO bankAccountSourceDTO = getBankAccount(accountSourceID);
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        debit(amount,accountSourceID,"transfert from");
        credit(amount,accountDestinationId,"transfert to");

        //map to AccountOperation to save on repository
        AccountOperation accountOperationToBeSaved=bankServiceMapper.fromAccountOperationDTO(accountOperationDTO);
        accountOperationRepository.save(accountOperationToBeSaved);

    }

    @Override
    public List<BankAccountDTO> getBankAccounts(){
        List<BankAccount> bankAccountList =bankAcountRepository.findAll();
        List<BankAccountDTO> bankAccountsDTOs = bankAccountList.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAcount){
                SavingAcount savingAcount =(SavingAcount) bankAccount;
              return   bankServiceMapper.fromSavingBankAccount(savingAcount);
            }else{
                CurrentAcount currentAcount=(CurrentAcount) bankAccount;
                return bankServiceMapper.fromCurrentBankAccount(currentAcount);
            }
        }).toList();
        return bankAccountsDTOs;
    }

    @Override
    public List<AccountOperationDTO> getAccountOperationsHistory(String accountId){
        // get operations based on account id
        List<AccountOperation> accountOperation=accountOperationRepository.findByBankAccountId(accountId);
        //map operation to dto
        List<AccountOperationDTO> accountOperationDTOS = accountOperation.stream().map(ops -> bankServiceMapper.fromAccountOperation(ops)).toList();
        //return results
        return accountOperationDTOS;

    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, BankAccountDTO bankAccountDTO){
      return  bankServiceMapper.fromAccountHistoryOperations(getAccountOperationsHistory(accountId),bankAccountDTO);

    }
}

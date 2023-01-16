package com.example.ebankingbackend.repositories;

import com.example.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAcountRepository extends JpaRepository<BankAccount,String> {
}

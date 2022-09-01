package com.amdocs.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.Bank.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,String>{

}

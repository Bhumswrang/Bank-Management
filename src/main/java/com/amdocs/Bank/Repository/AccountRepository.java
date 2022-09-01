package com.amdocs.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.Bank.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

}

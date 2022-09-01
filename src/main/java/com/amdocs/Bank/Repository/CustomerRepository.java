package com.amdocs.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.Bank.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}

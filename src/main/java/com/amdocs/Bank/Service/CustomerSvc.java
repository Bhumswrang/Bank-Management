package com.amdocs.Bank.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amdocs.Bank.Entity.Account;
import com.amdocs.Bank.Entity.Customer;
import com.amdocs.Bank.Repository.AccountRepository;
import com.amdocs.Bank.Repository.CustomerRepository;

@Service
public class CustomerSvc {

	@Autowired
	public CustomerRepository customerRepository;

	
	@Autowired 
	public AccountRepository accountRepository;
	
	@Autowired
	AccountSvc accountSvc;

	// To get all customer details
	public ResponseEntity<Object> getAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// To get account using cid
	public ResponseEntity<Object> getById(Integer cid) {
		return new ResponseEntity<>(customerRepository.findById(cid), HttpStatus.OK);
	}

	
	// To add customer

	public ResponseEntity<Object> add(Customer customer) {

		if (!customerRepository.existsById(customer.getCid())) {
			
			Random rnd = new Random();
			String accountNo = String.valueOf(rnd.nextInt(1_000_000_000) + (rnd.nextInt(90) + 10) * 1_000_000_000L);
			
			customer.setAc_no(accountNo);
			customerRepository.save(customer);
			Account account = new Account();
			account.setAcc_no(accountNo);
			accountSvc.add(account);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		return new ResponseEntity<>("Customer Id Conflict", HttpStatus.BAD_REQUEST);

	}

	// To update the customer detail
	public ResponseEntity<Object> update(Customer customer) {
		if (customerRepository.existsById(customer.getCid())) {

			customerRepository.save(customer);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>("Record Not found", HttpStatus.NOT_FOUND);
		}

	}

	// To delete the customer details from customer table along with account detail
	// from account table
	public ResponseEntity<String> delete(Integer cid) {

		if (customerRepository.existsById(cid)) {
			
			accountRepository.deleteById(customerRepository.findById(cid).get().getAc_no());
			
			customerRepository.deleteById(cid);

			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Record Not found", HttpStatus.NOT_FOUND);
		}
	}

	// To search detail using name

	public ResponseEntity<Object> searchByName(String cname) {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);

		List<Customer> cst = new ArrayList<>();
		boolean flg = false;

		for (Customer c : customers) {
			if (c.getCname().toLowerCase().contains(cname.toLowerCase())) {
				flg = true;
				cst.add(c);
			}
		}
		if (flg)
			return new ResponseEntity<>(cst, HttpStatus.OK);

		else
			return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
	}

	
	
	// To get customer name by account
	public Customer getCustomerByAccount(String ac_no) {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);

		for (Customer c : customers) {
			if (c.getAc_no().equals(ac_no)) {
				return c;
			}
		}

		return null;
	}

}

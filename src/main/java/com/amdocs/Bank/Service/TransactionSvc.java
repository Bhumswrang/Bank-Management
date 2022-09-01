package com.amdocs.Bank.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amdocs.Bank.Entity.Account;
import com.amdocs.Bank.Entity.Customer;
import com.amdocs.Bank.Entity.Transaction;
import com.amdocs.Bank.Enum.TransactionType;
import com.amdocs.Bank.Repository.AccountRepository;
import com.amdocs.Bank.Repository.TransactionRepository;

@Service
public class TransactionSvc {
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CustomerSvc customerSvc;

	@Autowired
	AccountRepository accountRepository;

//To get all transaction details
	public ResponseEntity<Object> getAll() {
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findAll().forEach(transactions::add);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
	
	

// To get transaction details by id
	public ResponseEntity<Object> getById(String ac_no) {

		return new ResponseEntity<>(transactionRepository.findById(ac_no), HttpStatus.OK);
	}

// To add the transactions

	public ResponseEntity<Object> add(Transaction transaction) {

		Account account = new Account();
		account = accountRepository.findById(transaction.getAc_no()).get();

		if (account != null) {

			if ((transaction.getAmount() <= account.getBalance()
					&& transaction.getTrans_type() == TransactionType.Debited)
					|| transaction.getTrans_type() == TransactionType.Credited) {

				// [1]To set new transaction id
				if (transaction.getTrans_id() == null) {
					String transId = String.valueOf(UUID.randomUUID());
					transaction.setTrans_id(transId);
				}

				// [2]to set name by account number
				Customer cst = new Customer();
				cst = customerSvc.getCustomerByAccount(transaction.getAc_no());
				transaction.setCust_name(cst.getCname());
				
				// to set date and time
				transaction.setDate_time(LocalDateTime.now());
				transactionRepository.save(transaction);

				

				// after transaction we have to maintain our account and customer table balance

				if (transaction.getTrans_type() == TransactionType.Debited) {
					account.setBalance(account.getBalance() - transaction.getAmount());
					accountRepository.save(account);
				}
				if (transaction.getTrans_type() == TransactionType.Credited) {
					account.setBalance(account.getBalance() + transaction.getAmount());
					accountRepository.save(account);
				}

				return new ResponseEntity<>(transaction, HttpStatus.OK);

			}

			return new ResponseEntity<>("Insufficient balance in your Account : " + account.getAcc_no(),
					HttpStatus.EXPECTATION_FAILED);

		} else {
			return new ResponseEntity<>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}

	}
	
	
	//To get the transactions using account number
	public ResponseEntity<Object> getByAccount(String ac_no) {

		List<Transaction> transaction = new ArrayList<>();
		transactionRepository.findAll().forEach(transaction::add);;
		List<Transaction> trsc = new ArrayList<>();
		boolean f = false;
		for(Transaction tc: transaction) {
			if(tc.getAc_no().equals(ac_no)) {
				trsc.add(tc);
				f=true;
			}
		}
		if(f) {
			return new ResponseEntity<>(trsc,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("No transaction history",HttpStatus.NO_CONTENT);
	}
	

}

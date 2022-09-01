package com.amdocs.Bank.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.amdocs.Bank.Entity.Account;
import com.amdocs.Bank.Enum.AccountType;
import com.amdocs.Bank.Repository.AccountRepository;

@Service
public class AccountSvc {
	@Autowired
	public AccountRepository accountRepository;
	
	

//To get all account details
	public ResponseEntity<Object> getAll() {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(accounts::add);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

//To get account using account number
	public ResponseEntity<Object> getByAccount(String acc_no) {
		return new ResponseEntity<>(accountRepository.findById(acc_no), HttpStatus.OK);
	}

//To add account detail
	public ResponseEntity<Object> add(Account account) {
		if (account.getAcc_no() == null) {
			Random rnd = new Random();
			String accountNo = String.valueOf(rnd.nextInt(1_000_000_000) + (rnd.nextInt(90) + 10) * 1_000_000_000L);
			account.setAcc_no(accountNo);
		}

		if (!accountRepository.existsById(account.getAcc_no())) {

			account.setLdt(LocalDateTime.now());
			if (account.getType() == null)
				account.setType(AccountType.Savings);
			account.setBalance(0.0d);
			accountRepository.save(account);
			return new ResponseEntity<>(account, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
		}

	}

//To delete account details but cannot be done through controller
	public ResponseEntity<String> deleteAccount(String acc_no) {
		
			if (accountRepository.existsById(acc_no)) {
				accountRepository.deleteById(acc_no);
				return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Record Not found", HttpStatus.BAD_REQUEST);
			}	
		
		
	}

	
//To update account details

	public ResponseEntity<Object> update(Account account) {
		if (account!=null && accountRepository.existsById(account.getAcc_no())) {
			if(account.getType()==null) {
				account.setType(accountRepository.findById(account.getAcc_no()).get().getType());
			}
			if(account.getBalance()==null) {
				account.setBalance(accountRepository.findById(account.getAcc_no()).get().getBalance());
			}
			
			if(account.getLdt()==null) {
				account.setLdt(accountRepository.findById(account.getAcc_no()).get().getLdt());
			}
			accountRepository.save(account);
			return new ResponseEntity<>(account, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>("Record Not found", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	

}

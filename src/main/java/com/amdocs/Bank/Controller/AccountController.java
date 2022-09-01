package com.amdocs.Bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Bank.Entity.Account;
import com.amdocs.Bank.Service.AccountSvc;

@RestController
public class AccountController {
	
	@Autowired
	private AccountSvc accountSvc;

	@RequestMapping(value="/account/accounts")
	public ResponseEntity<Object> getAll(){
		return accountSvc.getAll();
	}
	
	@RequestMapping(value="/account/accounts/{acc_no}")
	public ResponseEntity<Object> getByAccount(@PathVariable String acc_no){
		return accountSvc.getByAccount(acc_no);
	}
	
	@RequestMapping(value="/account/accounts", method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Account account){
		return accountSvc.add(account);
	}
	
	//we cannot delete account detail from controller
	
	@RequestMapping(value="/account/accounts", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String acc_no){
		return accountSvc.deleteAccount(acc_no);
	}
	
	
	@RequestMapping(value="/account/accounts", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Account account){
		return accountSvc.update(account);
	}
	
	
}

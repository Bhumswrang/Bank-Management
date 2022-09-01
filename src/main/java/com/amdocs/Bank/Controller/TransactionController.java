package com.amdocs.Bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Bank.Entity.Transaction;
import com.amdocs.Bank.Service.TransactionSvc;

@RestController
public class TransactionController {

	@Autowired 
	private TransactionSvc transactionSvc;
	
	@RequestMapping(value = "/transaction/transactions")
	ResponseEntity<Object> getAll(){
		return  transactionSvc.getAll();
	}
	
	
	@RequestMapping(value = "/transaction/transactions/{trans_id}")
	ResponseEntity<Object> getById(@PathVariable String trans_id){
		return  transactionSvc.getById(trans_id);
	}
	
	
	@RequestMapping(value = "/transaction/transactions/", method = RequestMethod.POST)
	ResponseEntity<Object> add(@RequestBody Transaction transaction){
		return  transactionSvc.add(transaction);
	}
	
	
	@RequestMapping(value = "/transaction/transactions/account/{ac_no}")
	ResponseEntity<Object> getByAccount(@PathVariable String ac_no){
		return  transactionSvc.getByAccount(ac_no);
	}
	

}

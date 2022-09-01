package com.amdocs.Bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.Bank.Entity.Customer;
import com.amdocs.Bank.Service.CustomerSvc;

@RestController
public class CustomerController {

	@Autowired 
	CustomerSvc customerSvc;
	
	@RequestMapping(value = "/customer/customers")
	ResponseEntity<Object> getAll(){
		return  customerSvc.getAll();
	}
	
	@RequestMapping(value = "/customer/customers/{cid}")
	ResponseEntity<Object> getById(@PathVariable Integer cid){
		return  customerSvc.getById(cid);
	}
	
	
	
	
	@RequestMapping(value = "/customer/customers", method = RequestMethod.POST)
	ResponseEntity<Object> add(@RequestBody Customer customer){
		return  customerSvc.add(customer);
	}
	
	

	@RequestMapping(value = "/customer/customers", method = RequestMethod.PUT)
	ResponseEntity<Object> update(@RequestBody Customer customer){
		return  customerSvc.update(customer);
	}
	
	
	
	@RequestMapping(value = "/customer/customers/{cid}", method = RequestMethod.DELETE)
	ResponseEntity<String> delete(@PathVariable Integer cid){
		return  customerSvc.delete(cid);
	}
	
	
	@RequestMapping(value = "/customer/name/{cname}")
	ResponseEntity<Object> searchByName(@PathVariable String cname ){
		return  customerSvc.searchByName(cname);
	}
	
}

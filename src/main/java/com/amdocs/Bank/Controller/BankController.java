package com.amdocs.Bank.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BankController {
	@RequestMapping("")
	public String hello() {
		return "Hello World!";
	}

}

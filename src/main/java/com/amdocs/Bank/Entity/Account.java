package com.amdocs.Bank.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.amdocs.Bank.Enum.AccountType;

@Entity
@Table(name = "account")

public class Account {

	@Id
	@Column(name = "account_number", nullable = false, unique = true)
	private String acc_no;

	@Column(name = "account_balance")
	private Double balance;

	@Column(name = "Date_and_Time")
	private LocalDateTime ldt;

	@Column(name = "Account_Type")
	@Enumerated(EnumType.STRING)
	private AccountType type;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Double balance, AccountType type) {
		super();
		// this.acc_no = acc_no;
		this.balance = balance;
		this.type = type;

	}

	public Account(AccountType type) {
		super();
		// this.acc_no = acc_no;
		// this.balance = balance;
		this.type = type;

	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setLdt(LocalDateTime ldt) {
		// LocalDateTime ltd1 = LocalDateTime.now();
		this.ldt = ldt;
	}

}

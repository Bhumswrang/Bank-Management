package com.amdocs.Bank.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amdocs.Bank.Enum.TransactionType;

@Entity
@Table(name = "tarnsactions")
public class Transaction {
	
@Id
@Column(name="Transaction_id",nullable=false,unique=true)
private String trans_id;

@Column(name="Account_Number",nullable=false)
private String ac_no;

@Column(name="Customer_Name",nullable=false)
private String cust_name;

@Column(name="Debit_Credit",nullable=false)
private TransactionType trans_type;

@Column(name="Amount")
private Double amount;

@Column(name="Transaction_Time")
private LocalDateTime date_time;




public Transaction() {
	super();
	// TODO Auto-generated constructor stub
}




public Transaction(String ac_no, TransactionType trans_type, Double amount) {
	super();
	this.ac_no = ac_no;
	this.trans_type = trans_type;
	this.amount = amount;
}




public Transaction(String trans_id, String ac_no, String cust_name, TransactionType trans_type, Double amount,
		LocalDateTime date_time) {
	super();
	this.trans_id = trans_id;
	this.ac_no = ac_no;
	this.cust_name = cust_name;
	this.trans_type = trans_type;
	this.amount = amount;
	this.date_time = date_time;
}




public String getTrans_id() {
	return trans_id;
}




public void setTrans_id(String trans_id) {
	this.trans_id = trans_id;
}




public String getAc_no() {
	return ac_no;
}




public void setAc_no(String ac_no) {
	this.ac_no = ac_no;
}




public String getCust_name() {
	return cust_name;
}




public void setCust_name(String cust_name) {
	this.cust_name = cust_name;
}




public TransactionType getTrans_type() {
	return trans_type;
}




public void setTrans_type(TransactionType trans_type) {
	this.trans_type = trans_type;
}




public Double getAmount() {
	return amount;
}




public void setAmount(Double amount) {
	this.amount = amount;
}




public LocalDateTime getDate_time() {
	return date_time;
}




public void setDate_time(LocalDateTime date_time) {
	this.date_time = date_time;
}



//Transactions: 
//trans_id (primary key), ac_no(foreign key), cust_name, trans_type, amount, date_time, 


}

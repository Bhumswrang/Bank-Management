package com.amdocs.Bank.Entity;

import javax.persistence.*;


@Entity
@Table(name="customer")
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int cid;

@Column(name="cname",nullable=false)
private String cname;


@Column(name="adhar",nullable=false,length=12,unique=true)
private String adhar;


@Column(name="pan",nullable=false,length=15,unique=true)
private String pan;



@Column(name="phone",nullable=false,length=13)
private String phone;

@Column(name="email",nullable=false)
private String email;

@Column(name="Account_Number",nullable=false,unique=true)
private String ac_no;









public Customer() {
	super();
	// TODO Auto-generated constructor stub
}




public Customer(String cname, String adhar, String pan, String phone, String email) {
	super();
	this.cname = cname;
	this.adhar = adhar;
	this.pan = pan;
	this.phone = phone;
	this.email = email;
}














public int getCid() {
	return cid;
}


public void setCid(int cid) {
	this.cid = cid;
}


public String getCname() {
	return cname;
}


public void setCname(String cname) {
	this.cname = cname;
}


public String getAdhar() {
	return adhar;
}


public void setAdhar(String adhar) {
	this.adhar = adhar;
}


public String getPan() {
	return pan;
}


public void setPan(String pan) {
	this.pan = pan;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getAc_no() {
	return ac_no;
}


public void setAc_no(String ac_no) {
	this.ac_no = ac_no;
}






//Cid (primary key), cname, aadhar, pan, phone, email, ac_no (foreign key)
}

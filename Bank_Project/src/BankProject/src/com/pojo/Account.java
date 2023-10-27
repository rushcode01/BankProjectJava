package com.pojo;

public class Account {

	private int accountno;
	private int custid;
	private String accounttype;
	private double balance;
	private String accstatus;

	public Account() {
		super();
	}

	public Account(int accountno, int custid, String accounttype, double balance, String accstatus) {
		super();
		this.accountno = accountno;
		this.custid = custid;
		this.accounttype = accounttype;
		this.balance = balance;
		this.accstatus = accstatus;
	}

	public Account(int accountno, String accounttype, double balance, String accstatus) {
		super();
		this.accountno = accountno;
		this.accounttype = accounttype;
		this.balance = balance;
		this.accstatus = accstatus;
	}

	public Account(int custid, String accounttype, double balance) {
		super();
		this.custid = custid;
		this.accounttype = accounttype;
		this.balance = balance;
	}

	public int getAccountno() {
		return accountno;
	}

	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccstatus() {
		return accstatus;
	}

	public void setAccstatus(String accstatus) {
		this.accstatus = accstatus;
	}

	@Override
	public String toString() {
		return "\nAccount No\t : " + accountno  + "\nAccount Type\t : " + accounttype + "\nBalance\t\t : "
				+ balance + "\nAccount Status\t : " + accstatus + "\n-------------------------------" ;
	}

}

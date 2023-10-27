package com.pojo;

public class Payee {

	private int useraccono;
	private int payeeaccono;
	private String payeename;
	private User user;

	public Payee(int useraccono, int payeeaccono) {
		super();
		this.useraccono = useraccono;
		this.payeeaccono = payeeaccono;
	}

	public Payee() {
		// TODO Auto-generated constructor stub
	}

	public int getUseraccono() {
		return useraccono;
	}

	public void setUseraccono(int useraccono) {
		this.useraccono = useraccono;
	}

	public int getPayeeaccono() {
		return payeeaccono;
	}

	public void setPayeeaccono(int payeeaccono) {
		this.payeeaccono = payeeaccono;
	}

	@Override
	public String toString() {
		return "Payee Accono : " + payeeaccono + " Payee name : " + payeename;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

}

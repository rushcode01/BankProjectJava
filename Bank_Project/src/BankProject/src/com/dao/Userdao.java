package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.pojo.Payee;
import com.pojo.User;

public interface Userdao {

	User showAccountDetails(int userid) throws SQLException;

	boolean depositAmount(int userid, double amount) throws SQLException;

	boolean withdrawAmount(int userid, double amount) throws SQLException;

	boolean transferAmount(int userid, int tuserid, String ttype, double amount, String tdate) throws SQLException;

	void showBalance(int userid) throws SQLException;

	boolean addPayee(int uaccono, int paccono) throws SQLException;

	boolean removePayee(int uaccono, int paccono) throws SQLException;

	User searchUser(String useremail, String userpass) throws SQLException;

	public boolean transactionEntry(int accountfrom, int accoto, String ttype, double amt, String tdate)
			throws SQLException;
	
	List<Payee> showPayee() throws SQLException;

}

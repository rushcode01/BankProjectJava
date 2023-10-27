package com.dao;

import java.sql.SQLException;

public interface AccountDao {
	
	public boolean addAccount(String accno, int custid) throws SQLException;
	public boolean deposit(String accno, int amt) throws SQLException;
	public boolean withdraw(String accno, int amt) throws SQLException;
	public double showBalance(String accno) throws SQLException;
}

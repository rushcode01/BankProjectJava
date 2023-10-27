package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Account;
import com.pojo.Payee;
import com.pojo.User;
import com.utility.DbConnection;

public class UserDaoImp implements Userdao {

	Connection conn = DbConnection.getConnect();
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	int result;
	String sql;
	User user;
	Account account;
	List<Payee> plist = new ArrayList<Payee>();

	@Override
	public User showAccountDetails(int userId) throws SQLException {
		sql = "Select * from userinfo where userid = userId ";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		return user;

	}

	public boolean depositAmount(int userid, double amount) throws SQLException {

		sql = "update accountinfo set balance = balance + ? where userid=?";
		ps = conn.prepareStatement(sql);

		ps.setDouble(1, amount);
		ps.setInt(2, userid);
		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;

	}

	public boolean withdrawAmount(int userid, double amount) throws SQLException {
		sql = "update accountinfo set balance = balance - ? where userid=?";
		ps = conn.prepareStatement(sql);

		ps.setDouble(1, amount);
		ps.setInt(2, userid);
		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;

	}

	@Override
	public boolean transferAmount(int accnofrom, int accnoto, String ttype, double amount, String tdate)
			throws SQLException {

		boolean success = true;
		sql = "update accountinfo set balance = balance + ? where accono=?";

		ps = conn.prepareStatement(sql);
		conn.setAutoCommit(false);
		ps.setDouble(1, -amount);
		ps.setInt(2, accnofrom);
		ps.addBatch();

		ps.setDouble(1, amount);
		ps.setInt(2, accnoto);
		ps.addBatch();

		int a[] = ps.executeBatch();

		for (int n : a) {
			if (n == 0) {
				success = false;
			}
		}
		conn.commit();
		conn.setAutoCommit(true);

		if (success) {
			transactionEntry(accnofrom, accnoto, ttype, amount, tdate);
		}
		return success;

	}

	@Override
	public void showBalance(int userid) throws SQLException {
		sql = "Select balance from accountinfo where userid = userid";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			System.out.println("Your Acoount Balance : " + rs.getDouble(1));
		}
	}

	public boolean addPayee(int uaccno, int paccno) throws SQLException {

		sql = "insert into payeeinfo values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uaccno);
		ps.setInt(2, paccno);

		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	public boolean removePayee(int uaccno, int paccno) throws SQLException {

		sql = "delete from payeeinfo where useraccono =? and payeeaccono=? ";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uaccno);
		ps.setInt(2, paccno);

		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	public User searchUser(String useremail, String userpass) throws SQLException {

		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid where u.useremail=? and u.userpass = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, useremail);
		ps.setString(2, userpass);

		rs = ps.executeQuery();

		if (rs.next()) {
			if (rs.getString(3).equals(useremail) && rs.getString(9).equals(userpass)
					&& rs.getString(14).equals("Active")) {
				user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setUseremail(rs.getString(3));
				user.setUsercontact(rs.getString(4));
				user.setUserdob(rs.getString(5));
				user.setUserpan(rs.getString(6));
				user.setUseraadhar(rs.getString(7));
				user.setUseraddress(rs.getString(8));
				user.setUserpass(rs.getString(9));
				user.setUserrole(rs.getString(10));
				account = new Account(rs.getInt(11), rs.getInt(1), rs.getString(12), rs.getDouble(13),
						rs.getString(14));

				user.setAccount(account);
				return user;

			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean transactionEntry(int accnofrom, int accnoto, String ttype, double tamt, String tdate)
			throws SQLException {

		sql = "insert into transactioninfo(accnofrom,accnoto,ttype,tamt,tdate) values (?,?,?,?,?)";

		ps = conn.prepareStatement(sql);
		ps.setInt(1, accnofrom);
		ps.setInt(2, accnoto);
		ps.setString(3, ttype);
		ps.setDouble(4, tamt);
		ps.setString(5, tdate);

		result = ps.executeUpdate();

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Payee> showPayee() throws SQLException {
		sql = "select p.payeeaccono ,u.username from payeeinfo p  join accountinfo a join userinfo u  on a.userid = u.userid and  p.payeeaccono = a.accono ";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			Payee payee = new Payee();

			payee.setPayeeaccono(rs.getInt(1));
			payee.setPayeename(rs.getString(2));

			plist.add(payee);
		}

		return plist;

	}
}

package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Account;
import com.pojo.User;
import com.utility.DbConnection;

public class AdminDaoImp implements Admindao {

	Connection conn = DbConnection.getConnect();
	PreparedStatement ps;
	ResultSet rs;
	int result;
	String sql;
	List<User> ulist = new ArrayList<User>();
	User user;
	Account account;

	@Override
	public boolean addUser(User user) throws SQLException {
		sql = "insert into userinfo(username,useremail,usercontact,userdob,userpan,useraadhar,"
				+ "useraddress,userpass)values(?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getUseremail());
		ps.setString(3, user.getUsercontact());
		ps.setString(4, user.getUserdob());
		ps.setString(5, user.getUserpan());
		ps.setString(6, user.getUseraadhar());
		ps.setString(7, user.getUseraddress());
		ps.setString(8, user.getUserpass());

		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		sql = "update userinfo set username=?,useremail=?,usercontact=?,userdob=?,userpan=?,useraadhar=?,useraddress=?,userpass=? where userid=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getUseremail());
		ps.setString(3, user.getUsercontact());
		ps.setString(4, user.getUserdob());
		ps.setString(5, user.getUserpan());
		ps.setString(6, user.getUseraadhar());
		ps.setString(7, user.getUseraddress());
		ps.setString(8, user.getUserpass());
		ps.setInt(9, user.getUserid());
		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;

	}

	public boolean activateUser(int userid) throws SQLException {
		sql = "Update accountinfo set status = 'Active'  where userid = ?";
		ps = conn.prepareStatement(sql);

		ps.setInt(1, userid);
		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;

	}

	@Override
	public boolean deleteUser(int userid) throws SQLException {

		sql = "Update accountinfo set status = 'Inactive'  where userid = ?";
		ps = conn.prepareStatement(sql);

		ps.setInt(1, userid);
		System.out.println("Query:" + ps);
		result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public List<User> showUserList() throws SQLException {
		ulist.clear();
		sql = "select * from userinfo where userrole = 'user'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
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

			ulist.add(user);
		}

		return ulist;
	}

	@Override
	public List<User> showUserWithAccount() throws SQLException {
		ulist.clear();
		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
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
			account = new Account(rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getString(14));
			// account=new Account();
			// account.setAccount_no(rs.getInt(11));
			user.setAccount(account);
			ulist.add(user);
		}
		return ulist;
	}

	@Override
	public User searchUserByUserid(int userid) throws SQLException {
		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid where u.userid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		System.out.println("query:" + ps);
		rs = ps.executeQuery();

		while (rs.next()) {
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
			account = new Account(rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getString(14));
			// account=new Account();
			// account.setAccount_no(rs.getInt(11));
			user.setAccount(account);

		}
		return user;

	}

	@Override
	public User searchUserByEmail(String email) throws SQLException {
		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid where u.useremail=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		System.out.println("query:" + ps);
		rs = ps.executeQuery();

		while (rs.next()) {
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
			account = new Account(rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getString(14));
			// account=new Account();
			// account.setAccount_no(rs.getInt(11));
			user.setAccount(account);
		}
		return user;
	}

	@Override
	public User searchUserByAccountno(int accono) throws SQLException {
		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid where accono=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, accono);
		System.out.println("query:" + ps);
		rs = ps.executeQuery();

		while (rs.next()) {
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
			account = new Account(rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getString(14));
			// account=new Account();
			// account.setAccount_no(rs.getInt(11));
			user.setAccount(account);
		}
		return user;
	}

	@Override
	public User searchUserByAadhar(String aadhar) throws SQLException {
		sql = "select u.userid,username,useremail,usercontact,userdob,userpan,useraadhar,useraddress,userpass,userrole,accono,acctype,balance,status"
				+ " from userinfo u join accountinfo a on u.userid=a.userid where useraadhar=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, aadhar);
		System.out.println("query:" + ps);
		rs = ps.executeQuery();

		while (rs.next()) {
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
			account = new Account(rs.getInt(11), rs.getString(12), rs.getDouble(13), rs.getString(14));
			// account=new Account();
			// account.setAccount_no(rs.getInt(11));
			user.setAccount(account);
		}
		return user;
	}

	public User searchAdmin(String useremail, String userpass) throws SQLException {

		sql = "select * from userinfo where useremail = ? and userpass = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, useremail);
		ps.setString(2, userpass);

		rs = ps.executeQuery();

		if (rs.next()) {
			if (rs.getString(3).equals(useremail) && rs.getString(9).equals(userpass)) {
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
	public boolean isAdmin(String useremail, String userpass) throws SQLException {

		sql = "select userrole from userinfo where useremail = ? and userpass = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, useremail);
		ps.setString(2, userpass);

		rs = ps.executeQuery();
		if (rs.next()) {
			if (rs.getString(1).equals("Admin")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}

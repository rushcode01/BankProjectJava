package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.pojo.User;

public interface Admindao {

	boolean addUser(User user) throws SQLException;
	boolean updateUser(User user) throws SQLException;
	boolean deleteUser(int userid) throws SQLException;
	boolean activateUser(int userid) throws SQLException;
	List<User> showUserList() throws SQLException;
	List<User> showUserWithAccount() throws SQLException;
	User searchUserByUserid(int userid) throws SQLException;
	User searchUserByEmail(String email) throws SQLException;
	User searchUserByAccountno(int aaco) throws SQLException;
	User searchUserByAadhar(String aadhar) throws SQLException;
	User searchAdmin(String useremail,String userpass) throws SQLException;
	boolean isAdmin(String useremail,String userpass) throws SQLException;
	
	

}

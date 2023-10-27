package com.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dao.AdminDaoImp;
import com.pojo.User;

public class AdminTest {
	AdminDaoImp admindao = new AdminDaoImp();

	public void adminLogin(String email, String pass) throws SQLException {
		User user = admindao.searchAdmin(email, pass);

		if (user != null) {
			AdminTest.main(user);
		} else {
			System.out.println("Admin Not found");
		}
	}

	public static void main(User user2) {
		// TODO Auto-generated method stub
		int userid;
		String username;
		String useremail;
		String usercontact;
		String userdob;
		String userpan;
		String useraadhar;
		String useraddress;
		String userpass;
		String userrole;
		int accountno;
		User user;
		List<User> ulist;
		AdminDaoImp userdao = new AdminDaoImp();
		boolean result;
		int choice;
		char ch;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("-----Welcome to Coder Bank-----");
			System.out.println("---------Admin Log-in----------");
			System.out.println("1.add user");
			System.out.println("2.update user");
			System.out.println("3.Change user Status");
			System.out.println("4.delete user");
			System.out.println("5.show user list");
			System.out.println("6.show user list with Account details");
			System.out.println("7.search user by id");
			System.out.println("8.search user by email");
			System.out.println("9.search user by account");
			System.out.println("10.search user by aadhar");
			System.out.println("11.Log-out");

			System.out.println("---------------------------------");

			System.out.println("Enter your choice:");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("----------Add User-------");
				System.out.println("Enter user name:");
				username = sc.next();
				System.out.println("Enter user email:");
				useremail = sc.next();
				System.out.println("Enter user contact:");
				usercontact = sc.next();
				System.out.println("Enter user dob:");
				userdob = sc.next();
				System.out.println("Enter user pan:");
				userpan = sc.next();
				System.out.println("Enter user aadhar:");
				useraadhar = sc.next();
				System.out.println("Enter user address:");
				useraddress = sc.next();
				System.out.println("Enter user password:");
				userpass = sc.next();
				user = new User(username, useremail, usercontact, userdob, userpan, useraadhar, useraddress, userpass);
				try {
					result = userdao.addUser(user);
					if (result)
						System.out.println("user added successfully");
					else
						System.out.println("user not added");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("--------Update User------");
				System.out.println("Enter user id:");
				userid = sc.nextInt();
				System.out.println("Enter user name:");
				username = sc.next();
				System.out.println("Enter user email:");
				useremail = sc.next();
				System.out.println("Enter user contact:");
				usercontact = sc.next();
				System.out.println("Enter user dob:");
				userdob = sc.next();
				System.out.println("Enter user pan:");
				userpan = sc.next();
				System.out.println("Enter user aadhar:");
				useraadhar = sc.next();
				System.out.println("Enter user address:");
				useraddress = sc.next();
				System.out.println("Enter user password:");
				userpass = sc.next();
				user = new User(userid, username, useremail, usercontact, userdob, userpan, useraadhar, useraddress,
						userpass);
				try {
					result = userdao.updateUser(user);
					if (result)
						System.out.println("user details updated successfully");
					else
						System.out.println("user not updated");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("---------Activate User-----------");
				try {
					System.out.println("Enter user id:");
					userid = sc.nextInt();
					result = userdao.activateUser(userid);
					if (result) {
						System.out.println("Status Change Successfully");
					} else {
						System.out.println("usere not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("---------User Delete-----------");
				try {
					System.out.println("Enter User id:");
					userid = sc.nextInt();
					result = userdao.deleteUser(userid);
					if (result) {
						System.out.println("User Deleted Successfully");
					} else {
						System.out.println("User Not Found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("---------user list-----------");
				try {
					ulist = userdao.showUserList();
					if (!ulist.isEmpty()) {
						for (User u : ulist) {
							System.out.println(u);
						}
					} else {
						System.out.println("userlist is empty");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 6:
				System.out.println("---------user list with account details-----------");
				try {
					ulist = userdao.showUserWithAccount();
					if (!ulist.isEmpty()) {
						for (User u : ulist) {
							System.out.println(u);
						}
					} else {
						System.out.println("userlist is empty");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 7:
				System.out.println("search user and account by id:");
				userid = sc.nextInt();
				try {
					user = userdao.searchUserByUserid(userid);
					if (user != null) {
						System.out.println(user);
					} else {
						System.out.println("user not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:
				System.out.println("search user and account by email:");
				useremail = sc.next();
				try {
					user = userdao.searchUserByEmail(useremail);
					if (user != null) {
						System.out.println(user);
					} else {
						System.out.println("user not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:
				System.out.println("search user and account by Account NO:");
				accountno = sc.nextInt();
				try {
					user = userdao.searchUserByAccountno(accountno);
					if (user != null) {
						System.out.println(user);
					} else {
						System.out.println("user not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 10:
				System.out.println("search user and account by Aadhar:");
				useraadhar = sc.next();
				try {
					user = userdao.searchUserByAadhar(useraadhar);
					if (user != null) {
						System.out.println(user);
					} else {
						System.out.println("user not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 11:
				System.out.println("Loggin out");
				BankTest.main(null);
				System.exit(0);
			}

			System.out.println("do you want to continue if yes enter y:");
			ch = sc.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
	}

}

package com.test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.dao.UserDaoImp;
import com.pojo.Account;
import com.pojo.Payee;
import com.pojo.User;

public class UserTest {

	static UserDaoImp userdao = new UserDaoImp();

	public void userLogin(String useremail, String userpass) throws SQLException {

		User user = userdao.searchUser(useremail, userpass);

		if (user != null) {
			UserTest.main(user);

		} else {
			System.out.println("User Inactive ");
		}
	}

	static int uid;
	private static List<Payee> plist;

	public void userid(int id) {
		uid = id;
	}

	public static void main(User user) {

		int userid = user.getUserid();
		Account account = user.getAccount();

		int accno = account.getAccountno();
		int choice;
		char ch = 0;
		boolean result;
		Double amount;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("-----Welcome to Coder Bank-----");
			System.out.println("----------User Log-in----------");
			System.out.println("1.Show Account Details");
			System.out.println("2.Deposit Amount");
			System.out.println("3.Withdraw Amount");
			System.out.println("4.Transfer Amount");
			System.out.println("5.Show Balance");
			System.out.println("6.Payee");
			System.out.println("7.Log-out");
			System.out.println("-------------------------------");

			System.out.println("Enter your choice:");
			choice = sc.nextInt();

			int paccono;
			switch (choice) {
			case 1:
				System.out.println("---User Account Details---");
				try {
					User detail = userdao.showAccountDetails(userid);
					System.out.println(detail);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 2:
				System.out.println("---Deposit Amount---");
				System.out.println("Enter Deposit Amount: ");
				amount = sc.nextDouble();
				try {
					result = userdao.depositAmount(userid, amount);
					if (result) {
						System.out.println("Deposit Successfull....");
					} else {
						System.out.println("Deposit faild....");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 3:
				System.out.println("---Withdraw Amount---");
				System.out.println("Enter Deposit Amount: ");
				amount = sc.nextDouble();
				try {
					result = userdao.withdrawAmount(userid, amount);
					if (result) {
						System.out.println("Withdraw Successfull....");
					} else {
						System.out.println("Withdraw faild....");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 4:
				System.out.println("---Transfer Amount---");

				System.out.println("Enter Account No :");
				int payeeacc = sc.nextInt();
				System.out.println("Enter Amount:");
				amount = sc.nextDouble();
				System.out.println("Enter Transaction Type : ");
				String ttype = sc.next();

				LocalDateTime dt = LocalDateTime.now();
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String curdate = dt.format(df);

				try {
					boolean success = userdao.transferAmount(accno, payeeacc, ttype, amount, curdate);
					if (success) {
						System.out.println("Amount" + amount + " Successfully Transfer to Account No: " + payeeacc);
					} else {
						System.out.println("Transfer faild....");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 5:
				System.out.println("---Account Balance---");
				try {
					userdao.showBalance(userid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 6:
				System.out.println("----Payee----");
				System.out.println("1.Add Payee");
				System.out.println("2.Remove Payee");
				System.out.println("3.Show Payee");
				System.out.println("4.Back");
				System.out.println("-------------------------------");
				System.out.println("Enter your choice:");
				choice = sc.nextInt();

				switch (choice) {

				case 1:
					System.out.println("---Add Payee---");
					System.out.println("Enter Account NO:");
					paccono = sc.nextInt();
					try {
						boolean added = userdao.addPayee(accno, paccono);
						if (added) {
							System.out.println("Payee Added successfully");
						} else {
							System.out.println("Payee Add Faild");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.println("---Remove Payee---");
					System.out.println("Enter Account NO:");
					paccono = sc.nextInt();
					try {
						boolean added = userdao.removePayee(accno, paccono);
						if (added) {
							System.out.println("Payee Remove successfully");
						} else {
							System.out.println("Payee remove Faild");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("-----Show Payee-----");
					try {
						plist = userdao.showPayee();
						if (!plist.isEmpty()) {
							for (Payee u : plist) {
								System.out.println(u);
							}
						} else {
							System.out.println("Payeelist is empty");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
				case 4:
					UserTest.main(user);
					break;
				}

				break;
			case 7:
				System.out.println("---Loggin out---");
				BankTest.main(null);
				System.exit(0);
				break;
			}

			System.out.println("do you want to continue if yes enter y:");
			ch = sc.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
	}
}

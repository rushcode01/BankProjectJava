package com.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.dao.AdminDaoImp;
import com.dao.UserDaoImp;
import com.pojo.User;

public class BankTest {

	public static void main(String[] args) {

		int choice;
		char ch;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("-----Welcome to Coder Bank-----");
			System.out.println("1.Log-in");
			System.out.println("2.Exit");
			System.out.println("-------------------------------");

			System.out.println("Enter your choice:");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Login-id : ");
				String username = sc.next();
				System.out.println("Enter Password :");
				String pass = sc.next();

				AdminTest admin = new AdminTest();
				UserTest user = new UserTest();
				AdminDaoImp admo = new AdminDaoImp();

				try {
					if (admo.isAdmin(username, pass)) {

						admin.adminLogin(username, pass);
					} else {
						user.userLogin(username, pass);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("---Thank You for Banking with Coder Bank---");
				System.out.println("---Visit Again :-)---");

				System.exit(0);

				break;

			}
			System.out.println("do you want to continue if yes enter y:");
			ch = sc.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
	}
}

package com.capgemini.banking.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;
import com.capgemini.banking.service.AccountServiceImpl;

public class Driver {
	public static void main(String[] args) {
		AccountServiceImpl asi = new AccountServiceImpl();
		int ch = 9;

		Scanner sc = new Scanner(System.in);
		while (ch != 0) {
			System.out.println("Welcome to Payement Wallet");
			System.out.println("=======Choose from the Below Menu=======");
			System.out.println("1. Create a new Bank Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Show Balance");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter Your Name");
				String name = sc.next();
				System.out.println(" and Openeing Balance");
				double bal = sc.nextDouble();
				asi.create(new Account(name, bal));
				break;
			case 2:
				System.out.println("Enter the account number and Amount :");
				int accno = sc.nextInt();
				double amt = sc.nextDouble();
				try {
					if (asi.deposit(accno, amt)) {
						System.out.println("Successful");
					}
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter the account number and Amount :");
				accno = sc.nextInt();
				amt = sc.nextDouble();
				try {
					if (asi.withdraw(accno, amt)) {
						System.out.println("Successfull");
					}
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter the account number");
				accno = sc.nextInt();
				try {
					System.out.println(asi.getBalance(accno));
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter your Account Number and Account Number to be credited and Amount :");
				int accFrom = sc.nextInt();
				int accTo = sc.nextInt();
				amt = sc.nextDouble();
				try {
					if (asi.fundTransfer(accFrom, accTo, amt)) {
						System.out.println("Successful");
					}
					break;

				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());

				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
			case 6:
				System.out.println("Enter the Account number");
				accno = sc.nextInt();
				try {
					ArrayList<String> ar = asi.showTransaction(accno);
					for (String s : ar) {
						System.out.println(s);
					}

				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Please enter Valid Choice");
				break;
			}
		}
	}
}

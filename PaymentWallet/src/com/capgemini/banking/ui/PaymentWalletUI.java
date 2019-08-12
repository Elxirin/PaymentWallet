package com.capgemini.banking.ui;

import java.io.Serializable;
import java.util.Scanner;

import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;
import com.capgemini.banking.implementation.AccountImplementationList;

public class PaymentWalletUI implements Serializable {

	
	
	public static void main(String[] args) {
		AccountImplementationList accImp = new AccountImplementationList();
		int ch = 9;
		Scanner sc = new Scanner(System.in);
		while (ch != 0) {
			System.out.println("Welcome to Payement Wallet");
			System.out.println("=======Choose from the Below Menu=======");
			System.out.println("1. Login to Wallet");
			System.out.println("2. Create a new Bank Account");
			System.out.println("0. EXIT");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter Account No and Pin : ");
				String s=null;
				try{
					s = accImp.login(sc.nextInt(), sc.nextInt());
				}
				catch(AccountNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				if (s.equals("Successfull")) {
					System.out.println("\n"+s+"\n\n");
					while(ch!=0) {
						System.out.println("=======Choose from the Below Menu=======");
						System.out.println("1. Deposit");
						System.out.println("2. Withdraw");
						System.out.println("3. Show Balance");
						System.out.println("4. Fund Transfer");
						System.out.println("5. Print Transactions");
						System.out.println("6. Change Pin");
						System.out.println("9. Sign Out");
						ch = sc.nextInt();
						switch(ch) {
						case 1:
							System.out.println(accImp.deposit());
							break;
						case 2:
							try{
							accImp.withdraw();
							}
							catch(InsufficientBalanceException e){
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							System.out.println(accImp.showBalance());
							break;
						case 4:
							
							try {
								System.out.println(accImp.fundTransfer());
							} catch (InsufficientBalanceException e) {
								System.out.println(e.getMessage());
							}
							catch (AccountNotFoundException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 5: 
							accImp.printTransactions();
							break;
						case 6:
							System.out.println(accImp.changePin());
							break;
						case 9:
							accImp.saveMap();
		
							break;
						default:
								System.out.println("Please enter Valid Choice");
								break;
						}
						if(ch==9) {
							break;
						}
					}
				} else {
					System.out.println(s);
				}
				break;
			case 2:
				if(accImp.createAccount()){
					System.out.println("Succesfully Created");
				}
				break;
			case 0:
				accImp.saveMap();
				System.exit(0);
			default :
				System.out.println("Please Choose from the Menu\n\n");
			case 7:
				accImp.showList();
			
			}
		}
	}
}

package capgemini.project.WalletImplementation;

import java.util.Scanner;

import capgemini.project.account_implementation.AccountImplementationList;

public class DriverProgram {

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
				String s = accImp.login(sc.nextInt(), sc.nextInt());
				if (s.equals("Successfull")) {
					System.out.println("\n"+s+"\n\n");
					while(ch!=0) {
						System.out.println("=======Choose from the Below Menu=======");
						System.out.println("1. Deposit");
						System.out.println("2. Withdraw");
						System.out.println("3. Show Balance");
						System.out.println("0. EXIT");
						ch = sc.nextInt();
						switch(ch) {
						case 1:
							System.out.println(accImp.deposit());
							break;
						case 2:
							accImp.withdraw();
							break;
						case 3:
							System.out.println(accImp.showBalance());
							break;
						case 0:
							System.exit(0);
							break;
						}
					}
				} else {
					System.out.println(s);
				}
				break;
			case 2:
				accImp.createAccount();
				break;
			case 0:
				System.exit(0);
			default :
				System.out.println("Please Choose from the Menu\n\n");

			
			}
		}
	}
}

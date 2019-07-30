package capgemini.project.account_implementation;

import java.util.Scanner;

import capgemini.project.account.Account;
import capgemini.project.banking.accountOperation;
import capgemini.project.banking.advancedAccountOperation;

public class AccountImplementation extends Account implements accountOperation, advancedAccountOperation {
	Scanner sc = new Scanner(System.in);
	public AccountImplementation() {
		this.name = sc.next();
		this.accType = sc.next();
		this.svBalance = sc.nextDouble();
		accGen++;
		accountNo = accGen;
		pin = Math.round(Math.random()*10000);
		System.out.println("Your Account No is : " + accountNo +"\n and Temporary Pin is :" + pin );
	}
	
	public AccountImplementation(String name,String accType, Double openingBal) {
		this.name = name;
		this.accType = accType;
		this.svBalance = openingBal;
		accGen++;
		accountNo = accGen;
		pin = Math.round(Math.random()*10000);
		System.out.println("Your Account No is : " + accountNo +"\n and Temporary Pin is :" + pin );
	}
	
	@Override
	public String fundTransfer(int accountNo, double amount) {
		
		return null;
	}

	@Override
	public String printTransactions(int accountNo) {
		
		return null;
	}

	@Override
	public String createAccount() {
		
		return null;
	}

	@Override
	public String login(int accountNo, int pin) {
	
		return null;
	}

	@Override
	public double showBalance(int accountNo) {
		
		return 0;
	}

	@Override
	public String deposit(int accountNo, String type) {
		
		return null;
	}

	@Override
	public String withdraw(int accountNo, String type) {
		
		return null;
	}

}

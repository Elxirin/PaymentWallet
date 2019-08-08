package com.capgemini.banking.operation;



import com.capgemini.banking.bean.Account;

public interface accountOperation {
	public boolean createAccount();
	
	public String login(int accountNo, long pin);
	
	public double showBalance();
	
	public  String deposit();
	
	public  String withdraw();
	
}

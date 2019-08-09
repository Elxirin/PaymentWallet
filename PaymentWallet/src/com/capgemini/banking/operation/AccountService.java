package com.capgemini.banking.operation;



import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public interface AccountService {
	public boolean createAccount();
	
	public String login(int accountNo, long pin) throws AccountNotFoundException;
	
	public double showBalance();
	
	public  String deposit();
	
	public  String withdraw() throws InsufficientBalanceException;
	
}

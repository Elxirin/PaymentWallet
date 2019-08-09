package com.capgemini.banking.operation;

import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public interface AdvancedAccountServices {

	public String fundTransfer() throws AccountNotFoundException, InsufficientBalanceException;
	
	public String printTransactions();
	
	public String changePin();
}

package com.capgemini.banking.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.dao.AccountDAO;
import com.capgemini.banking.dao.AccountDAOImpl;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public class AccountServiceImpl implements AccountService {

	AccountDAO accountDAO;
	
	public AccountServiceImpl() {
		super();
		 accountDAO = new AccountDAOImpl();
	}

	@Override
	public int create(Account account) {
		return accountDAO.create(account);
	}

	@Override
	public boolean deposit(int accNo, double amount) throws AccountNotFoundException {
		if(accountDAO.read(accNo)==-1) {
			throw new AccountNotFoundException("The Specified FROM Account Could Not be Found!");
		}
		else{
			if(accountDAO.update(accNo, amount,"CR"))
				return true;
			else
				return false;
		}
		
	}

	@Override
	public boolean withdraw(int accNo, double amount) throws InsufficientBalanceException, AccountNotFoundException{
		if(accountDAO.read(accNo)==-1) {
			throw new AccountNotFoundException("The Specified FROM Account Could Not be Found!");
		}
		else{
			if(accountDAO.read(accNo)<amount) {
				throw new InsufficientBalanceException("Not Sufficeint Balance for Transfer!");
			}
			else{
				if(accountDAO.update(accNo,amount,"DB"))
					return true;
				else
					return false;
			}
		}
	}

	@Override
	public double getBalance(int accNo) throws AccountNotFoundException {
		if(accountDAO.read(accNo)==-1) {
			throw new AccountNotFoundException("The Specified FROM Account Could Not be Found!");
		}else{
			return accountDAO.read(accNo);
		}
	}

	@Override
	public boolean fundTransfer(int accFrom, int accTo, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		if(accountDAO.read(accFrom)==-1){
			throw new AccountNotFoundException("The Specified FROM Account Could Not be Found!");
		}
		if(accountDAO.read(accTo)==-1){
			throw new AccountNotFoundException("The Specified TO Account Could Not be Found!");
		}
		if(accountDAO.read(accFrom)<amount){
			throw new InsufficientBalanceException("Not Sufficeint Balance for Transfer!");
		}else{
			if(accountDAO.update(accFrom, amount, "DB") && accountDAO.update(accTo, amount, "CR")){
				return true;
			}
			else{
				return false;
			}
			
		}
	}

	@Override
	public ResultSet showTransaction(int accNo)throws AccountNotFoundException {
		if(accountDAO.read(accNo)==-1) {
			throw new AccountNotFoundException("The Specified TO Account Could Not be Found!");
		}
		else{
			return (accountDAO.readAll(accNo));
		}
		
	}
	
	

	
}

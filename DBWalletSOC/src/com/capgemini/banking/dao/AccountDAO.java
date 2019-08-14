package com.capgemini.banking.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public interface AccountDAO {
	
	public int create(Account account);
	public double read(int accNo);
	public ResultSet readAll(int accNo);
	public boolean update(int accNO,double amount,String type);
	public boolean delete(int accNo);
	public boolean deleteAll(int accNo);
	
}

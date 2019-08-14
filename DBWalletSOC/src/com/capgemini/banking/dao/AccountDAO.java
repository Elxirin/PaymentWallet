package com.capgemini.banking.dao;

import java.util.ArrayList;

import com.capgemini.banking.bean.Account;


public interface AccountDAO {

	public int create(Account account);

	public double read(int accNo);

	public ArrayList<String> readAll(int accNo);
	

	public boolean update(int accNO, double amount, String type);

	public boolean delete(int accNo);

	public boolean deleteAll(int accNo);

}

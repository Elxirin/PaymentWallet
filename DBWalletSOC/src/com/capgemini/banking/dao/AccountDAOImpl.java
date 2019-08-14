package com.capgemini.banking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.dbutility.DBUtility;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public class AccountDAOImpl implements AccountDAO {
	
	Connection connection;
	DBUtility db;
	public AccountDAOImpl() {
			db = new DBUtility();
			connection = db.getConnection();
	}

	private Connection getConnection() {
		return connection;
	}

	@Override
	public int create(Account account) {
		String str = "INSERT INTO account(accountId,accountName,openingBal,currentBal) VALUES(ACCNO.nextval,?,?,?)";
		String str1 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
		String[] str2 = { "transactionId", "accountId", "tran_date", "description", "tran_type", "amount" };
		int acnum = 0;
		try(PreparedStatement stmt = getConnection().prepareStatement(str, new String[] { "accountid" })) {
			stmt.setString(1, account.getName());
			stmt.setDouble(2, account.getOpeningBal());
			stmt.setDouble(3, account.getOpeningBal());
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			while (set.next())
				acnum = set.getInt(1);
			System.out.println(acnum);
			account.setAccountNo(acnum);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet set1 = db.exTQuery(str1, "ACCOUNT CREATED", "CREATED", str2, account);
		return acnum;
	}

	@Override
	public double read(int accNo) {
		;
		double amt = -1;
		// int id =0;
		String str1 = "SELECT currentbal FROM account WHERE accountId=?";
		try(PreparedStatement stmt = getConnection().prepareStatement(str1)) {
			stmt.setInt(1, accNo);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				amt = set.getDouble(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return amt;
	}

	@Override
	public ResultSet readAll(int accNo) {
		if(read(accNo)!=-1){
			String str1 = "SELECT * FROM transaction WHERE accountId=?";
			ResultSet rs = db.exTQuery(str1, accNo);
			return rs;
		}else
		return null;
	}

	@Override
	public boolean update(int accNO, double amount, String type) {
		String str1, str2;
		if (type.equals("CR")) {
			str1 = "UPDATE account SET currentBal = currentBal+? WHERE accountId = ?";
			ResultSet rs = db.exAQuery(str1, accNO, amount);

			str2 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
			ResultSet rs2 = db.exTQuery(str2, "ACCOUNT CREDITED", "CR", accNO, amount);
			return true;
		}
		else
		if (type.equals("DB")) {
			str1 = "UPDATE account SET currentBal = currentBal-? WHERE accountId = ?";
			ResultSet rs = db.exAQuery(str1, accNO, amount);

			str2 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
			ResultSet rs2 = db.exTQuery(str2, "ACCOUNT DEBITED", "DB", accNO, amount);
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean delete(int accNo) {
		return false;
	}

	@Override
	public boolean deleteAll(int accNo) {
		return false;
	}

}

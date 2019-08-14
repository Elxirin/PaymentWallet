package com.capgemini.banking.dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.capgemini.banking.bean.Account;

public class DBUtility {
	Connection connection;
	public  Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "INVENTORY1", "INVENTORY1");
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	public Connection returnConnection() {
		return(connection);
	}
	
	
public ResultSet exQuery(String str) {
		
		try(Statement stmt = returnConnection().createStatement()) { 
			ResultSet rs = stmt.executeQuery(str);
			stmt.executeQuery("commit");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new Timestamp(today.getTime());

	}

	public ResultSet exTQuery(String str1, String str2, String str3, String[] str4, Account account) {
		try (PreparedStatement stmt=returnConnection().prepareStatement(str1, str4)) {
			stmt.setInt(1, account.getAccountNo());
			stmt.setTimestamp(2, getCurrentTimeStamp());
			stmt.setString(3, str2);
			stmt.setString(4, str3);
			stmt.setDouble(5, account.getCrBalance());
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet exTQuery(String str1, String str2, String str3, int accNo, double amount) {
		
		try(PreparedStatement stmt = returnConnection().prepareStatement(str1)) {
			stmt.setInt(1, accNo);
			stmt.setTimestamp(2, getCurrentTimeStamp());
			stmt.setString(3, str2);
			stmt.setString(4, str3);
			stmt.setDouble(5, amount);
			int i = stmt.executeUpdate();
			System.out.println(i);
			ResultSet set = stmt.getGeneratedKeys();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet exAQuery(String str1, int accNo, double amount) {
		
		try(PreparedStatement stmt = returnConnection().prepareStatement(str1)) {
			stmt.setDouble(1, amount);
			stmt.setInt(2, accNo);
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet exTQuery(String str1, int accNo) {
		PreparedStatement stmt ;
		try {
			stmt = returnConnection().prepareStatement(str1);
			stmt.setInt(1, accNo);
			ResultSet set = stmt.executeQuery();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}

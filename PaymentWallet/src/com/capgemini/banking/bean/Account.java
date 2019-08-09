package com.capgemini.banking.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Account implements Serializable {

	protected  int accGen = 1000;
	protected int accountNo;
	protected long pin;
	protected String name, accType; 
	protected double svBalance, crBalance, openingBal;
	protected List<String> trans = new ArrayList<String>();
	transient Scanner sc = new Scanner(System.in);

	public Account() {
		
	}

	public Account(String name, String accType, Double openingBal) {
		this.name = name;
		this.accType = accType;
		this.svBalance = openingBal;
		accGen++;
		accountNo = accGen;
		pin = Math.round(Math.random() * 10000);
		System.out.println("Your Account No is : " + accountNo + "\nand Temporary Pin is :" + pin);
	}

	public int getAccGen() {
		return accGen;
	}

	public void setAccGen(int accGen) {
		this.accGen = accGen;
	}

	public Account getAccount(int lastAccNo) {
		System.out.println("Enter Name on the Account\t Account Type Savings/Current \topeneing Balance :");
		this.name = sc.next();
		this.accType = sc.next();
		this.svBalance = sc.nextDouble();
		accGen = lastAccNo;
		accGen++;
		accountNo = accGen;
		pin = Math.round(Math.random() * 10000);
		System.out.println("Your Account No is : " + accountNo + "\nand Temporary Pin is :" + pin);
		return this;
	}
	
	public List<String> getTrans() {
		return trans;
	}

	private void setTrans(List<String> trans) {
		this.trans = trans;
	}
	
	public long getPin() {
		return this.pin;
	}
	
	public boolean setPin(long pin) {
		this.pin = pin;
		return true;
	}

	public void setBalance(double bal) {
		if(this.accType.equals("Savings"))
			setSvBalance(bal);
		else
			setCrBalance(bal);
	}
	public double getBalance() {
		if(this.accType.equals("Savings"))
			return getSvBalance();
		else
			return getCrBalance();
	}
	public int getAccountNo() {
		return accountNo;
	}

	private void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	

	

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getAccType() {
		return accType;
	}

	private void setAccType(String accType) {
		this.accType = accType;
	}

	public double getSvBalance() {
		return svBalance;
	}

	private void setSvBalance(double svBalance) {
		this.svBalance = svBalance;
	}

	public double getCrBalance() {
		return crBalance;
	}

	private void setCrBalance(double crBalance) {
		this.crBalance = crBalance;
	}

	public double getOpeningBal() {
		return openingBal;
	}

	private void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
	}

}

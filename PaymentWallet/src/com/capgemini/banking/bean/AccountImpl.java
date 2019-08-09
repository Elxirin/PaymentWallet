package com.capgemini.banking.bean;

public class AccountImpl extends Account {
	
	public AccountImpl() {
		super();
	}
	
	@Override
	public Account getAccount(int lastAccNo) {
		Account acc = new AccountImpl();
		System.out.println("Enter Name on the Account\t Account Type Savings/Current \topeneing Balance :");
		acc.name = sc.next();
		acc.accType = sc.next();
		acc.svBalance = sc.nextDouble();
		accGen = lastAccNo;
		accGen++;
		acc.accountNo = accGen;
		acc.pin = Math.round(Math.random() * 10000);
		System.out.println("Your Account No is : " + acc.accountNo + "\nand Temporary Pin is :" + acc.pin);
		return acc;
	}
}

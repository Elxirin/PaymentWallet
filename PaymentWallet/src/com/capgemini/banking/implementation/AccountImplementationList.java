package com.capgemini.banking.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.bean.AccountImpl;
import com.capgemini.banking.operation.accountOperation;
import com.capgemini.banking.operation.advancedAccountOperation;

public class AccountImplementationList extends AccountImpl implements accountOperation, advancedAccountOperation {
	
	private  List<Account> accountList;
	Scanner sc = new Scanner(System.in);
	private int sessionAccNo;
	public AccountImplementationList() {
		accountList = new ArrayList<Account>();
	}

	

	@Override
	public String printTransactions(int accountNo) {

		return null;
	}

	@Override
	public boolean createAccount() {
		Account account = getAccount();
		return accountList.add(account);
	}

	@Override
	public String login(int accountNo, long pin) {
		for(Account ac : accountList) {
			if(ac.getAccountNo()==accountNo) {
				if(ac.getPin()==pin) {
					sessionAccNo = ac.getAccountNo();
					return "Successfull";
				}
			}
		}
		
		return "Fail";
	}

	@Override
	public double showBalance() {
		for(Account ac : accountList) {
			if(ac.getAccountNo()== sessionAccNo ) {
				return ac.getBalance();
			}
		}
		return 0;
	}

	@Override
	public String deposit() {
		for(Account ac : accountList) {
			if(ac.getAccountNo()== sessionAccNo ) {
				System.out.println("Enter the Amount to be deposited :");
				ac.setBalance(ac.getBalance()+sc.nextDouble());
				return "Successfull";
			}
		}
		return "Fail";
	}

	@Override
	public String withdraw() {
		for(Account ac : accountList) {
			if(ac.getAccountNo()== sessionAccNo ) {
				System.out.println("Enter the Amount to be deposited :");
				ac.setBalance(ac.getBalance()-sc.nextDouble());
				return "Successfull";
			}
		}
		return null;
	}

	public void showList(){
		for(Account ac : accountList){
			System.out.println(ac.getAccountNo()+ "  "+ac.getName() +"  "+ac.getAccType()+"  "+ac.getBalance());
		}
	}
	
	@Override
	public String fundTransfer() {
		System.out.println("Enter the Account Number and Fund to be Transferred");
		int facc = sc.nextInt();
		double famt = sc.nextDouble();
		for(Account ac : accountList) {
			if(ac.getAccountNo()==sessionAccNo) {
				ac.setBalance(ac.getBalance()-famt);
			}
			if(ac.getAccountNo()==facc) {
				ac.setBalance(ac.getBalance()+famt);
			}
		}
		return "Successfull";
	}
	
}

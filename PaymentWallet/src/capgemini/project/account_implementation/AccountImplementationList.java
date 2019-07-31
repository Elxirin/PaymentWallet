package capgemini.project.account_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import capgemini.project.account.Account;
import capgemini.project.banking.accountOperation;
import capgemini.project.banking.advancedAccountOperation;

public class AccountImplementationList extends Account implements accountOperation, advancedAccountOperation {
	
	private List<Account> accountList;
	Scanner sc = new Scanner(System.in);
	private int sessionAccNo;
	public AccountImplementationList() {
		accountList = new ArrayList<Account>();
	}

	@Override
	public String fundTransfer(int accountNo, double amount) {

		return null;
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
				ac.setBalance(sc.nextDouble());
				return "Successfull";
			}
		}
		return null;
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

}

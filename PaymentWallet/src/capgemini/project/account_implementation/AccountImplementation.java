/*package capgemini.project.account_implementation;

import java.util.ArrayList;

import capgemini.project.account.Account;
import capgemini.project.banking.accountOperation;
import capgemini.project.banking.advancedAccountOperation;

public class AccountImplementation extends Account implements accountOperation, advancedAccountOperation {
	
	private int sessionAccNo;
	public AccountImplementation() {
		
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
		return true;
	}

	@Override
	public String login(int accountNo, long pin) {
		for(Account ac : accList) {
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
	public double showBalance(ArrayList<Account> accList) {
		for(Account ac : accList) {
			if(ac.getAccountNo()== sessionAccNo ) {
				return ac.getBalance();
			}
		}
		return 0;
	}

	@Override
	public String deposit(int accountNo, String type) {

		return null;
	}

	@Override
	public String withdraw(int accountNo, String type) {

		return null;
	}

}
*/
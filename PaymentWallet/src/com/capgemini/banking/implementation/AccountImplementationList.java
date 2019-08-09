package com.capgemini.banking.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.bean.AccountImpl;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;
import com.capgemini.banking.operation.AccountService;
import com.capgemini.banking.operation.AdvancedAccountServices;

public class AccountImplementationList extends AccountImpl
		implements AccountService, AdvancedAccountServices, Serializable {

	// private List<Account> accountList;

	private Map<Integer, Account> accountMap;
	transient Scanner sc = new Scanner(System.in);
	private int sessionAccNo;
	public int lastAccNo;
	File file = new File("C:\\Capgemini\\Projects\\PaymentWallet\\src\\Repo.txt");

	public AccountImplementationList() {
		// accountList = new ArrayList<Account>();
		accountMap = new HashMap<Integer, Account>();
		readMap();
	}

	public void saveMap() {
		FileOutputStream fout;
		try {

			fout = new FileOutputStream(file);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(accountMap);
			fout.flush();
			oout.close();
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readMap() {
		try (FileInputStream fin = new FileInputStream(file)) {
			ObjectInputStream oin = new ObjectInputStream(fin);
			accountMap = (HashMap<Integer, Account>) oin.readObject();
			List<Entry<Integer, Account>> entries = new ArrayList<Entry<Integer, Account>>(accountMap.entrySet());
			Entry<Integer, Account> lastEntry = entries.get(entries.size() - 1);
			lastAccNo = lastEntry.getKey();
			// System.out.println(lastAccNo);
			oin.close();
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {

		}

	}

	@Override
	public String printTransactions() {

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == sessionAccNo) {
				for (String str : ac.getTrans()) {
					System.out.println(str);
				}
			}

		}

		return null;
	}

	@Override
	public boolean createAccount() {
		Account account = getAccount(lastAccNo);
		// return accountList.add(account);
		accountMap.put(account.getAccountNo(), account);

		// Logging Transactions Here
		account.getTrans().add("Account No\tName\tType\tTransaction\tDate\tBalance\n");
		account.getTrans().add(account.getAccountNo() + " " + account.getName() + "  " + account.getAccType()
				+ "  Account Created  " + (new Date()).toString() + "  " + account.getBalance());

		return true;
	}

	@Override
	public String login(int accountNo, long pin) throws AccountNotFoundException {
		/*
		 * for(Account ac : accountList) { if(ac.getAccountNo()==accountNo) {
		 * if(ac.getPin()==pin) { sessionAccNo = ac.getAccountNo(); return
		 * "Successfull"; } } }
		 */

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == accountNo) {
				if (ac.getPin() == pin) {
					sessionAccNo = ac.getAccountNo();
					return "Successfull";
				}
			}

		}
		throw new AccountNotFoundException("No Such Account Found or Wrong Pin");
	}

	@Override
	public double showBalance() {
		/*
		 * for(Account ac : accountList) { if(ac.getAccountNo()== sessionAccNo )
		 * { return ac.getBalance(); } }
		 */

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == sessionAccNo) {
				return ac.getBalance();
			}
		}

		return 0;
	}

	@Override
	public String deposit() {
		/*
		 * for(Account ac : accountList) { if(ac.getAccountNo()== sessionAccNo )
		 * { System.out.println("Enter the Amount to be deposited :");
		 * ac.setBalance(ac.getBalance()+sc.nextDouble()); return "Successfull";
		 * } }
		 */

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == sessionAccNo) {
				System.out.println("Enter the Amount to be deposited :");
				double dp = sc.nextDouble();
				ac.setBalance(ac.getBalance() + dp);
				ac.getTrans().add(ac.getAccountNo() + " " + ac.getName() + "  " + ac.getAccType() + "  Deposit  " + dp
						+ (new Date()).toString() + "  " + ac.getBalance());
				return "Successfull";
			}
		}

		return "Fail";
	}

	@Override
	public String withdraw() throws InsufficientBalanceException {
		/*
		 * for(Account ac : accountList) { if(ac.getAccountNo()== sessionAccNo )
		 * { System.out.println("Enter the Amount to be deposited :");
		 * ac.setBalance(ac.getBalance()-sc.nextDouble()); return "Successfull";
		 * } }
		 */

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == sessionAccNo) {
				System.out.println("Enter the Amount to be Withdraw :");
				double dp = sc.nextDouble();
				if (ac.getBalance() < dp) {
					throw new InsufficientBalanceException("Not Enough Balance");
				} else {
					ac.setBalance(ac.getBalance() - dp);
					ac.getTrans().add(ac.getAccountNo() + " " + ac.getName() + "  " + ac.getAccType() + "  Withdraw  "
							+ dp + (new Date()).toString() + "  " + ac.getBalance());

					return "Successfull";
				}
			}
		}
		return null;
	}

	public void showList() {
		/*
		 * for(Account ac : accountList){ System.out.println(ac.getAccountNo()+
		 * "  "+ac.getName() +"  "+ac.getAccType()+"  "+ac.getBalance()); }
		 */

		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			System.out
					.println(ac.getAccountNo() + "  " + ac.getName() + "  " + ac.getAccType() + "  " + ac.getBalance());
		}
	}

	@Override
	public String fundTransfer() throws InsufficientBalanceException, AccountNotFoundException {
		System.out.println("Enter the Account Number and Fund to be Transferred");
		int facc = sc.nextInt();
		boolean flag = false;
		double famt = sc.nextDouble();
		/*
		 * for(Account ac : accountList) { if(ac.getAccountNo()==sessionAccNo) {
		 * ac.setBalance(ac.getBalance()-famt); } if(ac.getAccountNo()==facc) {
		 * ac.setBalance(ac.getBalance()+famt); } }
		 */
		Account to = null;
		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == facc) {
				to = ac;
				// ac.setBalance(ac.getBalance()+famt);
				flag = true;
			}

		}
		if (flag == true) {

			Iterator<Account> it = accountMap.values().iterator();
			while (it.hasNext()) {
				Account ac = it.next();
				if (ac.getAccountNo() == sessionAccNo) {
					if (ac.getBalance() < famt) {
						throw new InsufficientBalanceException("Balance not Enough for Transfer");
					} else {
						ac.setBalance(ac.getBalance() - famt);
						to.setBalance(to.getBalance() + famt);
						//System.out.println("here");
						ac.getTrans().add(ac.getAccountNo() + " " + ac.getName() + "  " + ac.getAccType()
								+ "  Debited  " + famt + (new Date()).toString() + "  " + ac.getBalance());
					}
				}
			}
		} else {
			throw new AccountNotFoundException("No Such Account in the Bank");
		}
		return "Successfull";
	}

	@Override
	public String changePin() {
		System.out.println("Enter the Current Pin : ");
		Iterator<Account> itr = accountMap.values().iterator();
		while (itr.hasNext()) {
			Account ac = itr.next();
			if (ac.getAccountNo() == sessionAccNo) {
				if (sc.nextInt() == ac.getPin()) {
					System.out.println("Enter New Pin:");
					ac.setPin(sc.nextLong());
				}
				return "Successfull";
			}
		}
		return "Fail";

	}

}

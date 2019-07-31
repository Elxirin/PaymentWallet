package capgemini.project.account;

import java.util.Scanner;

public abstract class Account {

	protected static int accGen = 1000;
	protected int accountNo;
	protected long pin;
	protected String name, accType;
	protected double svBalance, crBalance, openingBal;

	Scanner sc = new Scanner(System.in);

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

	public Account getAccount() {
		System.out.println("Enter Name on the Account\t Account Type Savings/Current \topeneing Balance :");
		this.name = sc.next();
		this.accType = sc.next();
		this.svBalance = sc.nextDouble();
		accGen++;
		accountNo = accGen;
		pin = Math.round(Math.random() * 10000);
		System.out.println("Your Account No is : " + accountNo + "\nand Temporary Pin is :" + pin);
		return this;
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

	public long getPin() {
		return pin;
	}

	private void setPin(long pin) {
		this.pin = pin;
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

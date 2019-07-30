package capgemini.project.account;

public abstract class Account {

	protected static int accGen=1000;
	protected int accountNo;
	protected long pin;
	protected String name, accType;
	protected double svBalance, crBalance, openingBal;
	
	
	public Account() {
		
	}


	public int getAccountNo() {
		return accountNo;
	}

	private void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	private long getPin() {
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

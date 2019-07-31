package capgemini.project.banking;



import capgemini.project.account.Account;

public interface accountOperation {
	public boolean createAccount();
	
	public String login(int accountNo, long pin);
	
	public double showBalance();
	
	public  String deposit();
	
	public  String withdraw();
	
}

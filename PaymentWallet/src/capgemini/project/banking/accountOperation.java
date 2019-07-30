package capgemini.project.banking;

public interface accountOperation {
	public String createAccount();
	
	public String login(int accountNo, int pin);
	
	public double showBalance(int accountNo);
	
	public  String deposit(int accountNo, String type);
	
	public  String withdraw(int accountNo, String type);
	
}

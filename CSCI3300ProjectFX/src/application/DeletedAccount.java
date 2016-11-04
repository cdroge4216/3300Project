package application;

public class DeletedAccount extends Account
{
	int accountNumber = 4;

	public DeletedAccount(String name, double balance, String ssn, String address, String dob, String membersince,
			int pin, int accounttype, int accountnumber) 
	{
		super(name, balance, ssn, address, dob, membersince, pin, accounttype, accountnumber);
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}
}

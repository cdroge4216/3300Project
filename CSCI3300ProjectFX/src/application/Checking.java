package application;

public class Checking extends Account
{	
	public int numSavingsAccounts;
	public int numLoansAccounts;
	
	public Checking(String name, double balance, String ssn, String address,
			String dob, String membersince, int pin, int accounttype, int accountnumber, int numsavingsaccounts,
			int numloansaccounts) 
	{
		super(name, balance, ssn, address, dob, membersince, pin, accounttype, accountnumber);
		super.setNumberAccounts(super.getnumberAccounts() + 10);
		setAccountNumber(super.getnumberAccounts());
		super.setAccountType(1);
		numSavingsAccounts = numsavingsaccounts;
		numLoansAccounts= numloansaccounts;
	}
	
	//sets all info in constructor and super
	public void setAccountNumber(int accountnumber)
	{
	  super.setAccountNumber(accountnumber);
	}
	
	public void setName(String name)
	{
		super.setName(name);
	}
	
	public void setBalance(double balance)
	{
		super.setBalance(balance);
	}
	
	public void setSSN(String ssn)
	{
		super.setSSN(ssn);
	}
	
	public void setAddress(String address)
	{
		super.setAddress(address);
	}
	
	public void setDOB(String dob)
	{
		super.setDOB(dob);
	}
	
	public void setMemberSince(String membersince)
	{
		super.setMemberSince(membersince);
	}
	
	public void setPin(int pin)
	{
		super.setPin(pin);
	}
	
	public void setAccountType(int accounttype) 
	{
		super.setAccountType(accounttype);;
	}
	
	public void setNumLoansAccounts(int numloansaccounts)
	{
		numLoansAccounts = numloansaccounts;
	}
	
	public void setNumSavingsAccounts(int numsavingsaccounts)
	{
		numSavingsAccounts = numsavingsaccounts;
	}
	
	//gets all info in constructor and super
	public int getAccountNumber()
	{
		return super.getAccountNumber();
	}
	
	public String getName()
	{
		return super.getName();
	}
	
	public double getBalance()
	{
		return super.getBalance();
	}
	
	public String getSSN()
	{
		return super.getSSN();
	}
	
	public String getAddress()
	{
		return super.getAddress();
	}
	
	public String getDOB()
	{
		return super.getDOB();
	}
	
	public String getMemberSince()
	{
		return super.getMemberSince();
	}
	
	public int getPin()
	{
		return super.getPin();
	}
	
	public int getAccountType()
	{
		return super.getAccountType();
	}
	
	public int getnumberAccounts()
	{
		return super.getnumberAccounts();
	}
	
	public int getNumLoansAccounts()
	{
		return numLoansAccounts;
	}
	
	public int getNumSavingsAccounts()
	{
		return numSavingsAccounts;
	}
	
	//prints basic account info
	public String toString()
	{
		return (super.toString() + "; Account Type: Checking");
	}
	
	public String smallString()
	{
		return (super.smallString() + "; Account Type: Checking");
	}
	
	//deposits and withdraws money from the account
	public void deposit(double ammount) 
	{
		super.deposit(ammount);
		
	}
	
	public void withdraw(double ammount) 
	{
		super.withdraw(ammount);
		
	}
}

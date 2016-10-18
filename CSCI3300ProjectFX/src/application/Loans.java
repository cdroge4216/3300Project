package application;

public class Loans extends Account
{
	double rate = 1.05;
	int checkingAccountNumber;
	int numLoansAccounts;
	
	public Loans (String name, double balance, String ssn, String address, String dob, String membersince, int pin,
			int accounttype, int accountnumber, int checkingaccountNumber,int numloansaccounts, double rate)
	{
		super(name, balance, ssn, address, dob, membersince, pin, accounttype, accountnumber);
		checkingAccountNumber = checkingaccountNumber;
		super.setAccountNumber(checkingAccountNumber + numloansaccounts + 5);
		super.setAccountType(3);
		this.rate = rate;
		numLoansAccounts = numloansaccounts;
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
		super.setAccountType(accounttype);
	}
	
	public void setCheckingAccountNumber(int checkingaccountnumber) 
	{
		checkingAccountNumber = checkingaccountnumber;
	}
	
	public void setRate(double rate)
	{
		this.rate = rate;
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
	
	public int getCheckingAccountNumber()
	{
		return checkingAccountNumber;
	}
	
	public double getRate()
	{
		return rate;
	}
	
	public int getnumberAccounts()
	{
		return super.getnumberAccounts();
	}
	
	public int getNumLoansAccounts()
	{
		return numLoansAccounts;
	}
	
	//prints basic account info
	public String toString()
	{
		return (super.toString() + "; Account Type: Savings");
	}
	
	public String smallString()
	{
		return (super.smallString() + "; Account Type: Savings");
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


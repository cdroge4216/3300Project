package application;

public class Savings extends Account
{
	int fee = 3;
	double interest = 1.02;
	int checkingAccountNumber;
	int numSavingsAccounts;
	
	public Savings (String name, double balance, String ssn, String address, String dob, String membersince, int pin,
			int accounttype, int accountnumber, int checkingaccountNumber,int numsavingsaccounts)
	{
		super(name, balance, ssn, address, dob, membersince, pin, accounttype, accountnumber);
		checkingAccountNumber = checkingaccountNumber;
		super.setAccountNumber(checkingAccountNumber + numsavingsaccounts);
		super.setAccountType(2);
		numSavingsAccounts = numsavingsaccounts;
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
	
	public void setFee(int fee)
	{
		this.fee = fee;
	}
	
	public void setInterest(double interest)
	{
		this.interest = interest;
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
	
	public int getFee()
	{
		return fee;
	}
	
	public double getInterest()
	{
		return interest;
	}
	
	public int getnumberAccounts()
	{
		return super.getnumberAccounts();
	}
	
	public int getNumSavingsAccounts()
	{
		return numSavingsAccounts;
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
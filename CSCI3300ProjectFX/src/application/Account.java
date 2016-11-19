package application;

public class Account 
{
	private String Name;
	private double Balance;
	private String SSN;
	private String Address;
	private String DOB;
	private String memberSince;
	private int Pin;
	//!!!need to save to dat file so it doesnt rely on static!!!
	private static int numberAccounts = 0;
	private int accountType;
	private int accountNumber;
	
	public Account(String name, double balance, String ssn, String address, String dob, String membersince, 
			int pin, int accounttype, int accountnumber)
	{
		Name = name;
		Balance = balance;
		SSN = ssn;
		Address = address;
		DOB = dob;
		memberSince = membersince;
		Pin = pin;
		accountType = accounttype;
		accountNumber = accountnumber;
	}
	
	//sets all info in constructor
	public void setNumberAccounts(int numberaccounts)
	{
		numberAccounts = numberaccounts;
	}
	
	public static void staticSetNumberAccounts(int numberaccounts)
	{
		numberAccounts = numberaccounts;
	}
	
	public void setAccountNumber(int accountnumber)
	{
		accountNumber = accountnumber;
	}
	
	public void setName(String name)
	{
		Name = name;
	}
	
	public void setBalance(double balance)
	{
		Balance = balance;
	}
	
	public void setSSN(String ssn)
	{
		SSN = ssn;
	}
	
	public void setAddress(String address)
	{
		Address = address;
	}
	
	public void setDOB(String dob)
	{
		DOB = dob;
	}
	
	public void setMemberSince(String membersince)
	{
		memberSince = membersince;
	}
	
	public void setPin(int pin)
	{
		Pin = pin;
	}
	
	public void setAccountType(int accounttype)
	{
		accountType = accounttype;
	}
	
	public static int staticGetnumberAccounts() 
	{
		return numberAccounts;
	}
	
	public  int getnumberAccounts() 
	{
		return numberAccounts;
	}
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public String getName()
	{
		return Name;
	}
	
	//gets all info in constructor
	public double getBalance()
	{
		return Balance;
	}
	
	public String getSSN()
	{
		return SSN;
	}
	
	public String getAddress()
	{
		return Address;
	}
	
	public String getDOB()
	{
		return DOB;
	}
	
	public String getMemberSince()
	{
		return memberSince;
	}
	
	public int getPin()
	{
		return Pin;
	}
	public int getAccountType()
	{
		return accountType;
	}
	
	public String toString()
	{
		return ("Account Number: " + accountNumber + "; Name: " + Name + "; Balance: " + Balance + "; \n Social Security Number: " + SSN + "; Address: " + Address 
				
				+ "; \n Date of Birth: " + DOB + "; Member Since: " + memberSince + "; Pin: " + Pin);
	}
	
	public String smallString()
	{
		return ("Account Number: " + accountNumber + "; Name: " + Name + "; \n Date of Birth: " + DOB + "; Member Since: " + memberSince );
	}

	public void deposit(double ammount) 
	{
		Balance += ammount;
		
	}
	
	public void withdraw(double ammount) 
	{
		Balance -= ammount;
		
	}

	

}


package application;

public class Employee 
{
	private int IdNumber, Passcode, EmployeeType;
	
	public Employee(int idNumber, int passcode, int employeeType)
	{
		IdNumber = idNumber;
		Passcode = passcode;
		EmployeeType = employeeType;
	}

	public int getIdNumber() 
	{
		return IdNumber;
	}

	public void setIdNumber(int idNumber) 
	{
		IdNumber = idNumber;
	}

	public int getPasscode() 
	{
		return Passcode;
	}

	public void setPasscode(int passcode) 
	{
		Passcode = passcode;
	}

	public int getEmployeeType() 
	{
		return EmployeeType;
	}

	public void setEmployeeType(int employeeType) 
	{
		EmployeeType = employeeType;
	}
}

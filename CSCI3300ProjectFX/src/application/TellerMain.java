package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

public class TellerMain 
{
	//*************************************
	//This is test code for gethub
	//*************************************
	
	
	// list used to display and minipulate the accounts
	static Account[] accountList = new Account[0];
	static int [] sortList = new int[0];

	static Scanner scan = new Scanner(System.in);
	
	//*************************************
	//Main Method
	//*************************************
	public static void main(String[ ] args) throws IOException
		{
		System.out.println(LocalDateTime.now());
		/*String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String timeStampYear = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		System.out.println(timeStampYear);*/
		//reads in data from the accounts.dat file
		reader();
		
		/*makes room for new account
		Account[] temp = new Account[accountList.length+1];
			
		for(int i = 0; i < accountList.length; i++)
		{
			temp[i] = accountList[i];
		}
			
		accountList = new Account[temp.length];
			
		for(int i = 0; i < accountList.length; i++)
		{
			accountList[i] = temp[i];
		}
			
		int[] tempS = new int[sortList.length+1];
			
		for(int i = 0; i < sortList.length; i++)
		{
			tempS[i] = sortList[i];
		}
			
		sortList = new int[tempS.length];
			
		for(int i = 0; i < sortList.length; i++)
		{
			sortList[i] = tempS[i];
		}
		accountList[0] = new Checking("Name", 1, "12345678", "Address",
				"BirthDate", "MemberDAte", 0000, 1, 10, 0, 0);
		sortList[0] = 10;
		System.out.println(accountList.length);*/
		//sorts the accounts based on account number 
		sort();
		
		//prints teller menu
		printMenu();
		int choice = scan.nextInt();
		while (choice != 0)
		 {
			dispatch(choice);
			printMenu();
			choice = scan.nextInt();
		 }
		
		//writes all account info to accounts.dat apon closing the program
		writer();
		}
	
	//***********************************************************
	//controls menu options, routes options to correct methods
	//***********************************************************
	public static void dispatch(int choice)
	{
		
		switch(choice)
		 {
			 case 0:
				 	System.out.println("Bye!");
				 	writer();
				 	break;
			
			 //Print a List of all accounts
			 case 1:
				 	printSmall();
				 		break;
			 
			//Prints all details for a single account
			 case 2:
				 	printAll();
					break;
			
			//withdraws from any account		
			 case 3:
					withdraw();
					break;
			
			//deposits into any account
			 case 4:
					deposit();
					break;
					
			//Pay towards a loan.
			 case 5:
				 	payLoan();
					break;
						
			//creates new Checking and Savings
			 case 6:
				 newChecking();
				 sort();
					break;
			
			//Create a new Savings Account		
			 case 7:
					newSavings();
					sort();
					break;
					
			//Creates new loan
			 case 8:
				 	newLoan();
				 	sort();
					break;
					
			 //Pays interest on Savings accounts and applies interest on loans
			 case 9:
				 	//interest();
					break;
				
			 default:
				 System.out.println("Sorry, invalid choice");
		 }
	}
	
	//********************************************
	//sorts the list of accounts by account number
	//********************************************
	public static void sort()
	{
		int min = 0, temp;
		Account TEMP = new Account(null, 0, null, null, null, null, 0, 0, 0);
			
		for(int index = 0; index < sortList.length; index ++)
		{
			min = index;
			//System.out.println(index);
			//System.out.println(sortList.length);
			for(int scan = (index); scan <sortList.length; scan++)
			{
				if(sortList[scan] < sortList[min])
				{
					min = scan;
				}			
			}
			temp = sortList[index];
			TEMP = accountList[index];
			sortList[index] = sortList[min];
			//System.out.println(accountList[index]);
			//System.out.println(min);
			//System.out.println(accountList[min]);
			accountList[index] = accountList[min];
			sortList[min] = temp;
			accountList[min] = TEMP;
		}
	}
		
	//******************************
	//Prints the User menu
	//******************************
	public static void printMenu()
	{
		System.out.println("\n Menu ");
		System.out.println(" ====");
		System.out.println("0: Quit");
		System.out.println("1: Print a List of all accounts.");
		System.out.println("2: Prints all details for a single account.");
		System.out.println("3: Withdraw a certain amount from an account.");
		System.out.println("4: Deposit a certain amount into an account.");
		System.out.println("5: Pay towards a loan.");
		System.out.println("6: Create a new Checking Account (will create a required savings account).");
		System.out.println("7: Create a new Savings Account (requires a checking account).");
		System.out.println("8: Create a new Loan (requires a checking and savings account).");
		System.out.println("9: Pays interest on Savings accounts and applies interest on loans (only do on the first of the month!).");
		System.out.print("\nEnter your choice: ");
	}
		
	//****************************************
	//reads in saved file
	//****************************************
	public static void reader() throws IOException
	{
		String Name = null;
		double Balance = 0;
		String SSN = null;
		String Address = null;
		String DOB = null;
		String memberSince = null;
		int Pin = 0;
		double rate = 0.0;
		int accountNumber = 0;
		int numSavingsAccounts = 0;
		int numLoansAccounts = 0;
		int accountType = 0;
		int checkingaccountNumber = 0;
			
		Scanner scan = new Scanner(new File("accounts.dat"));
		while(accountType != 5)
		{
			//System.out.println(scan.nextLine());
			accountType = scan.nextInt();
			System.out.println("AT: " + accountType);
					
			//scans checking accounts
			if(accountType == 1)
			{
				//note* had to double scan for some reason in eclipse?
				Name = scan.nextLine();
				Name = scan.nextLine();
				System.out.println("NAME" + Name);
				Balance = scan.nextDouble();
				System.out.println(Balance);
				SSN = scan.nextLine();
				SSN = scan.nextLine();
				System.out.println("SSN:" + SSN);
				Address = scan.nextLine();
				System.out.println("Address" + Address);
				DOB = scan.nextLine();
				System.out.println("DoB" + DOB);
				memberSince = scan.nextLine();
				System.out.println("MS" + memberSince);
				Pin = scan.nextInt();
				System.out.println("pin" + Pin);
				accountNumber = scan.nextInt();
				System.out.println("AN:" + accountNumber);
				numSavingsAccounts = scan.nextInt();
				System.out.println("numSavings" + numSavingsAccounts);
				numLoansAccounts = scan.nextInt();
				System.out.println("numLoans" + numLoansAccounts);
					
					
				//makes room for new account
				Account[] temp = new Account[accountList.length+1];
					
				for(int i = 0; i < accountList.length; i++)
				{
					temp[i] = accountList[i];
				}
					
				accountList = new Account[temp.length];
					
				for(int i = 0; i < accountList.length; i++)
				{
					accountList[i] = temp[i];
				}
					
				int[] tempS = new int[sortList.length+1];
					
				for(int i = 0; i < sortList.length; i++)
				{
					tempS[i] = sortList[i];
				}
					
				sortList = new int[tempS.length];
					
				for(int i = 0; i < sortList.length; i++)
				{
					sortList[i] = tempS[i];
				}
					
				//creates new checking account
				accountList[accountList.length-1] = new Checking(Name, Balance, SSN, Address, DOB, memberSince, Pin,
						accountType, accountNumber, numSavingsAccounts, numLoansAccounts);
				sortList[accountList.length-1] = accountList[accountList.length-1].getnumberAccounts();
				//accountList[accountList.length-1].setAccountNumber(AN + "");
				sortList[(sortList.length-1)] = accountNumber;
				System.out.println(accountList[(accountList.length-1)].toString());
			}
				
			//scans Savings accounts
			if(accountType == 2)
			{
				//note* had to double scan for some reason in eclipse?
				Name = scan.nextLine();
				Name = scan.nextLine();
				System.out.println("NAME" + Name);
				Balance = scan.nextDouble();
				System.out.println(Balance);
				SSN = scan.nextLine();
				SSN = scan.nextLine();
				System.out.println("SSN:" + SSN);
				Address = scan.nextLine();
				System.out.println("Address" + Address);
				DOB = scan.nextLine();
				System.out.println("DoB" + DOB);
				memberSince = scan.nextLine();
				System.out.println("MS" + memberSince);
				Pin = scan.nextInt();
				System.out.println("pin" + Pin);
				accountNumber = scan.nextInt();
				System.out.println("AN:" + accountNumber);
				checkingaccountNumber = scan.nextInt();
				System.out.println("CN" + checkingaccountNumber);
				numSavingsAccounts = scan.nextInt();
				System.out.println("numSA:" + numSavingsAccounts);
					
					
				//makes room for new account
				Account[] temp = new Account[accountList.length+1];
					
				for(int i = 0; i < accountList.length; i++)
				{
					temp[i] = accountList[i];
				}
					
				accountList = new Account[temp.length];
					
				for(int i = 0; i < accountList.length; i++)
				{
					accountList[i] = temp[i];
				}
					
				int[] tempS = new int[sortList.length+1];
					
				for(int i = 0; i < sortList.length; i++)
				{
					tempS[i] = sortList[i];
				}
					
				sortList = new int[tempS.length];
					
				for(int i = 0; i < sortList.length; i++)
				{
					sortList[i] = tempS[i];
				}
					
				//creates new savings account
				accountList[(accountList.length-1)] = new Savings(Name, Balance, SSN, Address, DOB,
						memberSince, Pin, 0, accountType, checkingaccountNumber, numSavingsAccounts);
				//accountList[accountList.length-1].setAccountNumber(AN + "");
				sortList[(sortList.length-1)] = accountNumber; 
				//System.out.println(accountList[(accountList.length-1)].toString());
			}
				
			//scans Loan accounts
			if(accountType == 3)
			{
				//note* had to double scan for some reason in eclipse?
				Name = scan.nextLine();
				Name = scan.nextLine();
				//System.out.println("NAME" + Name);
				Balance = scan.nextDouble();
				//System.out.println(Balance);
				SSN = scan.nextLine();
				SSN = scan.nextLine();
				//System.out.println("SSN:" + SSN);
				Address = scan.nextLine();
				//System.out.println("Address" + Address);
				DOB = scan.nextLine();
				//System.out.println("DoB" + DOB);
				memberSince = scan.nextLine();
				//System.out.println("MS" + memberSince);
				Pin = scan.nextInt();
				//System.out.println("pin" + Pin);
				accountNumber = scan.nextInt();
				checkingaccountNumber = scan.nextInt();
				numLoansAccounts = scan.nextInt();
				rate = scan.nextDouble();
					
				//makes room for new account
				Account[] temp = new Account[accountList.length+1];
					
				for(int i = 0; i < accountList.length; i++)
				{
					temp[i] = accountList[i];
				}
					
				accountList = new Account[temp.length];
					
				for(int i = 0; i < accountList.length; i++)
				{
					accountList[i] = temp[i];
				}
					
				int[] tempS = new int[sortList.length+1];
					
				for(int i = 0; i < sortList.length; i++)
				{
					tempS[i] = sortList[i];
				}
					
				sortList = new int[tempS.length];
					
				for(int i = 0; i < sortList.length; i++)
				{
					sortList[i] = tempS[i];
				}
					
				//creates new loan account
				accountList[(accountList.length-1)] = new Loans(Name, Balance, SSN, Address, DOB, memberSince, Pin,
						accountType, accountNumber, checkingaccountNumber, numLoansAccounts, rate);
				//accountList[accountList.length-1].setAccountNumber(AN + "");
				sortList[(sortList.length-1)] = accountNumber;
				//System.out.println(accountList[(accountList.length-1)].toString());
			}
		}
			
	}		
	//****************************************
	//Writes file to save accounts
	//****************************************
	public static void writer()
	{
		int AT = 0;
			
		//clears old file
		File file = new File("accounts.dat");
		FileWriter fo = null;
		BufferedWriter bw = null;
		try 
		{
			fo = new FileWriter(file);
			bw = new BufferedWriter(fo);
			bw.write("");
		} 
			
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				if(bw!=null) bw.close();
			} 
				
			catch (IOException e) 
			{
				e.printStackTrace();
			}
				
			try 
			{
				if(fo!=null) fo.close();
			} 
				
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
			
		//writes new file
		file = new File("accounts.dat");
		fo = null;
		bw = null;
		PrintWriter po = null;
		try 
		{
			fo = new FileWriter(file, true);
			bw = new BufferedWriter(fo);
			po = new PrintWriter(bw);
			for(int i = 0; i < accountList.length; i++)
			{
				System.out.println(i);
				System.out.println("AN" + accountList[i].getAccountNumber());
				//determines type of account
				if(accountList[i].getAccountType() == 1)
				{
					AT = 1;
				}
					
				for(int j = 1 ; j < 6; j ++)
				{
					if(accountList[i].getAccountType() == 2)
					{
						AT = 2;
					}
				}
				
				for(int j = 6 ; j < 10; j ++)
				{
					if(accountList[i].getAccountType() == 3)
					{
						AT = 3;
					}
				}	
								
				System.out.println(AT);
					
				//prints checking
				if(AT == 1)
				{
					po.println("1");
					po.println(accountList[i].getName());
					po.println(accountList[i].getBalance());
					po.println(accountList[i].getSSN());
					po.println(accountList[i].getAddress());
					po.println(accountList[i].getDOB());
					po.println(accountList[i].getMemberSince());
					po.println(accountList[i].getPin());
					po.println(((Checking) accountList[i]).getAccountNumber());
					po.println(((Checking)accountList[i]).getNumSavingsAccounts());
					po.println(((Checking)accountList[i]).getNumLoansAccounts());
				}
						
				//prints savings
				if(AT == 2)
				{
					po.println("2");
					po.println(accountList[i].getName());
					po.println(accountList[i].getBalance());
					po.println(accountList[i].getSSN());
					po.println(accountList[i].getAddress());
					po.println(accountList[i].getDOB());
					po.println(accountList[i].getMemberSince());
					po.println(accountList[i].getPin());
					po.println(((Savings) accountList[i]).getAccountNumber());
					po.println(((Savings) accountList[i]).getCheckingAccountNumber());
					po.println(((Savings)accountList[i]).getNumSavingsAccounts());
				}
						
				//prints loans
				if(AT == 3)
				{
					po.println("3");
					po.println(accountList[i].getName());
					po.println(accountList[i].getBalance());
					po.println(accountList[i].getSSN());
					po.println(accountList[i].getAddress());
					po.println(accountList[i].getDOB());
					po.println(accountList[i].getMemberSince());
					po.println(accountList[i].getPin());
					po.println(((Loans) accountList[i]).getAccountNumber());
					po.println(((Loans) accountList[i]).getCheckingAccountNumber());
					po.println(((Loans) accountList[i]).getNumLoansAccounts());
					po.println(((Loans) accountList[i]).getRate());
					
				}
						
				if(AT == 0)
				{
					po.println("ERROR");
				}
			}
				
			//reader method didnt see first 5 so had to add a second
			po.println("5");
			po.println("5");
				
		}
					
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				if(po!=null) po.close();
				if(bw!=null) bw.close();
				if(fo!=null) po.close();
			}
				
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	//***************************************
	//prints public details for all accounts
	//***************************************
	public static void printSmall()
	{
		for(int i = 0; i < accountList.length; i++)
		{
			System.out.println(accountList[i].smallString());
		}
	}
	
	//***************************************
	//Prints all details for a single account
	//***************************************
	public static void printAll()
	{
		int sortNum = 0;
		int checking = 0;
		int realAccount = 0;
		//collects data for new account
				System.out.println("Please enter the customer's account number. (##)");
				checking = scan.nextInt();
				
		//looks for all info related to the account number
		for(int i = 0; i < sortList.length; i++)
		{
			if (checking == sortList[i])
			{
				sortNum = i;
				realAccount = 1;
			}
		}
		
		//makes sure account exist
		if(realAccount == 0)
		{
			System.out.println("The account number you entered does not exist");
			printMenu();
			int choice = scan.nextInt();
			while (choice != 0)
			 {
				dispatch(choice);
				printMenu();
				choice = scan.nextInt();
			 }
		}		
		
		//prints details
		System.out.println("Below is the info for the account.");
		System.out.println(accountList[sortNum].toString());
			
	}

	//*******************************************************
	//creates new checking account with a new savings account
	//*******************************************************
	public static void newChecking() 
	{
		
		String name = null;
		double balanceChecking = 0;
		double balanceSavings = 0;
		String ssn = null;
		String address = null;
		String dob = null;
		String memberDate = null;
		int pin = 0;
		int accounts = 0;
		int accountNumber = 0;
		int correct1 = 0;
		int correct2 = 0;
		
		//makes room for new account
		Account[] temp = new Account[accountList.length+2];
		
		for(int i = 0; i < accountList.length; i++)
		{
			temp[i] = accountList[i];
		}
		
		accountList = new Account[temp.length];
		
		for(int i = 0; i < accountList.length; i++)
		{
			accountList[i] = temp[i];
		}
		
		int[] tempS = new int[sortList.length+2];
		
		for(int i = 0; i < sortList.length; i++)
		{
			tempS[i] = sortList[i];
		}
		
		sortList = new int[tempS.length];
		
		for(int i = 0; i < sortList.length; i++)
		{
			sortList[i] = tempS[i];
		}
		
		
		//collects data for new account
		
		//inputs starting balance
		System.out.println("Please enter the customer's name. (Last Name, First Name)");
		name = scan.nextLine();
		name = scan.nextLine();
		System.out.println("Please enter the customer's checking starting balance. (###.##)");
		//checks for correct format
		correct1 = 0;
		while(correct1 == 0)
		{
			correct2 = 0;
			try
			{
				balanceChecking = scan.nextDouble();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Please enter the starting balance using the correct format: ###.##");
				correct2 = 1;
			}
			finally
			{
				if(correct2 == 0)
				{
					correct1 = 1;
				}
				else
				{
					balanceChecking = 0.0;
					//Eclipse requires hold?
					String hold = scan.nextLine();
					balanceChecking = scan.nextDouble();
				}
			}
			System.out.println("Please enter the customer's savings starting balance. (###.##)");
			//checks for correct format
			correct1 = 0;
			while(correct1 == 0)
			{
				correct2 = 0;
				try
				{
					balanceSavings = scan.nextDouble();
				}
				catch(java.util.InputMismatchException e)
				{
					System.out.println("Please enter the starting balance using the correct format: ###.##");
					correct2 = 1;
				}
				finally
				{
					if(correct2 == 0)
					{
						correct1 = 1;
					}
					else
					{
						balanceSavings = 0.0;
						//Eclipse requires hold?
						String hold = scan.nextLine();
						balanceSavings = scan.nextDouble();
					}
				}
		}
		correct1 = 0;
		
		//inputs SSN
		System.out.println("Please enter the customer's Social Security Number. (#########)");
		ssn = scan.nextLine();
		while(correct1 == 0)
		{
			ssn = scan.nextLine();
			//Checks for correctness of input
			for(int i = 0; i < ssn.length(); i ++)
			{
				if (ssn.charAt(i) != '0' && ssn.charAt(i) != '1' && ssn.charAt(i) != '2' && ssn.charAt(i) != '3' && ssn.charAt(i) != '4' &&
						ssn.charAt(i) != '5' && ssn.charAt(i) != '6' && ssn.charAt(i) != '7' && ssn.charAt(i) != '8' && ssn.charAt(i) != '9' )
				{
					System.out.println("Please enter the SSN in the correct format: #########");
					correct1 = 0;
				}
				
				else
				{
					correct1 = 1;
				}
			}
			
			if(ssn.length() != 9 && correct1 == 1)
			{
				System.out.println("Please enter the SSN in the correct format: #########");
				correct1 = 0;
			}
		}
		
		//inputs address
		System.out.println("Please enter the customer's Address.(# Street, City, State, #####)");
		address = scan.nextLine();
		
		//inputs dOB
		System.out.println("Please enter the customer's Date of Birth. (##JAN####)");
		correct1 = 0;
		while(correct1 == 0)
		{
			dob = scan.nextLine();
			//Checks for correctness of input
			if(dob.length() != 9)
			{
				System.out.println("Please enter the dob in the correct format: ##JAN####");
				correct1 = 0;
			}
			else if (dob.length() == 9)
			{
				correct1 = 1;
			}
			
			if(correct1 == 1)
			{
				for(int i = 0; i < 2; i ++)
				{
					if (dob.charAt(i) != '0' && dob.charAt(i) != '1' && dob.charAt(i) != '2' && dob.charAt(i) != '3' && dob.charAt(i) != '4' &&
							dob.charAt(i) != '5' && dob.charAt(i) != '6' && dob.charAt(i) != '7' && dob.charAt(i) != '8' && dob.charAt(i) != '9' )
					{
						System.out.println("Please enter the dob in the correct format: ##JAN####");
						correct1 = 0;
					}
					
					else
					{
						correct1 = 1;
					}
				}
			}
			if(correct1 == 1)
			{
				correct2 = 1;
				for(int i = 2; i < 5; i ++)
				{
					//System.out.println(dob.charAt(i));
					if (dob.charAt(i) == 'A' || dob.charAt(i) == 'B' || dob.charAt(i) == 'C' || dob.charAt(i) == 'D' || dob.charAt(i) == 'E' 
							|| dob.charAt(i) == 'F'|| dob.charAt(i) == 'G' || dob.charAt(i) == 'H' || dob.charAt(i) == 'I' || dob.charAt(i) == 'J' 
							|| dob.charAt(i) == 'K'|| dob.charAt(i) == 'L' || dob.charAt(i) == 'M' || dob.charAt(i) == 'N' || dob.charAt(i) == 'O' 
							|| dob.charAt(i) == 'P' || dob.charAt(i) == 'Q' || dob.charAt(i) == 'R' || dob.charAt(i) == 'S' || dob.charAt(i) == 'T' 
							|| dob.charAt(i) == 'U' || dob.charAt(i) == 'V' || dob.charAt(i) == 'W' || dob.charAt(i) == 'X' || dob.charAt(i) == 'Y' 
							||dob.charAt(i) == 'Z')
					{
						correct1 = 1;
					}
					
					else
					{
						System.out.println("Please enter the dob in the correct format: ##JAN####");
						correct2 = 0;
					}
				}
				
				if(correct2 == 0)
				{
					correct1  = 0;
				}
			}
			
			if(correct1 == 1)
			{
				for(int i = 5; i < 9; i ++)
				{
					if (dob.charAt(i) != '0' && dob.charAt(i) != '1' && dob.charAt(i) != '2' && dob.charAt(i) != '3' && dob.charAt(i) != '4' &&
							dob.charAt(i) != '5' && dob.charAt(i) != '6' && dob.charAt(i) != '7' && dob.charAt(i) != '8' && dob.charAt(i) != '9' )
					{
						System.out.println("Please enter the dob in the correct format: ##JAN####");
						correct1 = 0;
					}
					
					else
					{
						correct1 = 1;
					}
				}
			}
		}
		
		//inputs current date
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		memberDate = timeStamp;
		
		//inputs pin
		System.out.println("Please enter a pin for this customer's account. (####) ");
			//check correctness of input
		correct1 = 0;
		while(correct1 == 0)
		{
			try
			{
				pin = scan.nextInt();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Please enter the pin using the correct format: ####");
				correct1 = 0;
			}
			finally
			{
				
			}
			if(pin > 9999 || pin < 0)
			{
				System.out.println("Please enter the date in the correct format: ####");
				correct1 = 0;
			}
			else if (pin < 10000 && pin > 0)
			{
				correct1 = 1;
			}
			
		}
		
		
		//creates new checking account
		accountList[(accountList.length-2)] = new Checking(name, balanceChecking, ssn, address, dob,
				memberDate, pin, 1, accountNumber, 1, 0);
		sortList[(sortList.length-2)] = accountList[(accountList.length-2)].getnumberAccounts();
		int AN = accountList[(accountList.length-2)].getnumberAccounts();
		
		//creates new Savings
		accountList[(accountList.length-1)] = new Savings(name, balanceSavings, ssn, address, dob, memberDate, pin, 2, (AN + 1), AN, 1);
		sortList[(sortList.length-1)] = (AN + 1);
		
		//Prints Info for both new accounts
		System.out.println("Below is the account infor for each account:");
		System.out.println(accountList[(accountList.length-2)].toString());
		System.out.println(accountList[(accountList.length-1)].toString());
		
		}
	}
	
	//******************************
	//Creates a new savings account
	//******************************
	public static void newSavings() 
	{
		
		String name = null;
		double balance = 0;
		String ssn = null;
		String address = null;
		String dob = null;
		String MS = null;
		int pin = 0;
		int accounts = 0;
		int checking = 0;
		int checkingNumber = 0;
		int correct1 = 0;
		int correct2 = 0;
		String pinString = null;
		
		//makes room for new account
		Account[] temp = new Account[accountList.length+1];
		
		for(int i = 0; i < accountList.length; i++)
		{
			temp[i] = accountList[i];
		}
		
		accountList = new Account[temp.length];
		
		for(int i = 0; i < accountList.length; i++)
		{
			accountList[i] = temp[i];
		}
		
		int[] tempS = new int[sortList.length+1];
		
		for(int i = 0; i < sortList.length; i++)
		{
			tempS[i] = sortList[i];
		}
		
		sortList = new int[tempS.length];
		
		for(int i = 0; i < sortList.length; i++)
		{
			sortList[i] = tempS[i];
		}
		
		//collects data for new account
		System.out.println("Please enter the customer's Checking account number. (##)");
		checkingNumber = scan.nextInt();
		
		//looks for all info related to the account number
				for(int i = 0; i < sortList.length; i++)
				{
					if (checkingNumber == sortList[i])
					{
						checking = i;
					}
				}
		
		//collects the remaining info
		accounts = ((Checking)accountList[checking]).getNumSavingsAccounts();
		
		System.out.println("Please enter the customer's savings starting balance. (###.##)");
		
		//checks for correct format
		correct1 = 0;
		while(correct1 == 0)
		{
			correct2 = 0;
			try
			{
				balance = scan.nextDouble();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Please enter the starting balance using the correct format: ###.##");
				correct2 = 1;
			}
			finally
			{
				if(correct2 == 0)
				{
					correct1 = 1;
				}
				else
				{
					balance = 0.0;
					String hold = scan.nextLine();
					balance = scan.nextDouble();
				}
			}
		}
		
		//creates new Savings
		accountList[(accountList.length-1)] = new Savings(accountList[checking].getName(), 
				balance, accountList[checking].getSSN(), accountList[checking].getAddress(),
				accountList[checking].getDOB(), accountList[checking].getMemberSince(),
				accountList[checking].getPin(), 2, (checkingNumber + accounts + 1), checkingNumber, (accounts + 1));
		
		sortList[(accountList.length-1)] = (checking + accounts + 1); 
		
		//Updates attached checking account to show the proper amount of savings accounts
		((Checking) accountList[checking]).setNumSavingsAccounts(((Checking) accountList[checking]).
				getNumSavingsAccounts() + 1);
		
		//Prints Info for the new account
		System.out.println("Below is the account infor for the new account:");
		System.out.println(accountList[(accountList.length-1)].toString());
		
	}
	
	//*********************************************************************************************
	//Creates a new Loan attached to a checking account and deposits amount to the checking account
	//*********************************************************************************************
	public static void newLoan() 
	{
		
		String name = null;
		double balance = 0;
		String ssn = null;
		String address = null;
		String dob = null;
		String MS = null;
		int pin = 0;
		int accounts = 0;
		int checking = 0;
		int checkingNumber = 0;
		int correct1 = 0;
		int correct2 = 0;
		double rate = 0;
		String pinString = null;
		
		//makes room for new account
		Account[] temp = new Account[accountList.length+1];
		
		for(int i = 0; i < accountList.length; i++)
		{
			temp[i] = accountList[i];
		}
		
		accountList = new Account[temp.length];
		
		for(int i = 0; i < accountList.length; i++)
		{
			accountList[i] = temp[i];
		}
		
		int[] tempS = new int[sortList.length+1];
		
		for(int i = 0; i < sortList.length; i++)
		{
			tempS[i] = sortList[i];
		}
		
		sortList = new int[tempS.length];
		
		for(int i = 0; i < sortList.length; i++)
		{
			sortList[i] = tempS[i];
		}
		
		//collects data for new account
				System.out.println("Please enter the customer's Checking account number. (##)");
				checkingNumber = scan.nextInt();

		//looks for all info related to the account number
				for(int i = 0; i < sortList.length; i++)
				{
					if (checkingNumber == sortList[i])
					{
						checking = i;
					}
				}
				
		//collects the remaining info
		accounts = ((Checking)accountList[checking]).getNumLoansAccounts();
		
		System.out.println("Please enter the loan's starting balance. (###.##)");
		//checks for correct format
		correct1 = 0;
		while(correct1 == 0)
		{
			correct2 = 0;
			try
			{
				balance = scan.nextDouble();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Please enter the starting balance using the correct format: ###.##");
				correct2 = 1;
			}
			finally
			{
				if(correct2 == 0)
				{
					correct1 = 1;
				}
				else
				{
					balance = 0.0;
					String hold = scan.nextLine();
					balance = scan.nextDouble();
				}
			}
		}
		
		System.out.println("Please enter the interest rate for this Loan. (1.##)");
		rate = scan.nextDouble();
	
		//creates new Loan
		accountList[(accountList.length-1)] = new Loans(accountList[checking].getName(), balance,
				accountList[checking].getSSN(), accountList[checking].getAddress(),
				accountList[checking].getDOB(), accountList[checking].getMemberSince(),
				accountList[checking].getPin(), 3,
				(checkingNumber + accounts + 1), checkingNumber, (accounts + 1), rate);
		
		sortList[(accountList.length-1)] = (checkingNumber + accounts + 6); 
		
		//Adds the amount of the loan to the corresponding checking account
		accountList[checking].deposit(balance);
		
		//Updates attached checking account to show the proper amount of savings accounts
				((Checking) accountList[checking]).setNumLoansAccounts(((Checking) accountList[checking]).
						getNumLoansAccounts() + 1);
		
		//Prints Info for the new account and attached checking account
		System.out.println("Below is the account info for the new account:");
		System.out.println(accountList[(accountList.length-1)].toString());
		System.out.println("Below is the old balance for the attached checking acccount:");
		System.out.println(accountList[checking].getBalance() - balance);
		System.out.println("Below is the new balance for the attached checking acccount:");
		System.out.println(accountList[checking].getBalance());
		
	}
	
	//*****************************************
	//withdraw a certain amount from an account
	//!!!!!ENTERS LOOP IF NOT ENOUGH MONEY!!!!!
	//*****************************************
	public static void withdraw()
	{
		int sortNum = 0;
		int checking = 0;
		int pin = 0;
		int getpin = 1;
		int AT = 5;
		double ammount = 0;
		String cont = "yes";
		double test = 0;
		int realAccount = 0;
		
		//collects data for new account
		System.out.println("Please enter the customer's account number. (##)");
		checking = scan.nextInt();
			
		//looks for all info related to the account number
		for(int i = 0; i < sortList.length; i++)
		{
			if (checking == sortList[i])
			{
				sortNum = i;
				realAccount = 1; 
			}
		}
		
		//makes sure account exist
		if(realAccount == 0)
		{
			System.out.println("The account number you entered does not exist");
			printMenu();
			int choice = scan.nextInt();
			while (choice != 0)
			 {
				dispatch(choice);
				printMenu();
				choice = scan.nextInt();
			 }
		}
		
		//determines type of account
		AT = accountList[sortNum].getAccountType();
		System.out.println(AT);
			
		//Informs condition
		if(AT == 1)
		{
			System.out.println("Congrats! No Fee to withdraw from a checking account at CSCI Bank!");
		}
		
		if(AT == 2)
		{
			
			System.out.println("Please note that there is a $3 fee to withdraw from a savings account. \n Would you like to continue? Y or N?");
			//cont = scan.nextLine();
			//cont = scan.nextLine();
			//if (cont.charAt(0) == 'N' || cont.charAt(0) == 'n')
			//{
			//	printMenu();
			//	int choice = scan.nextInt();
			//	while (choice != 0)
			//	 {
			//		dispatch(choice);
			//		printMenu();
			//		choice = scan.nextInt();
			//	 }
			//}
		}
		
		if(AT == 3)
		{
			
			System.out.println("Sorry you can not withdraw from a loan. \n Please pay towards the loan or create a new loan in the menu. \n");
					//+ "Would you like to try a different account #? Y or N?");
			//cont = scan.nextLine();
			//cont = scan.nextLine();
			//if (cont.charAt(0) == 'N' || cont.charAt(0) == 'n')
			//{
			//	printMenu();
			//	int choice = scan.nextInt();
			//	while (choice != 0)
			//	 {
			//		dispatch(choice);
			//		printMenu();
			//		choice = scan.nextInt();
			//	 }
				
			//}
			//if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y')
			//{
				withdraw();
			//}
		}
		
		//compares pins		
		
		if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y')
		{
				while(pin != getpin)
				{
					System.out.println("Please enter the Pin for this account. (####)");
					getpin = scan.nextInt();
						
					if(getpin == accountList[sortNum].getPin())
					{
						pin = getpin;
					}
						
					else
					{
						System.out.println("The PIN you entered is incorrect! Type 1 to Try Again, or type 0 to exit.");
						//int exit = 1;
						//exit = scan.nextInt();
						//if (exit == 0)
						//{
							//printMenu();
							//int choice = scan.nextInt();
							//while (choice != 0)
							// {
							//	dispatch(choice);
							//	printMenu();
							//	choice = scan.nextInt();
							// }
						withdraw();
					}
				}
			}
		
		
			//withdraws ammount
			System.out.println("Enter The ammount to be withdrawn (###.##):");
			ammount = scan.nextDouble();
			//checks to see if there is enough to withdraw ammount
			if(AT == 1)
			{
				test = (accountList[sortNum].getBalance() - ammount);
				if (test < 0)
				{
					System.out.println("Sorry you do not have enough to cover this withdrawl.");
					//printMenu();
					//int choice = scan.nextInt();
					//while (choice != 0)
					// {
					//	dispatch(choice);
					//	printMenu();
					//	choice = scan.nextInt();
					// }
					withdraw();
				}
				else
				{
					accountList[sortNum].withdraw(ammount);
					//prints new balance for the account
					 System.out.println("The new balance for this account is:");
					System.out.println(accountList[sortNum].getBalance());
				}
			}
			
			if(AT == 2)
			{
				test = (accountList[sortNum].getBalance() - ammount - 3);
				if (test < 0)
				{
					System.out.println("Sorry but This account does not have enough money to cover that withdrawl.");
					//printMenu();
					//int choice = scan.nextInt();
					//while (choice != 0)
					// {
					//	dispatch(choice);
					//	printMenu();
					//	choice = scan.nextInt();
					withdraw();
					// }
				}
				else
				{
					accountList[sortNum].withdraw(ammount);
					//prints new balance for the account
					 System.out.println("The new balance for this account is:");
					System.out.println(accountList[sortNum].getBalance());
				}
			}
	}
	
	//**********************************************************
	//Deposit a certain amount into an account.
	//*********************************************************
	public static void deposit()
	{
		int sortNum = 0;
		int checking = 0;
		int pin = 0;
		int getpin = 1;
		int AT = 5;
		double ammount = 0;
		String cont = "yes";
		double test = 0;
		int realAccount = 0;
		
		//collects data for new account
		System.out.println("Please enter the customer's account number. (##)");
		checking = scan.nextInt();
			
		//looks for all info related to the account number
		for(int i = 0; i < sortList.length; i++)
		{
			if (checking == sortList[i])
			{
				sortNum = i;
				realAccount = 1;
			}
		}
		
		//makes sure account exist
		if(realAccount == 0)
		{
			System.out.println("The account number you entered does not exist");
			printMenu();
			int choice = scan.nextInt();
			while (choice != 0)
			 {
				dispatch(choice);
				printMenu();
				choice = scan.nextInt();
			 }
		}
		
		//determines type of account
				AT = accountList[sortNum].getAccountType();
				System.out.println(AT);
		
		//System.out.println(AT);
		
		//Informs condition		
		if(AT == 3)
		{
			
			System.out.println("Sorry you can not deposit money into a loan. \n Please pay twards the loan or create a new loan in the menu. \n"
					+ "Would you like to try a different account #? Y or N?");
			cont = scan.nextLine();
			cont = scan.nextLine();
			if (cont.charAt(0) == 'N' || cont.charAt(0) == 'n')
			{
				printMenu();
				int choice = scan.nextInt();
				while (choice != 0)
				 {
					dispatch(choice);
					printMenu();
					choice = scan.nextInt();
				 }
			}
			if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y')
			{
				deposit();
			}
		}
		
			
			//deposits ammount
			System.out.println("Enter The ammount to be deposited (###.##):");
			ammount = scan.nextDouble();
			accountList[sortNum].deposit(ammount);
			//prints new balance for the account
			System.out.println("The new balance for this account is:");
			System.out.println(accountList[sortNum].getBalance());
		
	}	

	//*****************************************
	//pay towards a loan
	//*****************************************
	public static void payLoan()
	{
		int sortNum = 0;
		int checking = 0;
		int pin = 0;
		int getpin = 1;
		int AT = 5;
		double ammount = 0;
		String cont = "yes";
		double test = 0;
		int realAccount = 0;
		
		//collects data for new account
		System.out.println("Please enter the customer's account number. (##)");
		checking = scan.nextInt();
			
		//looks for all info related to the account number
		for(int i = 0; i < sortList.length; i++)
		{
			if (checking == sortList[i])
			{
				sortNum = i;
				realAccount = 1;
			}
		}
		
		//makes sure account exist
		if(realAccount == 0)
		{
			System.out.println("The account number you entered does not exist");
			printMenu();
			int choice = scan.nextInt();
			while (choice != 0)
			 {
				dispatch(choice);
				printMenu();
				choice = scan.nextInt();
			 }
		}
		
		//determines type of account
		AT = accountList[sortNum].getAccountType();
		System.out.println(AT);
		
		//Informs condition
		if(AT == 1)
		{
			
			System.out.println("Sorry you can not pay into a Checking Account. \n  Would you like to try a different account #? Y or N?");
			cont = scan.nextLine();
			cont = scan.nextLine();
			if (cont.charAt(0) == 'N' || cont.charAt(0) == 'n')
			{
				printMenu();
				int choice = scan.nextInt();
				while (choice != 0)
				 {
					dispatch(choice);
					printMenu();
					choice = scan.nextInt();
				 }
				
			}
			if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y')
			{
				payLoan();
			}
		}
		if(AT == 2)
		{
			
			System.out.println("Sorry you can not pay into a Savings Account. \n  Would you like to try a different account #? Y or N?");
			cont = scan.nextLine();
			cont = scan.nextLine();
			if (cont.charAt(0) == 'N' || cont.charAt(0) == 'n')
			{
				printMenu();
				int choice = scan.nextInt();
				while (choice != 0)
				 {
					dispatch(choice);
					printMenu();
					choice = scan.nextInt();
				 }
				
			}
			if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y')
			{
				payLoan();
			}
		}
		
		//withdraws amount and deposits any over payment into checking
		System.out.println("Enter The ammount to be withdrawn (###.##):");
		ammount = scan.nextDouble();
		test = (accountList[sortNum].getBalance() - ammount );
		if (test < 0)
		{
			ammount = (accountList[sortNum].getBalance() - ammount);
			//finds corresponding checking account
			System.out.println(sortNum);
			int checkingNum = ((Loans) accountList[sortNum]).getCheckingAccountNumber();
			int sortCheckingNum = 0;
			//finds corresponding list number for the checking number
			for(int i = 0; i < sortList.length; i++)
			{
				if (checkingNum == sortList[i])
				{
					sortCheckingNum = i;
				}
			}
			
			accountList[sortCheckingNum].deposit(-ammount);
			ammount = accountList[sortNum].getBalance();
			accountList[sortNum].withdraw(ammount);
			
			//prints new balance for the checking account
			 System.out.println("The new balance for the attached checking account is now:");
			System.out.println(accountList[sortCheckingNum].getBalance());
		}
		
		else
		{
			accountList[sortNum].withdraw(ammount);
		}
		
		//prints new balance for the account
		 System.out.println("The new balance for this loan account is:");
		System.out.println(accountList[sortNum].getBalance());
	}
	
}

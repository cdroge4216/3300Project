package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.IllegalFormatException;
import java.util.Scanner;

import application.Account;
import application.Checking;
import application.DeletedAccount;
import application.Employee;
import application.Loans;
import application.MainFXApp;
import application.Savings;
import application.TellerMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController 
{
	private MainFXApp main;
	
	boolean PINTruth = false;
	static boolean CorrectPIN = false;
	static boolean CorrectPassCode = false;
	static boolean FirstEntrance = true;
	boolean newAccountError = true;
	boolean booleanCheckingBalance = false;
	boolean booleanSavingsBalance = false;
	boolean booleanPIN = false;
	boolean booleanSSN = false;
	
	static int accountNumber, pin, pinStringHiddenLength, employeeID, passCode, checkingBalance, savingsBalance,
		socialSecurityNumber, checkingAccountNumber;
	static int checkingNumber = 999;
	static int [] sortList = new int[0];
	
	static int [] employeeSortList = new int[0];
	
	static String accountNumberString, pinString, name, address, memberDate, dateOfBirth; 
	static String pinStringHidden = " ";
	
	static String dateStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	static String lastDate = null;
	
	static Account[] accountList = new Account[0];
	static Employee[] employeeList = new Employee[0];

	static Scanner scan = new Scanner(System.in);
	
	Stage stage;
	Scene scene;
	Parent root;

	
	//MainFront
	@FXML Button ATMButton, TellerButton;
	
	//TellerLogIn
	@FXML Button LogInButton;
	@FXML Label TellerLogInUpdate;
	@FXML TextField EmployeeIDField;
	@FXML PasswordField PassCodeField;
	
	//TellerChoice
	@FXML Button TellerWithdrawButton, DepositButton, NewSavingsButton, NewCheckingButton, NewLoanButton, DeleteButton, LogOutButton, TellerChoiceView;
	
	//TellerNewChecking
	@FXML Button NewCheckingBackButton, NewCheckingEnterButton;
	@FXML TextField NewCheckingName, NewCheckingDOB, NewCheckingSCB, NewCheckingSSB, NewCheckingAddress, NewCheckingPIN, NewCheckingSSN;
	@FXML Label NewCheckingNameError, NewCheckingDOBError, NewCheckingSCBError, NewCheckingSSBError, NewCheckingAddressError, NewCheckingPINError, NewCheckingSSNError;
	
	//TellerNewCheckingConfirm
	@FXML Button NewCheckingConfirmCancel, NewCheckingConfirm;
	@FXML Label CheckingConfirmAccountNumberSavings, CheckingConfirmAccountNumberChecking, CheckingConfirmBalanceSavings, CheckingConfirmBalanceChecking;
	@FXML Label CheckingConfirmName, CheckingConfirmSSN, CheckingConfirmDOB, CheckingConfirmAddress, CheckingConfirmMemberDate, CheckingConfirmPIN;
	
	//TellerNewSavings
	@FXML Button NewSavingsBackButton, NewSavingsEnterButton;
	@FXML TextField NewSavingsSSB, NewSavingsCAN;
	@FXML Label NewSavingsSSBError, NewSavingsCANError;
	
	//TellerNewSavingsConfirm
	@FXML Button NewSavingsConfirmCancel, NewSavingsConfirm;
	@FXML Label SavingsConfirmAccountNumber, SavingsConfirmBalance;
	@FXML Label SavingsConfirmName, SavingsConfirmSSN, SavingsConfirmDOB, SavingsConfirmAddress, SavingsConfirmMemberDate, SavingsConfirmPIN;
	
	//ATMEntance
	@FXML Label AccountNumberText, PINText;
	@FXML Button NextButton, ClearButton, Num1, Num2, Num3, Num4, Num5, Num6, Num7, Num8, Num9; 
	@FXML AnchorPane Entrance;
	
	//ATMFront
	@FXML Label AccountNumberFront;
	@FXML Button WithdrawButton, DBBalance, CancelButton;
	
	//***********************************************************************************************************
	//***********************************************************************************************************
	//Main methods
	//***********************************************************************************************************
	//***********************************************************************************************************
	
	//used to connect the main class with the controller
	public void setMain(MainFXApp mainFXApp)
	{
		main=mainFXApp;
	}
	
	//a class that is used to import all data
	public void in() throws IOException
	{
		if (FirstEntrance == true)
		{
			System.out.println(dateStamp);
			FirstEntrance = false;
			
			reader();
		}
		
	}
	
	//***********************************************************************************************************
	//***********************************************************************************************************
	//GUI Related methods
	//***********************************************************************************************************
	//***********************************************************************************************************
	
	//***********************************************************************************************************
	//MainFront
	//***********************************************************************************************************
	
	public void ClickTeller(ActionEvent event) throws Exception
	{
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerLogIn.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickATM(ActionEvent event) throws Exception
	{
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/ATMEntrance.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
	}
	
	//***********************************************************************************************************
	//TellerLogIn
	//***********************************************************************************************************
	public void TellerLogInIn() throws IOException
	{
		if (FirstEntrance == true)
		{
			System.out.println(dateStamp);
			FirstEntrance = false;
			
			EmployeeReader();
		}
	}
	
	public void ClickLogIn(ActionEvent event) throws Exception
	{
		employeeID = Integer.parseInt(EmployeeIDField.getText());
		passCode = Integer.parseInt(PassCodeField.getText());
		System.out.println(employeeID);
		System.out.println(passCode);
		
		//CorrectPassCode = true;
		checkPasscode(employeeID, passCode);
		//!!DIAZ!! CHECK PASSCODE METHOD HERE. Return to CorrectPassCode true or false
		
		if (CorrectPassCode == true)
			{
				stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

				root = FXMLLoader.load(getClass().getResource("/view/TellerChoice.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				employeeID = 0;
				passCode = 0;
				CorrectPassCode = false;
				FirstEntrance = true;
			}
	}

	//***********************************************************************************************************
	//TellerChoice
	//***********************************************************************************************************
	
	public void ClickNewChecking(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerNewChecking.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickNewSavings(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerNewSavings.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickNewLoan(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerNewLoan.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickDelete(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerDelete.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickTellerWithdraw(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerWithdraw.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickDeposit(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerDeposit.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void ClickLogOut(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerLogIn.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void TellerChoiceClickView(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerView.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	//***********************************************************************************************************
	//TellerNewChecking
	//***********************************************************************************************************
	
	public void NewCheckingClickBack(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerChoice.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void NewCheckingClickEnter(ActionEvent event) throws Exception
	{
		//inputs user entered data
		name = NewCheckingName.getText();
		address = NewCheckingAddress.getText();
		dateOfBirth = NewCheckingDOB.getText();
		
		//inputs current date
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		memberDate = timeStamp;

		//inputs and error Checks User Data
		//PIN
		try
		{
			booleanPIN = false;
			NewCheckingPINError.setText(" " );
			pin = Integer.parseInt(NewCheckingPIN.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewCheckingPINError.setText("Please enter the pin using the correct format: ####" );
			booleanPIN = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewCheckingPINError.setText("Please enter the pin using the correct format: ####" );
			booleanPIN = true;
		}
		finally
		{
			
		}
		if(pin > 9999 || pin < 0)
		{
			NewCheckingPINError.setText("Please enter the pin using the correct format: ####" );
			booleanPIN = true;
		}
		
		//Checking Balance
		try
		{
			booleanCheckingBalance = false;
			NewCheckingSCBError.setText(" " );
			checkingBalance = Integer.parseInt(NewCheckingSCB.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewCheckingSCBError.setText("Please enter the balance using the correct format: ##.##" );
			booleanCheckingBalance = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewCheckingSCBError.setText("Please enter the balance using the correct format: ##.##" );
			booleanCheckingBalance = true;
		}
		finally
		{
			
		}
		
		//Savings Balance
		try
		{
			booleanSavingsBalance = false;
			NewCheckingSSBError.setText(" " );
			savingsBalance = Integer.parseInt(NewCheckingSSB.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewCheckingSSBError.setText("Please enter the balance using the correct format: ##.##" );
			booleanSavingsBalance = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewCheckingSSBError.setText("Please enter the balance using the correct format: ##.##" );
			booleanSavingsBalance = true;
		}
		finally
		{
			
		}
		
		//Social Security
		try
		{
			booleanSSN = false;
			NewCheckingSSBError.setText(" " );
			socialSecurityNumber = Integer.parseInt(NewCheckingSSN.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewCheckingSSNError.setText("Please enter the SSN using the correct format: #########" );
			booleanSSN = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewCheckingSSNError.setText("Please enter the SSN using the correct format: #########" );
			booleanSSN = true;
		}
		
		finally
		{
			
		}
		
		if (socialSecurityNumber < 100000000 || socialSecurityNumber > 999999999)
		{
			booleanSSN = true;
			NewCheckingSSNError.setText("Please enter the SSN using the correct format: #########" );
		}
		
		if(booleanSSN == false && booleanCheckingBalance == false && booleanSavingsBalance == false && booleanSSN == false 
				&& booleanPIN == false)
		{
			newAccountError = false;
		}
					
			
		if (newAccountError == false)
		{
			for(int i = 0; i < accountList.length; i++)
			{
				System.out.println(accountList[i].smallString());
			}
			writer();
			
			
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	
			root = FXMLLoader.load(getClass().getResource("/view/TellerNewCheckingConfirm.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			FirstEntrance = true;
		}
	}
	
	//***********************************************************************************************************
	//TellerNewCheckingConfirm
	//***********************************************************************************************************
	
	public void TellerNewCheckingConfirmInitial() throws IOException
	{
		if (FirstEntrance == true)
		{
			in();
		
			System.out.println(savingsBalance);
		
			CheckingConfirmAccountNumberSavings.setText(Integer.toString(Account.staticGetnumberAccounts() + 11));
			CheckingConfirmAccountNumberChecking.setText(Integer.toString(Account.staticGetnumberAccounts() + 10)); 
			CheckingConfirmBalanceSavings.setText(Integer.toString(savingsBalance));
			CheckingConfirmBalanceChecking.setText(Integer.toString(checkingBalance));
			CheckingConfirmName.setText(name);
			CheckingConfirmSSN.setText(Integer.toString(socialSecurityNumber));
			CheckingConfirmDOB.setText(dateOfBirth);
			CheckingConfirmAddress.setText(address);
			CheckingConfirmMemberDate.setText(memberDate);
			CheckingConfirmPIN.setText(Integer.toString(pin));
		}
	}
	
	public void NewCheckingClickConfirm(ActionEvent event) throws Exception
	{
		
		//creates new accounts
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
		
		//creates new checking account
		accountList[(accountList.length-2)] = new Checking(name, checkingBalance, Integer.toString(socialSecurityNumber), 
				address, dateOfBirth, memberDate, pin, 1, accountNumber, 1, 0);
		sortList[(sortList.length-2)] = accountList[(accountList.length-2)].getnumberAccounts();
		int AN = accountList[(accountList.length-2)].getnumberAccounts();
		
		//creates new Savings
		accountList[(accountList.length-1)] = new Savings(name, savingsBalance, Integer.toString(socialSecurityNumber), 
				address, dateOfBirth, memberDate, pin, 2, (AN + 1), AN, 1);
		sortList[(sortList.length-1)] = (AN + 1);
		
		sort();
		
		System.out.println("Below is the account infor for each account:");
		System.out.println(accountList[(accountList.length-2)].toString());
		System.out.println(accountList[(accountList.length-1)].toString());
		
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerChoice.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void NewCheckingClickConfirmCancel(ActionEvent event) throws Exception
	{
		writer();
		System.out.println("here");
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerNewChecking.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	//***********************************************************************************************************
	//TellerNewSavings
	//***********************************************************************************************************
	
	public void NewSavingsClickBack(ActionEvent event) throws Exception
	{
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerChoice.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void NewSavingsClickEnter(ActionEvent event) throws Exception
	{
		
		newAccountError = false;
				
		//inputs and error Checks User Data
		//checkingAccountNumber
		try
		{
			NewSavingsCANError.setText(" " );
			checkingAccountNumber = Integer.parseInt(NewSavingsCAN.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewSavingsCANError.setText("Please enter the account number using the correct format: ##" );
			newAccountError = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewSavingsCANError.setText("Please enter the account number using the correct format: ##" );
			newAccountError = true;
		}
		finally
		{
			
		}
		
		//Savings Balance
		try
		{
			NewSavingsSSBError.setText(" " );
			savingsBalance = Integer.parseInt(NewSavingsSSB.getText());
		}
		catch(java.util.InputMismatchException e)
		{
			NewSavingsSSBError.setText("Please enter the balance using the correct format: ##.##" );
			newAccountError = true;
		}
		catch(java.lang.NumberFormatException e)
		{
			NewSavingsSSBError.setText("Please enter the balance using the correct format: ##.##" );
			newAccountError = true;
		}
		finally
		{
			
		}
					
			
		if (newAccountError == false)
		{
			for(int i = 0; i < accountList.length; i++)
			{
				System.out.println(accountList[i].smallString());
			}
			writer();
			
			
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	
			root = FXMLLoader.load(getClass().getResource("/view/TellerNewSavingsConfirm.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			FirstEntrance = true;
		}
	}
	
	//***********************************************************************************************************
	//TellerNewSavingsConfirm
	//***********************************************************************************************************
	
	public void TellerNewSavingsConfirmInitial() throws IOException
	{
		
		if (FirstEntrance == true)
		{
			in();
			checkingNumber = 999;
		//	System.out.println(checkingAccountNumber);
			
			//checks to see if checking account number is real
			//looks for all info related to the account number
			for(int i = 0; i < sortList.length; i++)
			{
				//System.out.println(sortList[i]);
				if (checkingAccountNumber == sortList[i])
				{
					checkingNumber = i;
				}
				//System.out.println(checkingNumber);
			}
			
			if (checkingNumber != 999)
			{
				//collects info from checking
				name = accountList[checkingNumber].getName();
				socialSecurityNumber = Integer.parseInt(accountList[checkingNumber].getSSN()); 
				address = accountList[checkingNumber].getAddress();
				dateOfBirth = accountList[checkingNumber].getDOB(); 
				memberDate = accountList[checkingNumber].getMemberSince();
				pin = accountList[checkingNumber].getPin();
				int accounts = ((Checking)accountList[checkingNumber]).getNumSavingsAccounts();
				
				SavingsConfirmAccountNumber.setText(Integer.toString(checkingAccountNumber + accounts + 1));
				SavingsConfirmBalance.setText(Integer.toString(savingsBalance));
				SavingsConfirmName.setText(name);
				SavingsConfirmSSN.setText(Integer.toString(socialSecurityNumber));
				SavingsConfirmDOB.setText(dateOfBirth);
				SavingsConfirmAddress.setText(address);
				SavingsConfirmMemberDate.setText(memberDate);
				SavingsConfirmPIN.setText(Integer.toString(pin));
			}
			
			else 
			{
				SavingsConfirmAccountNumber.setText("The Checking account number does not exist.");
				SavingsConfirmBalance.setText("Please hit \"Cancel\" and try again.");
			}
		}
	}
	
	public void NewSavingsClickConfirm(ActionEvent event) throws Exception
	{
		
		//creates new accounts
		//makes room for new account
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
		
		
		//creates new Savings
		accountList[(accountList.length-1)] = new Savings(name, 
				savingsBalance, Integer.toString(socialSecurityNumber), address, dateOfBirth, memberDate, pin, 2,
				(checkingAccountNumber + (((Checking)accountList[checkingNumber]).getNumSavingsAccounts()) + 1), 
				checkingAccountNumber, ((((Checking)accountList[checkingNumber]).getNumSavingsAccounts()) + 1));
		
		sortList[(accountList.length-1)] = 
				(checkingAccountNumber + (((Checking)accountList[checkingNumber]).getNumSavingsAccounts()) + 1); 
		
		//Updates attached checking account to show the proper amount of savings accounts
		((Checking) accountList[checkingNumber]).setNumSavingsAccounts(((Checking) accountList[checkingNumber]).
				getNumSavingsAccounts() + 1);
		
		//Prints Info for the new account
		System.out.println("Below is the account infor for the new account:");
		System.out.println(accountList[(accountList.length-1)].toString());
		
		sort();
		
		writer();
		
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerChoice.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	public void NewSavingsClickConfirmCancel(ActionEvent event) throws Exception
	{
		writer();
		System.out.println("here");
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("/view/TellerNewSavings.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		FirstEntrance = true;
	}
	
	//***********************************************************************************************************
	//ATMEntrance
	//***********************************************************************************************************
	
	public void Click1()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 1);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 1);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click2()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 2);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 2);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click3()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 3);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 3);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click4()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 4);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 4);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click5()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 5);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 5);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click6()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 6);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 6);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click7()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 7);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 7);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click8()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 8);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 8);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click9()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 9);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 9);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void Click0()
	{
		if (PINTruth == false)
		{
			accountNumber = ((accountNumber * 10) + 0);
			accountNumberString = Integer.toString(accountNumber);
			AccountNumberText.setText(accountNumberString);
		}
		
		else
		{
			pin = ((pin * 10) + 0);
			pinString = Integer.toString(pin);
			
			pinStringHiddenLength = pinStringHidden.length();
			for(int i = pinStringHidden.length(); i <= pinStringHiddenLength; i++)
			{
				pinStringHidden = pinStringHidden + "*"; 
			}
			
			PINText.setText(pinStringHidden);
		}
	}
	
	public void ClickNext(ActionEvent event) throws Exception
	{
		if (PINTruth == false)
		{
			PINTruth = true;
		}
		
		else if (PINTruth == true && CorrectPIN == false)
		{
			//!!DIAZ!! CHECK PIN METHOD HERE. Return to CorrectPIN true or false
			CheckPIN(pin, accountNumber);
			//CorrectPIN = true;
			System.out.println(CorrectPIN);
		}
		
		if (CorrectPIN == true)
		{
			writer();
			FirstEntrance = true;
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

			root = FXMLLoader.load(getClass().getResource("/view/ATMFront.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
		}
		
	}
	
	public void ClickClear()
	{
		accountNumber = 0;
		accountNumberString = "";
		pin = 0;
		pinString = "";
		pinStringHidden = "";
		PINText.setText(" ");
		AccountNumberText.setText(" ");
		PINTruth = false;
	}
	
	//***********************************************************************************************************
	//ATMFront
	//***********************************************************************************************************
	public void ClickCancel()
	{
		
	}
	
	public void ClickDB()
	{
		
	}
	
	public void ClickWithdraw()
	{
		
	}
	
	//***********************************************************************************************************
	//***********************************************************************************************************
	//DATA Related methods
	//***********************************************************************************************************
	//***********************************************************************************************************
	
	//****************************************
	//reads in saved file
	//****************************************
	public static void reader() throws IOException
	{
		Account.staticSetNumberAccounts(0);
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
		
		accountList = new Account[0];
		
		sortList = new int[0];
			
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
				System.out.println("AN:" + accountNumber);
				numSavingsAccounts = scan.nextInt();
				//System.out.println("numSavings" + numSavingsAccounts);
				numLoansAccounts = scan.nextInt();
				//System.out.println("numLoans" + numLoansAccounts);
				
					
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
				//System.out.println("AN:" + accountNumber);
				checkingaccountNumber = scan.nextInt();
				//System.out.println("CN" + checkingaccountNumber);
				numSavingsAccounts = scan.nextInt();
				//System.out.println("numSA:" + numSavingsAccounts);
					
					
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
				System.out.println(accountList[(accountList.length-1)].toString());
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
				System.out.println(accountList[(accountList.length-1)].toString());
				
				sort();
			}
		}
			
	}		
	//****************************************
	//Writes file to save accounts
	//****************************************
	public static void writer()
	{
		sort();
		for(int i = 0; i < accountList.length; i++)
		{
			System.out.println(accountList[i].smallString());
		}
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
				//System.out.println(i);
				//System.out.println("AN" + accountList[i].getAccountNumber());
				//determines type of account
				if(accountList[i].getAccountType() == 1)
				{
					AT = 1;
				}
					
				if(accountList[i].getAccountType() == 2)
				{
					AT = 2;
				}
			
				if(accountList[i].getAccountType() == 3)
				{
					AT = 3;
				}
			
				if(accountList[i].getAccountType() == 4)
				{
					AT = 4;
				}
				
				//System.out.println(AT);
					
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
			accountList[index] = accountList[min];
			sortList[min] = temp;
			accountList[min] = TEMP;
		}
	}
	//*******************************
	//Compares the user enter pin to check if pin is correct or not
	//*******************************
	public static boolean CheckPIN(int p, int a)
	{
		int checkPin = p;
		int checkAccountNumber = a;
		int correctPin;
		
		for(int i = 0; i < sortList.length; i++)
		{
			if (checkAccountNumber == sortList[i])
			{
				//System.out.println();
				checkingNumber = i;
			}
		}
		
		correctPin = accountList[checkingNumber].getPin();
		if (checkPin == correctPin)
		{
			return CorrectPIN = true;
		}
		
		else
		{
			return CorrectPIN = false;
		}
		
	}
	
	//********************************************
	//Reads in employee info
	//********************************************
	public static void EmployeeReader() throws IOException
	{
		int IdNumber = 100, Passcode,	EmployeeType;
		employeeList = new Employee[0];
		employeeSortList = new int[0];
		boolean contID = true;
		
		
		Scanner scan = new Scanner(new File("employees.dat"));
		
		//while(contID == true)
		//{
			IdNumber = scan.nextInt(); 
			System.out.println("ID" + IdNumber);
			IdNumber = scan.nextInt();
			if (IdNumber == 0)
					{
						contID = false;
					}
			
			Passcode = scan.nextInt();
			System.out.println("Passcode" + Passcode);
			
			EmployeeType = scan.nextInt();
			System.out.println("Type" + EmployeeType);
			
			//makes room for new account
			Employee[] temp = new Employee[employeeList.length+1];
				
			for(int i = 0; i < employeeList.length; i++)
			{
				temp[i] = employeeList[i];
			}
				
			employeeList = new Employee [temp.length];
				
			for(int i = 0; i < employeeList.length; i++)
			{
				employeeList[i] = temp[i];
			}
				
			int[] tempS = new int[employeeSortList.length+1];
				
			for(int i = 0; i < employeeSortList.length; i++)
			{
				tempS[i] = employeeSortList[i];
			}
				
			employeeSortList = new int[tempS.length];
				
			for(int i = 0; i < employeeSortList.length; i++)
			{
				employeeSortList[i] = tempS[i];
			}
			
			employeeList[employeeList.length - 1] = new Employee(IdNumber, Passcode, EmployeeType);
			
			employeeSortList[employeeList.length - 1] = employeeList[employeeList.length - 1].getIdNumber();
			
			System.out.println("ID" + employeeList[employeeList.length - 1].getIdNumber());
			System.out.println("Passcode" + employeeList[employeeList.length - 1].getPasscode());
			System.out.println("Type" + employeeList[employeeList.length - 1].getEmployeeType());
		//}
	}
	
	//*******************************
	//Compares the user enter passcode to check if passcode is correct or not
	//*******************************
	public static boolean checkPasscode(int p, int a)
	{
		int checkPass = a;
		int checkIdNumber = p;
		int correctPass;
		int ID = 100;
		
		for(int i = 0; i < sortList.length; i++)
		{
			System.out.println(checkIdNumber);
			System.out.println(employeeSortList[i]);
			if (checkIdNumber == employeeSortList[i])
			{
				
				ID = i;
			}
		}
		
		correctPass = employeeList[ID].getPasscode();
		if (checkPass == correctPass)
		{
			return CorrectPassCode = true;
		}
		
		else
		{
			return CorrectPassCode = false;
		}
		
	}
	
}

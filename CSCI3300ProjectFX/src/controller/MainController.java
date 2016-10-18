package controller;

import application.MainFXApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController 
{
	//composition,
	//Is called by the main application to give a reference back to itself.
	private MainFXApp main;
	//used to connect the main class with the controller
	
	@FXML TextField accountNumberField;
	@FXML PasswordField pinField;
	@FXML Button submitButton; 
	
	public void setMain(MainFXApp mainIn)
	{
		main=mainIn;
	}
	
	public void login()
	{
		String pin = pinField.getText();
		String accountNumber = accountNumberField.getText();
		
	}
}

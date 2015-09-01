package com.jmpp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jmpp.message.AuthMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController extends GUIController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Chat View is now loaded");		
	}

    @FXML private Text actiontarget;
    @FXML private TextField displayName;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) 
    {
        String username = displayName.getText();
		System.out.println("Authenticating as " + username);
		
		defaultController.getEndpoint().sendMessage(AuthMessage.getObject(username).toString());        
    }
}

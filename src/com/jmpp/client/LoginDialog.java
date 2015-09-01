package com.jmpp.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginDialog 
{		
	public static String display()
	{				
		Stage window = new Stage();		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Login");
		window.setMinWidth(250);
		
		Label usernameLabel = new Label("Username");
		TextField usernameField = new TextField();
		
		//Label passwordLabel = new Label("Password");
		//TextField passwordField = new TextField();
		
		Button loginButton = new Button("Login");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				window.close();				
			}
		});		
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				window.close();				
			}
		});
		
	    GridPane gridpane = new GridPane();
	    GridPane.setRowIndex(usernameLabel, 1);
	    GridPane.setColumnIndex(usernameLabel, 1);	    
	    GridPane.setRowIndex(usernameField, 1);
	    GridPane.setColumnIndex(usernameField, 2);
	    
	    //GridPane.setRowIndex(passwordField, 2);
	    //GridPane.setColumnIndex(passwordField, 2);
	    //GridPane.setRowIndex(passwordLabel, 2);
	    //GridPane.setColumnIndex(passwordLabel, 1);

	    GridPane.setRowIndex(cancelButton, 3);
	    GridPane.setColumnIndex(cancelButton, 1);
	    GridPane.setRowIndex(loginButton, 3);
	    GridPane.setColumnIndex(loginButton, 3);

	    gridpane.getChildren().addAll(	usernameLabel, 
							    		//passwordLabel, 
							    		usernameField, 
							    		//passwordField, 
							    		loginButton, 
							    		cancelButton);
		Scene scene = new Scene(gridpane);
		window.setScene(scene);
		window.showAndWait();
		
		return usernameField.getText();
	}
}

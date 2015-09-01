package com.jmpp.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatClientGUI extends Application
{		
	public void start(Stage stage) throws Exception 
	{		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jmpp/controllers/default.fxml"));
		Parent root = loader.load();
		//DefaultController defaultController = (DefaultController) loader.getController();
		Scene scene = new Scene(root, 640,480);
		
		stage.setTitle("JMPP Chat Client");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}	
}

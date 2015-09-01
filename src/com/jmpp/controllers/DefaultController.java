package com.jmpp.controllers;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.jmpp.client.ChatClientEndpoint;
import com.jmpp.client.ChatClientEndpoint.MessageHandler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class DefaultController implements Initializable, MessageHandler
{
	static final String PUBLIC_ROOM = "0713e687-7706-4214-b1b8-c70dadfc639a";
	
    private ChatClientEndpoint endpoint;
    
	private LoginController loginController;
	private ChatController chatController;

    @FXML private BorderPane borderPane;
    
    public ChatClientEndpoint getEndpoint()
    {
    	return this.endpoint;
    }
        
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Default View is now loaded");	

		try
		{			
			this.endpoint = new ChatClientEndpoint(new URI("ws://localhost:8080/JMPPServer/ws/chat"));
			this.endpoint.addMessageHandler(this);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
			Parent root = loader.load();
			this.loginController = (LoginController) loader.getController();
			this.loginController.setDefaultController(this);
			borderPane.setCenter(root);   
		}
		catch(URISyntaxException e)
		{
			System.err.println("Error Connecting to the Server");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.err.println("Error Loading the Login Screen");
			e.printStackTrace();
		}
	}
    
	@Override
	public void handleMessage(String message) 
	{
		System.out.println("Received: " + message);

		try
		{		
		   	JsonReader jsonReader = Json.createReader(new StringReader(message));
		   	JsonObject object = jsonReader.readObject();
		   	 
			switch(object.get("type").toString())
			{
				case "auth_ok":
					
					DefaultController controller = this;
					Platform.runLater(new Runnable() {
				        @Override
				        public void run() {
							System.out.println("Loading Chat Controller");
							try 
							{
								// Chat Tab Pane
								FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
								Parent root = loader.load();
								chatController = (ChatController) loader.getController();
								chatController.setDefaultController(controller);
								borderPane.setCenter(root);
								
								// Chat Tab
								FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("chattab.fxml"));
								Parent tabVbox = tabLoader.load();
								ChatTabController tabController = (ChatTabController) tabLoader.getController();
								tabController.setDefaultController(controller);
								tabController.setIdentifier(PUBLIC_ROOM);
								Tab tab = new Tab();
								tabController.setTab(tab);
								tab.setText("Public");
								tab.setContent(tabVbox);
								chatController.addChatTab(tabController);
								
							} catch (IOException e) {
								e.printStackTrace();
							}							
				        }
				   });					
					
					break;
				
				case "room_message":
				case "user_message":
					if(chatController != null)
					{
						chatController.addChatMessage(object);
					}
					break;
					
				case "user_connected":
				case "user_disconnected":
					if(chatController != null)
					{
						chatController.addUserStatusMessage(object);
					}
					break;
			
				default:
					System.out.println("Invalid Message Type: " + object.get("type").toString());
			}        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    @FXML protected void handleExitMenu(ActionEvent event) 
    {
    	System.exit(0);
    }
    
    @FXML protected void handleSettingsMenu(ActionEvent event) 
    {

    }
    
    @FXML protected void handleAboutMenu(ActionEvent event) 
    {

    }
}

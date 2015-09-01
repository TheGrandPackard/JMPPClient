package com.jmpp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.json.JsonObject;

import com.jmpp.message.ChatMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatTabController extends GUIController implements Initializable 
{	    
    @FXML private TextArea chatLog;
    @FXML private TextField messageField;
    
    private Tab tab;
    private String identifier;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Chat Tab View is now loaded");		
	}
	
	public void setTab(Tab tab)
	{
		this.tab = tab;
	}
	
	public Tab getTab()
	{
		return this.tab;
	}
	
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	public String getIdentifier()
	{
		return this.identifier;
	}
    
    @FXML protected void handleSendButtonAction(ActionEvent event) 
    {
    	this.sendMessage();
    }
    
    @FXML protected void onEnter() 
    {
    	this.sendMessage();
    }
    
    private void sendMessage()
    {
        String message = messageField.getText();
		System.out.println("Sending Message : " + ChatMessage.getObject(message).toString());
				
       defaultController.getEndpoint().sendMessage(ChatMessage.getObject(message).toString());
       messageField.setText("");
    }
    
    public void addChatMessage(JsonObject object)
    {    	
    	chatLog.appendText(object.getString("sender") + " : " + object.getString("chat_message") + "\r\n");
    }
    
    public void addUserStatusMessage(JsonObject object)
    {
    	switch(object.getString("type"))
    	{
			case "user_connected":
		    	chatLog.appendText("User " + object.getString("user") + " Connected.\r\n");				
				break;
			case "user_disconnected":
		    	chatLog.appendText("User " + object.getString("user") + " Disconnected.\r\n");			
				break;
    	}
    }
}

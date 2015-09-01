package com.jmpp.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.json.JsonObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class ChatController extends GUIController implements Initializable 
{		
    @FXML private TabPane tabPane;    
    
    HashMap<String, ChatTabController> controllers;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Chat View is now loaded");		
		controllers = new HashMap<String, ChatTabController>();
	}
	
	public void addChatTab(ChatTabController controller)
	{
		this.controllers.put(controller.getIdentifier(), controller);
		this.tabPane.getTabs().add(controller.getTab());
	}
    
    public void addChatMessage(JsonObject object)
    {   
    	String type = object.getString("type");
    	String identifier;
    	
    	if(type.equals("room_message"))
    		identifier = object.getString("room_identifier");
    	else
    		identifier = object.getString("user_identifer");
    	
    	ChatTabController controller = this.controllers.get(identifier);
    	
    	if(controller != null)
    		controller.addChatMessage(object);
    }
    
    public void addUserStatusMessage(JsonObject object)
    {
		String identifier = object.getString("room_identifier");		
		ChatTabController controller = this.controllers.get(identifier);
		
		if(controller != null)
			controller.addUserStatusMessage(object);
    }
}

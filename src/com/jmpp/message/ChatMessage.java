package com.jmpp.message;

import javax.json.Json;
import javax.json.JsonObject;

public class ChatMessage 
{
	public static JsonObject getObject(String message) 
	{
        return Json.createObjectBuilder()
						.add("type", "chat_message")
        				.add("chat_message", message)
        				.build();        				
    }
}

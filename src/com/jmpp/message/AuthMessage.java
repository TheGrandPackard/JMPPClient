package com.jmpp.message;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;

public class AuthMessage {
	
	public static JsonObject getObject(String username)
	{
		return Json.createObjectBuilder()
        		.add("type", "auth")
                .add("display_name", username)
                .add("identifier", UUID.randomUUID().toString())
                .build();
	}
}

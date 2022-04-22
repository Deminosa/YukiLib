package de.deminosa.discord;

import java.io.OutputStream;
import java.net.URL;

import com.google.gson.JsonObject;

import de.deminosa.discord.webhook.Webhook;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:45:26 # 22.04.2022
*
*/

public class DiscordHook extends Webhook{

	public DiscordHook(URL url) {
		super(url);
	}
	
	public void send(String s) {
		try {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("content", s);
			
			connect();
			addRequestProperty("Content-Type", "application/json");
			addRequestProperty("User-Agent", "Java-DiscordWebhook");
			setDoOutput(true);
			setRequestMethod("POST");
			
			OutputStream stream = getOpenConnection().getOutputStream();
			stream.write(jsonObject.toString().getBytes());
			stream.flush();
			stream.close();
			
			closeAllAndDisconnect();
		}catch (Exception e) {}
	}

}

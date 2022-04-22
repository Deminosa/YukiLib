package de.deminosa.discord.webhook;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:36:40 # 22.04.2022
*
*/

public abstract class Webhook {

	private final URL url;
	private HttpsURLConnection connection;
	
	public Webhook(URL url) {
		this.url = url;
	}
	
	public void connect() {
		try {
			connection = (HttpsURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addRequestProperty(String key, String value) {
		if(connection == null) return; 
		connection.addRequestProperty(key, value);
	}
	
	public void setDoOutput(boolean bool) {
		if(connection == null) return; 
		connection.setDoOutput(bool);
	}
	
	public void setDoInput(boolean bool) {
		if(connection == null) return; 
		connection.setDoInput(bool);
	}
	
	public void setRequestMethod(String method) {
		if(connection == null) return; 
		try {
			connection.setRequestMethod(method);
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}
	
	public void closeAllAndDisconnect() {
		try {
			connection.getInputStream().close();
			connection.getOutputStream().close();
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HttpsURLConnection getOpenConnection() {
		return connection;
	}
	
	public URL getUrl() {
		return url;
	}
}

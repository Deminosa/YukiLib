package de.deminosa.webinterface.utils;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.bukkit.Bukkit;

import com.sun.net.httpserver.HttpServer;

import de.deminosa.core.YukiLib;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:55:10 # 01.01.2020
*
*/

@SuppressWarnings("restriction")
public class WebServerManager {

	private HttpServer server;
	
	public WebServerManager(int port) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
		} catch (IOException e) {
			if(YukiLib.DEBUG) e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §cSomthing is wrong with starting the Server!");
			Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §cMessage: " + e.fillInStackTrace());
			Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §cFor the Error Message from Java, turn on the "
					+ "Debug mode!");
			Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §c=============================");
		}
	}
	
	
	public void start() {
		server.start();
	}
	
	public void stop() {
		server.stop(0);
	}
	
	public HttpServer getServer() {
		return server;
	}
	
}

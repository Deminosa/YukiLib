package de.deminosa.webinterface;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import de.deminosa.core.YukiLib;
import de.deminosa.core.annotaion.NotReady;
import de.deminosa.webinterface.api.QueryResponse;
import de.deminosa.webinterface.auth.SpigotPermissionAuth;
import de.deminosa.webinterface.responses.KickResponse;
import de.deminosa.webinterface.utils.WebFileManager;
import de.deminosa.webinterface.utils.WebServerManager;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:32:32 # 29.07.2021
*
*/

@NotReady(description = "This has lots of erros", 
version = "0.01", 
author = "Deminosa")
public class WebInterface {

	private static WebServerManager webServerManager;
	private static int port;
	private static WebInterface instance;
	private SpigotPermissionAuth auth;
	
	private final List<QueryResponse> queryResponse;
	
	public WebInterface() {
		instance = this;
		queryResponse = new ArrayList<>();
		
		addQueryResponse(new KickResponse());
		auth = new SpigotPermissionAuth();
	}
	
	public static YukiLib getInstace() {
		return YukiLib.get();
	}
	
	public void onEnable() {
		port = 20000;
		//		port = ThreadLocalRandom.current().nextInt(100)+20000;
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §a=============================");
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §aWebinterface is starting on port " + port);
		webServerManager = new WebServerManager(port);
		webServerManager.start();
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §a=============================");
		
		new WebFileManager();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §a=============================");
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §aStopping Webinterface...");
		webServerManager.stop();
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §aWebinterface is stopped!");
		Bukkit.getConsoleSender().sendMessage("§8[§c!!!§8] §a=============================");
	}
	
	public static WebServerManager getWebServerManager() {
		return webServerManager;
	}
	
	public static int getCurrentPort() {
		return port;
	}
	
	public void addQueryResponse(QueryResponse query) {
		if(!queryResponse.contains(query)) {
			queryResponse.add(query);
		}
	}

	public List<QueryResponse> getQueryResponse() {
		return queryResponse;
	}

	public static WebInterface get() {
		return instance;
	}
	
	public SpigotPermissionAuth getSpigotAuth() {
		return auth;
	}
	
}

package de.deminosa.webinterface.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.deminosa.core.YukiLib;
import de.deminosa.webinterface.WebInterface;
import de.deminosa.webinterface.api.QueryResponse;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	19:03:51 # 01.01.2020
 *
 */

@SuppressWarnings({ "restriction" })
public class WebFileManager implements HttpHandler{

	public WebFileManager() {
		load();
	}

	public void load() {
		File dir = new File(WebInterface.getInstace().getDataFolder() + "//webpages//");
		if(!dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}

		if(!dir.exists()) {
			dir.mkdirs();
		}

		File[] files = dir.listFiles();
		if(files != null) {
			for(int i = 0; i < files.length; i++) {
				if(!files[i].isDirectory()) {
					WebInterface.getWebServerManager().getServer()
							.createContext("/" + files[i].getName(), this);
				}
			}
		}

		WebInterface.getWebServerManager().getServer().createContext("/", this);
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String response = FileUtils.getFileContents(httpExchange.getRequestURI().getPath().toString());
		
		response = response.replace("%players%", Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		response = response.replace("%logs%", FileUtils.getLogContents());
		response = response.replace("%plugins%", FileUtils.getPlugins());
		response = response.replace("%players_names%", FileUtils.getPlayers());
		response = response.replace("%bukkit%", FileUtils.getBukkitInfo());
		response = response.replace("%login%", "");

		if(httpExchange.getRequestURI().getQuery() != null &&
				httpExchange.getRequestURI().getQuery() != "null") {
			if(!WebInterface.get().getQueryResponse().isEmpty()) {
				String queryResponse = httpExchange.getRequestURI().getQuery();
				System.out.println("[Webinterface] Get query response '"+queryResponse+"'");
				
				HashMap<String, String> map = new HashMap<>();
				String[] args = queryResponse.split("&");
				
				for(int i = 0; i < args.length; i++) {
					String[] raw = args[i].split("=");
					String key = raw[0];
					String value = raw[1];
					
					map.put(key, value);
				}
				
				for(QueryResponse qr : WebInterface.get().getQueryResponse()) {
					new BukkitRunnable() {
						@Override
						public void run() {
							qr.incomingResponse(map);
						}
					}.runTask(YukiLib.get());
				}

				String url = httpExchange.getRequestURI().getPath().toString().replace(queryResponse, "");
				httpExchange.getResponseHeaders().set("locaion", url);
			}
		}

		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream outputStream = httpExchange.getResponseBody();
		outputStream.write(response.getBytes());
		outputStream.close();
	}
	
}

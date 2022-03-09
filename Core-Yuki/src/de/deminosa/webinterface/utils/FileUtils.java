package de.deminosa.webinterface.utils;

import java.io.BufferedReader;
import java.io.FileReader;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.deminosa.webinterface.WebInterface;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	19:12:13 # 01.01.2020
 *
 */

@SuppressWarnings({ "resource"})
public class FileUtils {

	public static String getPlayers() {
		String s = "";
		for(Player players : Bukkit.getOnlinePlayers()) {
			s += players.getName() + " ("+players.getWorld().getName()+" at "
					+ players.getLocation().getBlockX()+ " "
					+ players.getLocation().getBlockY()+ " "
					+ players.getLocation().getBlockZ()+ ") "
					+ "<a href=\"?kick="+players.getName()+"\"><button>Kick</button></a>";
		}
		return s;
	}
	
	public static String getBukkitInfo() {
		String s = "";
		
		s += "<p><b>Version:</b> " + Bukkit.getBukkitVersion() + "</p>";
		s += "<p><b>IP:</b> " + Bukkit.getIp() + ":" + Bukkit.getPort() + "</p>";
		s += "<p><b>Online Mode:</b> " + (Bukkit.getOnlineMode() ? "ON" : "OFF") + "</p>";
		s += "<p><b>Allow Flight:</b> " + (Bukkit.getAllowFlight() ? "ON" : "OFF") + "</p>";
		
		return s;
	}
	
	public static String getPlugins() {
		String s = "";
		for(Plugin plugins : Bukkit.getPluginManager().getPlugins()) {
			String color = "";
			if(plugins.isEnabled()) {
				color = "0ED40B"; //GREEN
			}else {
				color = "DA4F4F"; //RED
			}
			
			s += "<li class=\"mdl-list__item\">"
						+ "<span class=\"mdl-list__item-primary-content\" style=\"color: #"+color+";\">"
							+ plugins.getName()
						+ "</span>"
					+ "</li>";
		}
		if(s.endsWith(", ")) {
			s = s.substring(0, s.length()-2);
		}
		return s;
	}
	
	public static String getFileContents(String filenname) {
		try {
			BufferedReader bufferedReader 
			= new BufferedReader(new FileReader(WebInterface.getInstace().getDataFolder() + "/webpages/" + filenname));
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			return stringBuilder.toString();
		}catch (Exception e) {
			return "<h1>ERROR</h1><p>Please check the URL!<br>"
					+ "<a href=\"/index.html\">Back to Main Page</a></p><hr />"
					+ "<p>" + e.fillInStackTrace() + "</p>";
		}
	}

	public static String getLogContents() {
		try {
			BufferedReader bufferedReader 
				= new BufferedReader(new FileReader(WebInterface.getInstace().getDataFolder() + "/../../logs/latest.log"));
			String end = "";
			String line = bufferedReader.readLine();
			while (line != null) {
				end = line + "<br>" + end;
				
				line = bufferedReader.readLine();
			}
			return end;
		}catch (Exception e) {
			return "<h1>ERROR</h1><p>Please check the URL!<br>"
					+ "<a href=\"/index.html\">Back to Main Page</a></p><hr />"
					+ "<p>" + e.fillInStackTrace() + "</p>";
		}
	}
}

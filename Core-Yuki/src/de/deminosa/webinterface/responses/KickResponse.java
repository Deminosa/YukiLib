package de.deminosa.webinterface.responses;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.deminosa.webinterface.api.QueryResponse;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	21:45:27 # 26.12.2021
 *
 */

public class KickResponse extends QueryResponse{

	@Override
	public void incomingResponse(String response) {
		if(response.startsWith("kick")) {
			String name = response.split("=")[1];

			Player player = Bukkit.getPlayer(name);
			if(player != null) {
				player.kickPlayer("�cKick from Server\n"
						+ "�cReson: �7Kick via Interface");	
			}else {
				System.out.println("Player '"+name+"' is not online!");
			}
		}
	}

}

package de.deminosa.core.manager.login;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	11:22:54 # 02.01.2022
*
*/

public class JoinQuitListener implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(event.getPlayer() == null) return;

		new User(event.getPlayer());
		
		if(checkUser(event.getPlayer())) {
			YukiLib.get().getManager().getUserLoader().forEach((u) -> {
				u.loaded(event, User.get(event.getPlayer()));
			});
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
	}
	
	
	private boolean checkUser(Player player) {
		User u = User.get(player);
		
		return u != null;
	}
	
}

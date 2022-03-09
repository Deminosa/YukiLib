package de.deminosa.core.manager.login;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;
import de.deminosa.webinterface.WebInterface;

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
		new User(event.getPlayer());
		
		if(checkUser(event.getPlayer())) {
			YukiLib.get().getManager().getUserLoader().forEach((u) -> {
				u.loaded(event, User.getUser(event.getPlayer()));
			});
		}
		
		webinterfaceRegister(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		webinterfaceRemover(event.getPlayer());
	}
	
	private void webinterfaceRegister(Player player) {
		if(player.isOp()) {
			String ip = player.getAddress().getAddress().getHostAddress();
			System.out.println("[Webinterface] Register auth for '"+ip+"'");
			
			WebInterface.get().getSpigotAuth().add(ip);
		}
	}
	
	private void webinterfaceRemover(Player player) {
		if(player.isOp()) {
			String ip = player.getAddress().getAddress().getHostAddress();
			System.out.println("[Webinterface] remove auth for '"+ip+"'");
			WebInterface.get().getSpigotAuth().remove(ip);
		}
	}
	
	private boolean checkUser(Player player) {
		User u = User.getUser(player);
		
		return u != null;
	}
	
}

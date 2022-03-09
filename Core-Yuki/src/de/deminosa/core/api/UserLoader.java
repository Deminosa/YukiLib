package de.deminosa.core.api;

import org.bukkit.event.player.PlayerJoinEvent;

import de.deminosa.core.entitys.User;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	15:23:05 # 06.03.2022
*
*/

public interface UserLoader {

	/**
	 * Executed when the player was registered as a user when joining.
	 */
	public void loaded(PlayerJoinEvent event, User u);
	
}

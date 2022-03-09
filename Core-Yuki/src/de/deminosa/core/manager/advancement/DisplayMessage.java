package de.deminosa.core.manager.advancement;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:11:17 # 08.07.2019
*
*/

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class DisplayMessage {
	
	/**
	 * Display message to player
	 * @param player Player to view
	 */
	public abstract void showTo(Player player);
	
	/**
	 * Show message to onlines players
	 */
	public void showToAll()	{
		showTo(Bukkit.getServer().getOnlinePlayers());
	};
	
	/**
	 * Show message to specifics players
	 * @param collection Collection of players
	 */
	public void showTo(Collection<? extends Player> collection)	{
		for (Player player : collection)	{
			showTo(player);
		}
	}
	
	/**
	 * Show message to specifics players
	 * @param players Players to view
	 */
	public void showTo(Player... players)	{
		showTo(Arrays.asList(players));
	}
}

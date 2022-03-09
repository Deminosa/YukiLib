package de.deminosa.core.manager.warps;

import org.bukkit.Location;

import de.deminosa.core.entitys.User;
import de.deminosa.core.entitys.user.UserLocation;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	19:58:03 # 10.06.2021
*
*/

public class Warp {

	private final String name;
	private final Location loc;
	
	public Warp(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}
	
	public Warp(String name, UserLocation loc) {
		this.name = name;
		this.loc = loc.get();
	}
	
	public void telepor(User player) {
		player.getLocation().teleport(loc);
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return loc;
	}
}

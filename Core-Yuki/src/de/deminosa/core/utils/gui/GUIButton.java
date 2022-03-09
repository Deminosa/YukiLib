package de.deminosa.core.utils.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.deminosa.core.entitys.User;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	16:30:34 # 19.01.2019
*
*/

public abstract class GUIButton{
	
	private User cPlayer;
	
	public abstract void onClick(InventoryClickEvent event);
	public abstract ItemStack getIcon();
	
	
	public void setUser(User player) {
		this.cPlayer = player;
	}
	
	protected User getUser() {
		return cPlayer;
	}
	
}

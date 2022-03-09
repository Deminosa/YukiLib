package de.deminosa.core.utils.gui;

import org.bukkit.event.inventory.InventoryType;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	16:48:11 # 15.07.2020
*
*/

public class GUIOption {
	
	private boolean random, clean, defaul;
	private String title = "";
	private GUISize size = GUISize.INIT;
	private InventoryType type = InventoryType.CHEST;
	
	public GUIOption() {
	}
	
	public void setRandomColors(boolean bool) {
		random = bool;
	}
	
	public void setClanInventory(boolean bool) {
		clean = bool;
	}
	
	protected boolean isRandomColors() {
		return random;
	}
	
	protected boolean isCleanInventory() {
		return clean;
	}

	protected InventoryType getType() {
		return type;
	}

	public void setType(InventoryType type) {
		this.type = type;
	}

	protected GUISize getGUISize() {
		return size;
	}

	public void setSize(GUISize size) {
		this.size = size;
	}

	protected String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	protected boolean isDefault() {
		return defaul;
	}

	public void setDefaultInventory(boolean defaul) {
		this.defaul = defaul;
	}
}

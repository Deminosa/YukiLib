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
	
	private final boolean random, clean, defaul;
	private final String title;
	private final GUISize size;
	private final InventoryType type;
	private boolean unique;

	public GUIOption(boolean random, boolean clean, boolean defaul, String title, InventoryType type) {
		this.random = random;
		this.clean = clean;
		this.defaul = defaul;
		this.title = title;
		this.type = type;
		this.size = GUISize.INIT;
	}

	public GUIOption(boolean random, boolean clean, boolean defaul, String title, GUISize size) {
		this.random = random;
		this.clean = clean;
		this.defaul = defaul;
		this.title = title;
		this.size = size;
		this.type = null;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isUnique() {
		return unique;
	}

	protected boolean isRandomColors() {
		return random;
	}
	
	protected boolean isCleanInventory() {
		return clean;
	}

	public InventoryType getType() {
		return type;
	}

	public GUISize getGUISize() {
		return size;
	}

	protected String getTitle() {
		return title;
	}

	protected boolean isDefault() {
		return defaul;
	}

}

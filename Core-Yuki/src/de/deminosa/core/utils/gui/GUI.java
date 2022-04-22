package de.deminosa.core.utils.gui;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Warning;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;
import de.deminosa.core.exeptions.ConfigException;
import de.deminosa.core.utils.ItemBuilder;
/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	16:27:07 # 19.01.2019
 *
 */

public class GUI implements Listener{

	String title;

	User cPlayer;

	HashMap<Integer, GUIButton> buttons;
	Inventory inv;
	UID uid;

	public GUI(User cPlayer, GUIOption option) {
		uid = new UID();
		this.title = option.getTitle() + " §8§k" + IDManager.generateID(3);
		buttons = new HashMap<>();
		YukiLib.get().getServer().getPluginManager().registerEvents(this, YukiLib.get());
		this.cPlayer = cPlayer;

		if(option.getGUISize() != GUISize.INIT) {
			inv = Bukkit.createInventory(cPlayer.getEntity(), option.getGUISize().getSize(), this.title);
		}else if(option.getType() != InventoryType.CHEST){
			inv = Bukkit.createInventory(cPlayer.getEntity(), option.getType(), this.title);
		}else {
			throw new ConfigException("Somthing is wrong! The Option has a wrong config!");
		}

		if(option.isDefault()) {
			for(int i = 0; i < inv.getSize(); i++) {
				inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
			}
		}else if(option.isRandomColors()) {
			for(int i = 0; i < inv.getSize(); i++) {
				int r = ThreadLocalRandom.current().nextInt(16);
				switch(r) {
				case 0:
					inv.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 1:
					inv.setItem(i, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 2:
					inv.setItem(i, new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 3: 
					inv.setItem(i, new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 4:
					inv.setItem(i, new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 5:
					inv.setItem(i, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 6:
					inv.setItem(i, new ItemBuilder(Material.PINK_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 7:
					inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 8:
					inv.setItem(i, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 9:
					inv.setItem(i, new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 10:
					inv.setItem(i, new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 11:
					inv.setItem(i, new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 12:
					inv.setItem(i, new ItemBuilder(Material.BROWN_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 13:
					inv.setItem(i, new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 14:
					inv.setItem(i, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				case 15:
					inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§8").setLore("§6").build());
					break;
				}
			}
		}else if(option.isCleanInventory()) {

		}else {
			throw new ConfigException("Somthing is wrong! The Option has a wrong config!");
		}
	}

	public void setButton(int slot, GUIButton gUIButton) throws ConfigException{
		if(!buttons.containsKey(slot)) {
			gUIButton.setUser(cPlayer);
			buttons.put(slot, gUIButton);
			inv.setItem(slot, gUIButton.getIcon());
		}else {
			throw new ConfigException("The slot " + slot + " is allready set!");
		}
	}

	public void setButton(HashMap<Integer, GUIButton> buttons) {
		for(int i : buttons.keySet()) {
			setButton(i, buttons.get(i));
		}
	}

	public void open() {
		cPlayer.getEntity().openInventory(inv);
	}

	public Inventory getInventory() {
		return inv;
	}

	@EventHandler
	private void onClickHandler(InventoryClickEvent event) {

		if(event.getAction() == InventoryAction.UNKNOWN) event.setCancelled(true);
		if(event.getAction() == InventoryAction.NOTHING) event.setCancelled(true);

		if(buttons != null) {
			if(event.getWhoClicked().getOpenInventory().getTitle().equals(title)) {
				
				if(buttons.containsKey(event.getSlot()) && 
						buttons.get(event.getSlot()).getIcon() != null && event.getCurrentItem() != null
						&& event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
					
					if(event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) event.setCancelled(true);
					if(event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) event.setCancelled(true);
					if(event.getAction() == InventoryAction.HOTBAR_SWAP) event.setCancelled(true);
					buttons.get(event.getSlot()).onClick(event);
					
				}else if(buttons.containsKey(event.getSlot()) && buttons.get(event.getSlot()).getIcon() != null && 
						buttons.get(event.getSlot()).getIcon().equals(event.getCurrentItem())) {
					
					if(event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) event.setCancelled(true);
					if(event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) event.setCancelled(true);
					if(event.getAction() == InventoryAction.HOTBAR_SWAP) event.setCancelled(true);
					buttons.get(event.getSlot()).onClick(event);
					
				}
				event.setCancelled(true);
				return;
			}
		}
	}

	public UID getUID() {
		return uid;
	}

}

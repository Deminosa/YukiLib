package de.deminosa.core.manager.advancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;
import de.deminosa.core.utils.CoreID;

public class AdvancementManager {
	
	private final User u;
	
	public AdvancementManager(User u) {
		this.u = u;
	}

	private void send(Player player, String title, FrameType type, Material mat, int i) {
		try {
			AdvancementMessage message = new AdvancementMessage(CoreID.generate(8), type, 
					title, mat.name().toLowerCase(), YukiLib.get());
			message.showTo(player);
		}catch (StackOverflowError e) {
			Bukkit.getConsoleSender().sendMessage("&9Yuki-Lib &cERROR - StackOverflowError");
			Bukkit.getConsoleSender().sendMessage("&9CODE: &e" + e.fillInStackTrace());
			player.sendMessage("&9Advancement &8>> &7"+ title);
		}
	}

	public void send(String title, FrameType type, Material mat, int i) {
		send(u.getEntity(), title, type, mat, i);
	}

	public void sendInfo(String title, int i) {
		send(u.getEntity(), title, FrameType.task, Material.OAK_SIGN, i);
	}

}

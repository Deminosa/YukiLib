package de.deminosa.core.entitys.user;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.deminosa.core.manager.messages.Text;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:57:05 # 04.06.2021
*
*/

public class MessageBuilder {
	
	private final Player player;
	
	public MessageBuilder(Player player) {
		this.player = player;
	}
	
	public void sendMessage(String prefix, String msg) {
		player.sendMessage(prefix + " " + msg);
	}
	
	public void sendMessage(Text txt) {
		player.sendMessage(txt.getString());
	}
	
	public void sendActionbar(String msg) {
		String s = ChatColor.translateAlternateColorCodes('&', msg);
		TextComponent text = new TextComponent(s);
		BaseComponent bc = text;
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, bc);
	}
	
	public void sendActionbar(Text txt) {
		String s = ChatColor.translateAlternateColorCodes('&', txt.getString());
		TextComponent text = new TextComponent(s);
		BaseComponent bc = text;
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, bc);
	}
	
	public void sendTitle(int in, int stay, int out, String title, String subTitle) {
		title = ChatColor.translateAlternateColorCodes('&', title);
		subTitle = ChatColor.translateAlternateColorCodes('&', subTitle);
		player.sendTitle(title, subTitle, in, stay, out);
	}
	
	public void sendOnlyTitle(int in, int stay, int out, String title) {
		sendTitle(in, stay, out, title, "");
	}
	
	public void sendOnlySubTitle(int in, int stay, int out, String subtitle) {
		sendTitle(in, stay, out, "", subtitle);
	}
}

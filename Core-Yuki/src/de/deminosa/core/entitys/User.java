package de.deminosa.core.entitys;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.user.MessageBuilder;
import de.deminosa.core.entitys.user.UserLocation;
import de.deminosa.core.manager.advancement.AdvancementManager;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:48:45 # 04.06.2021
*
*/

public class User {

	private static final HashMap<Player, User> CACHE = new HashMap<>();
	
	public static User getUser(Player player) {
		return CACHE.get(player);
	}
	
	private final Player player;
	private final UserLocation loc;
	private final MessageBuilder msgb;
	private final AdvancementManager advancement;
	
	public User(Player player) {
		this.player = player;
		loc = new UserLocation(player);
		msgb = new MessageBuilder(player);
		advancement = new AdvancementManager(this);
		
		CACHE.put(player, this);
	}
	
	/**
	 * Update the User Cache for the Player/User
	 */
	public void flush() {
		CACHE.put(player, this);
	}
	
	@Deprecated
	/**
	 * Update the User Cache for the Player/User <br>
	 * @see flush()
	 */
	public void updateCache() {
		flush();
	}

	/**
	 * <h1><b>Only for a Bungeecord server system or cloud</b></h1>
	 * <p>Connect the Player to a Server</p>
	 * @param server - Use the server name that is entered in the Bungee Config.<br />
	 * <i>If you have a cloud, use the server name that the cloud gave you</i>
	 */
	public void connect(String server) {
		player.sendMessage("§7Connecting...");
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(YukiLib.get(), "BungeeCord", out.toByteArray());
	}
	
	/**
	 * <p>Here you can get the Bukkit Player</p>
	 * @return Player
	 */
	public Player getEntity() {
		return player;
	}

	/**
	 * <p>get the location manager. A small, extended location class.</p>
	 * @return UserLocation
	 */
	public UserLocation getLocation() {
		return loc;
	}
	
	/**
	 * <p>get the MessageBuilder. A small, extended message class.</p>
	 * @return MessageBuilder
	 */
	public MessageBuilder getMessageBuilder() {
		return msgb;
	}

	/**
	 * <p>get the AdvancementManager. A small class for your own Advancement Messages on Top-Right</p>
	 * 
	 * @return AdvancementManager
	 */
	public AdvancementManager getAdvancement() {
		return advancement;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void sendMessage(String prefix, String msg) {
		getMessageBuilder().sendMessage(prefix, msg);
	}

	
	
	
}

package de.deminosa.core.entitys;

import java.util.HashMap;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.user.MessageBuilder;
import de.deminosa.core.entitys.user.UserBungeeCord;
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
	
	public static User get(Player player) {
		return CACHE.get(player);
	}
	
	private final Player player;
	private final UserLocation loc;
	private final MessageBuilder msgb;
	private final AdvancementManager advancement;
	private final UserBungeeCord bungeeCord;
	
	public User(@Nonnull Player player) {
		this.player = player;
		loc = new UserLocation(player);
		msgb = new MessageBuilder(player);
		advancement = new AdvancementManager(this);
		bungeeCord = new UserBungeeCord(this);
		
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

	/**
	 * <p>get the UserBungeeCord. A small class for UserBungeeCord<br>
	 * It also includes features for recognizing players on a Bedrock account. (Works only if the server is Java based.)</p>
	 * 
	 * @return UserBungeeCord
	 */
	public UserBungeeCord getBungeeCord() {
		return bungeeCord;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void sendMessage(String prefix, String msg) {
		getMessageBuilder().sendMessage(prefix, msg);
	}

	public String getName() {
		return player.getName();
	}
	
	
}

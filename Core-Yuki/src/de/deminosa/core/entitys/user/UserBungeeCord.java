package de.deminosa.core.entitys.user;

import de.deminosa.core.entitys.User;
import de.deminosa.core.utils.bungeecord.BungeecordChannelMessageSender;
import de.deminosa.core.utils.bungeecord.BungeecordChannelMessageType;

public class UserBungeeCord {
    
    private final User user;

    public UserBungeeCord(User user) {
        this.user = user;
    }
    
    /**
	 * <h1><b>Only for a Bungeecord server system or cloud</b></h1>
	 * <p>Connect the Player to a Server</p>
	 * @param server - Use the server name that is entered in the Bungee Config.<br />
	 * <i>If you have a cloud, use the server name that the cloud gave you</i>
	 */
	public void connect(String server) {
		BungeecordChannelMessageSender sender = new BungeecordChannelMessageSender(BungeecordChannelMessageType.CONNECT);
		sender.setArgument(server);
		sender.sendToPlayer(user);
	}

    /**
	 * <p>Checking of the Player if they from Bedrock Client.<br>
	 * use the prefix if this is changed</p>
	 * 
	 * @param prefix
	 * @return
	 */
	public boolean isBedrockPlayer(String prefix) {
		return user.getName().startsWith(prefix);
	}
	
	/**
	 * <p>Checking of the Player if they from Bedrock Client.</p>
	 * 
	 * @return
	 */
	public boolean isBedrockPlayer() {
		return user.getName().startsWith(".");
	}
}

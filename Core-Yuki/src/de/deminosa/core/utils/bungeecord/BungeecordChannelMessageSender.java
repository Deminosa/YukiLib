package de.deminosa.core.utils.bungeecord;

import java.util.ArrayList;
import java.util.List;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;

public class BungeecordChannelMessageSender {
    
    private final BungeecordChannelMessageType type;
    private final List<String> arguments;

    public BungeecordChannelMessageSender(BungeecordChannelMessageType type) {
        this.type = type;
        arguments = new ArrayList<>();
    }

    public void setArgument(String s) {
        arguments.add(s);
    }

    public void sendToPlayer(User player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(type.toChannelString());
		for(int i = 1; i < arguments.size(); i++) {
            out.writeUTF(arguments.get(i));
        }
        player.getEntity().sendPluginMessage(YukiLib.get(), "BungeeCord", out.toByteArray());
    }

    public BungeecordChannelMessageType getType() {
        return type;
    }
}

package de.deminosa.core.utils.bungeecord;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.YukiLib;

public class BungeecordChannelMessageReceived implements PluginMessageListener{
    
    private final BungeecordChannelMessageType type;
    private final List<String> arguments;

    public BungeecordChannelMessageReceived(BungeecordChannelMessageType type) {
        this.type = type;
        arguments = new ArrayList<>();
        YukiLib.get().getServer().getMessenger().registerIncomingPluginChannel(YukiLib.get(), "BungeeCord", this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        
        if(channel.equals("BungeeCord")) {
            String subchannel = in.readUTF();
            
            if(subchannel.equals(type.toChannelString())) {
                switch(type) {
                    // 1 Response
                    case UUID:
                    case GET_SERVER:
                    case GET_SERVERS:
                        read(in, 1);
                        break;

                    // 2 Response
                    case IP:
                    case PLAYER_COUNT:
                    case PLAYER_LIST:
                    case UUID_OTHER:
                        read(in, 2);
                        break;

                    // 3 Response
                    case SERVER_IP:
                    case IP_OTHER:
                        read(in, 3);
                        break;

                    // Default
                    default:
                        break;
                }
            }
        }
    }

    private void read(ByteArrayDataInput in, int lines) {
        for(int i = 0; i < lines; i++){
            arguments.add(in.readUTF());
        }
    }

}

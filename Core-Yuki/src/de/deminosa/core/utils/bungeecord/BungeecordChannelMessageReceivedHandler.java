package de.deminosa.core.utils.bungeecord;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import de.deminosa.core.YukiLib;

public class BungeecordChannelMessageReceivedHandler implements PluginMessageListener{
    
    private final BungeecordChannelMessageType type;
    private final List<String> arguments;
    private final List<BungeecordChannelMessageReceivedObject> receivers;
    private int index = -1;

    public BungeecordChannelMessageReceivedHandler(BungeecordChannelMessageType type) {
        this.type = type;
        arguments = new ArrayList<>();
        receivers = new ArrayList<>();
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

                for(BungeecordChannelMessageReceivedObject obj : receivers){
                    obj.received(this);
                }
                
            }
        }
    }

    public void registerObject(BungeecordChannelMessageReceivedObject obj){
        receivers.add(obj);
    }

    private void read(ByteArrayDataInput in, int lines) {
        for(int i = 0; i < lines; i++){
            arguments.add(in.readUTF());
        }
    }

    public String read() {
        index++;
        return arguments.get(index);
    }

    public boolean isNextLine() {
        int nextIndex = index;
        nextIndex++;

        if(nextIndex < arguments.size()){
            return true;
        }
        return false;
    }

}

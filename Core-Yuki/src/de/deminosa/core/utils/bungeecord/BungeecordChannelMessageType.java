package de.deminosa.core.utils.bungeecord;

public enum BungeecordChannelMessageType {
    
    CONNECT("Connect"),
    CONNECT_OTHER("ConnectOther"),
    IP("IP"),
    IP_OTHER("IPOther"),
    PLAYER_COUNT("PlayerCount"),
    PLAYER_LIST("PlayerList"),
    GET_SERVERS("GetServers"),
    GET_SERVER("GetServer"),
    MESSAGE("Message"),
    MESSAGE_RAW("MessageRaw"),
    UUID("UUID"),
    UUID_OTHER("UUIDOther"),
    SERVER_IP("ServerIP"),
    KICK_PLAYER("KickPlayer")
    ;

    private BungeecordChannelMessageType(String string) {
        this.string = string;
    }

    private final String string;

    public String toChannelString() {
        return string;
    }

}

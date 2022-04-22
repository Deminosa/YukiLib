# YukiLib
![](https://img.shields.io/badge/YukiLib-0.0.1-blue) ![](https://img.shields.io/badge/Webhook-0.0.1-blue) ![](https://img.shields.io/badge/Webinterface-0.0.1-blue)
![](https://img.shields.io/github/issues/Deminosa/YukiLib) ![](https://img.shields.io/github/forks/Deminosa/YukiLib) ![](https://img.shields.io/github/stars/Deminosa/YukiLib) ![](https://img.shields.io/github/license/Deminosa/YukiLib)

## What is YukiLib?
YukiLib is a library for Minecraft plugins intended to make programming a little easier. This also means that this library is correspondingly large, but cannot contain everything.

## Webinterface
The web interface is still very young and can therefore still be expanded.
The web interface supports query response.
```java 
public class KickResponse extends QueryResponse{
	@Override
	public void incomingResponse(String response) {
		if(response.startsWith("kick")) {
			String name = response.split("=")[1];

			Player player = Bukkit.getPlayer(name);
			if(player != null) {
				player.kickPlayer("§cKick from Server\n"
						+ "§cReson: §7Kick via Interface");	
			}else {
				System.out.println("Player '"+name+"' is not online!");
			}
		}
	}
}
```

## Discord / Webhook
YukiLib also supports webhook for Discord in simplified variant.
```java 
// Only HTTPS is Supportet
Webhook hook = new DiscordHook(new URL("https://discord.com"));
hook.send("My Message");
```


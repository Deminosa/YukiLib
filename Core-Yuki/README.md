# YukiLib
![](https://img.shields.io/badge/YukiLib-0.1.6-blue) ![](https://img.shields.io/badge/Webhook-0.0.1-blue) ![](https://img.shields.io/badge/Webinterface-0.0.1-blue)
![](https://img.shields.io/github/issues/Deminosa/YukiLib) ![](https://img.shields.io/github/forks/Deminosa/YukiLib) ![](https://img.shields.io/github/stars/Deminosa/YukiLib) ![](https://img.shields.io/github/license/Deminosa/YukiLib)

## What is YukiLib?
YukiLib is a library for Minecraft plugins intended to make programming a little easier. This also means that this library is correspondingly large, but cannot contain everything.

## Webinterface
The web interface is still very young and can therefore still be expanded.
The web interface supports query response. The web interface is not secured yet. In other words, there is no login function yet and anyone can connect to it. The default port is 20000.

- [KickResponse.java](https://github.com/Deminosa/YukiLib/blob/master/Core-Yuki/src/de/deminosa/webinterface/responses/KickResponse.java)
- [QueryResponse.java](https://github.com/Deminosa/YukiLib/blob/master/Core-Yuki/src/de/deminosa/webinterface/api/QueryResponse.java)

```java 
public class KickResponse implements QueryResponse {
	@Override
	public void incomingResponse(HashMap<String, String> map) {
		if(map.containsKey("kick")) {
			String name = map.get("kick");

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

# Core
## Entitys
### User
```java
User user = User.get(player); 
```

## MySQL
MySQL has been simplified a bit. See the example for this:

- First create a table 

```java
		Table table = new Table("user");
```

- Then create all your columns and let them create your table class.

```java
		Colum colums = new Colum();
		
		colums.create("UUID", ColumType.UUID);
		colums.create("pos", ColumType.VARCHAR_16);
		
		table.create(colums);
```

- Next you create a ColumValue class with which you assign a name and the corresponding value. Here we used the position.

```java
		ColumValue pos = new ColumValue("pos", User.get(player).getLocation().toString());
		ColumValue uuid = new ColumValue("pos", player.getUniqueId().toString());
```

- Next: Since we haven't added a value yet, we need to add a reference value first. Therefore our first value will be the player's UUID.

```java
		table.setFirstColum(uuid);
```

- Now that we have added a reference value, we can now add the position via update.

```java
		table.updateColum(new Update(new Search("UUID", player.getUniqueId().toString()), pos));
```

- Overall it looks like this:

```java
		Table table = new Table("user");
		Colum colums = new Colum();
		
		colums.create("UUID", ColumType.UUID);
		colums.create("pos", ColumType.VARCHAR_16);
		
		table.create(colums);
		
		ColumValue pos = new ColumValue("pos", User.get(player).getLocation().toString());
		ColumValue uuid = new ColumValue("pos", player.getUniqueId().toString());
		table.setFirstColum(uuid);
		
		table.updateColum(new Update(new Search("UUID", player.getUniqueId().toString()), pos));
```
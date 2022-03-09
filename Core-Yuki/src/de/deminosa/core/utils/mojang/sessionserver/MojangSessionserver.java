package de.deminosa.core.utils.mojang.sessionserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	12:08:46 # 22.01.2021
*
*/

public class MojangSessionserver {

	private static HashMap<String, String> uuidCache = new HashMap<String, String>();
	private static HashMap<String, String> nameCache = new HashMap<String, String>();

	public String getUUID(MojangName name){
		if (uuidCache.containsKey(name.getName())) {
			return (String)uuidCache.get(name.getName());
		}
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name.getName());
			InputStream stream = url.openStream();
			InputStreamReader inr = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(inr);
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			String result = sb.toString();
			JsonElement element = new JsonParser().parse(result);
			JsonObject obj = element.getAsJsonObject();
			String api = obj.get("id").toString();
			api = api.substring(1);
			api = api.substring(0, api.length() - 1);
			StringBuffer sbu = new StringBuffer(api);
			sbu.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
			String uuid = sbu.toString();
			uuidCache.put(name.getName(), uuid);
			return uuid;
		}catch (IOException|IllegalStateException localIOException) {}
		return null;
	}
	
	public String getName(MojangUUID uuid){
		if (nameCache.containsKey(uuid.getUUID())) {
			return (String)uuidCache.get(uuid.toString());
		}
		try {
			URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.getUUID());
			InputStream stream = url.openStream();
			InputStreamReader inr = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(inr);
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			String result = sb.toString();
			JsonElement element = new JsonParser().parse(result);
			JsonObject obj = element.getAsJsonObject();
			String name = obj.get("name").toString();
			name = name.substring(1);
			name = name.substring(0, name.length() - 1);
			nameCache.put(uuid.getUUID(), name);
			return name;
		}catch (IOException|IllegalStateException localIOException) {}
		return null;
	}
	
}

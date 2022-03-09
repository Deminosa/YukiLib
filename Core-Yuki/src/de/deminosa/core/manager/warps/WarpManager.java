package de.deminosa.core.manager.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.deminosa.core.YukiLib;
import de.deminosa.core.exeptions.ConfigException;
import de.deminosa.core.utils.files.JIniFile;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:02:18 # 10.06.2021
*
*/

public class WarpManager {

	public static void save(Warp warp) {
		JIniFile file = new JIniFile(YukiLib.get().getDataFolder() + "/core/cache/warps/"
									+ warp.getName()+".warp");
		file.setFloat("header", "x", (float) warp.getLocation().getX());
		file.setFloat("header", "y", (float) warp.getLocation().getY());
		file.setFloat("header", "z", (float) warp.getLocation().getZ());
		file.setFloat("header", "a", (float) warp.getLocation().getYaw());
		file.setFloat("header", "b", (float) warp.getLocation().getPitch());
		file.setString("header", "w", warp.getLocation().getWorld().getName());
		file.setInteger("secure", "x", warp.hashCode());
		file.UpdateFile();
	}
	
	public static Warp getWarp(String name) throws ConfigException{
		JIniFile file = new JIniFile(YukiLib.get().getDataFolder() + "/core/cache/warps/"
				+ name + ".warp");
		if(!file.existSection("header") || !file.existSection("secure")) {
			throw new ConfigException("The warp file '"+name+"' is damaged! Can't load file! "
					+ "Even if ignore damaged file is on, it will not work!");
		}
		int x, y, z, secure;
		String world;
		float a,b;
		
		world = file.getString("header", "w", "world");
		x = file.getInteger("header", "x", 0);
		y = file.getInteger("header", "y", 0);
		z = file.getInteger("header", "z", 0);
		a = file.getFloat("header", "a", 0f);
		b = file.getFloat("header", "b", 0f);
		secure = file.getInteger("secure", "x", 0);
		
		if(Bukkit.getWorld(world) == null) throw new ConfigException("The world '"+world+"' does not exsist!");
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setYaw(a);
		loc.setPitch(b);
		Warp w = new Warp(name, loc);
		
		if(w.hashCode() == secure) {
			return w;
		}
		
		if(YukiLib.WARP_IGNORE_DAMAGED) {
			Bukkit.getConsoleSender()
			.sendMessage("§6WARNING §rWarp is loaded but there could be errors. ("+name+")");
			return w;
		}else {
			throw new ConfigException("The warp '"+name+"' was corrupted or could not be verified. "
					+ "The loading of the file was interrupted!");
		}
	}
	
}

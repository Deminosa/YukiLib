package de.deminosa.core;

import java.io.File;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Warning;
import org.bukkit.plugin.java.JavaPlugin;

import de.deminosa.core.api.YukiLoader;
import de.deminosa.core.api.YukiManager;
import de.deminosa.core.manager.login.JoinQuitListener;
import de.deminosa.core.utils.files.YamlConfig;
import de.deminosa.core.utils.mysql.AsyncMySQL;
import de.deminosa.webinterface.WebInterface;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	12:04:37 # 24.05.2021
*
*/

public class YukiLib extends JavaPlugin{

	private static YukiLib instance;
	private HashSet<YukiLoader> apis;
	private YukiManager manager;
	
	private AsyncMySQL mysql;
	private YamlConfig CoreConfig;
	
	public static boolean DEBUG = false;
	public static boolean WARP_IGNORE_DAMAGED = false;
	
	@Override
	public void onEnable() {
		super.onEnable();
		apis = new HashSet<>();
		instance = this;
		manager = new YukiManager();
		
		new WebInterface().onEnable();
		
		apis.forEach((api) ->{
			api.load(instance);
		});
		getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
		
		createDir(getDataFolder() + "/core/cache/warps");
		createDir(getDataFolder() + "/core/cache/player");
		
		createConfig();
		conMySQL();
		getServer().getMessenger().registerOutgoingPluginChannel(get(), "BungeeCord");
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		new WebInterface().onDisable();
	}
	
	private void createConfig() {
		CoreConfig = new YamlConfig("", "config");

		if(!CoreConfig.exsistPath("isSet")) {
			CoreConfig.set("isSet", true);
			CoreConfig.set("Core.debug", false);
			CoreConfig.set("Core.prefix", "&8[&9%sys%&8] &7");
			CoreConfig.set("Core.noPerm", "&cSorry, Permission %s% need!");
			CoreConfig.set("Warp.IgnoreDamagedFile", false);
			CoreConfig.save();
		}

		DEBUG = CoreConfig.getOrDefault("Core.debug", Boolean.class, false);
		WARP_IGNORE_DAMAGED = CoreConfig.getOrDefault("Warp.IgnoreDamagedFile", Boolean.class, false);
		
		apis.forEach((api) -> {
			api.createConfig(CoreConfig);
		});
	}
	
	public void registerLoader(YukiLoader loader) {
		if(!apis.contains(loader)) {
			apis.add(loader);
		}else {
			Bukkit.getConsoleSender()
			.sendMessage("§cERO - The CoreLoader '"+loader.getClass().getName()+"' is already reisterd!" );
		}
	}
	
	public void createDir(String dir) {
		File file = new File(dir);
		Bukkit.getConsoleSender().sendMessage("§bINFO\tInit '"+dir+"'");
		file.mkdir();
		file.mkdirs();
//		if(!file.mkdirs()) {
//			throw new CoreException("can't create a dir with the path '"+dir+"'");
//		}
	}
	
	private void conMySQL() {
		YamlConfig config = new YamlConfig("", "mysql");

		if(!config.exsistPath("isSet")) {
			config.set("isSet", true);
			config.set("MySQL.host", "localhost");
			config.set("MySQL.user", "coreUser");
			config.set("MySQL.pass", "corePassword");
			config.set("MySQL.data", "database");
			config.save();
		}
		
		mysql = new AsyncMySQL(this, 
				config.getOrDefault("MySQL.host", String.class, ""),
				config.getOrDefault("MySQL.user", String.class, ""),
				config.getOrDefault("MySQL.pass", String.class, ""),
				config.getOrDefault("MySQL.data", String.class, ""));
		
		apis.forEach((api) -> {
			api.connectMySQL(mysql);
		});
	}
	
	@Warning
	public static YukiLib get() {
		return instance;
	} 

	public AsyncMySQL getAsyncMySQL() {
		return mysql;
	}

	public YukiManager getManager() {
		return manager;
	}
	
}

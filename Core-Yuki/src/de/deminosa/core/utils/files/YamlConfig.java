package de.deminosa.core.utils.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.deminosa.core.YukiLib;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:47:52 # 26.12.2018
*
*/

public class YamlConfig {

	public File file;
	public FileConfiguration config;
	
	public YamlConfig(String path, String file) {
		this.file = new File(YukiLib.get().getDataFolder() + "/core/" + path + "/" + file + ".yml");
		if(!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
			}
		}
		config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
	}
	
	public boolean exsistPath(String path) {
		return config.isSet(path);
	}
	
	public Object get(String path) {
		return config.get(path);
	}
	
	public <T> T getOrDefault(String path, Class<? extends T> clazz, T t) {
		if(!config.contains(path)) {
			return t;
		}
		return clazz.cast(config.get(path));
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

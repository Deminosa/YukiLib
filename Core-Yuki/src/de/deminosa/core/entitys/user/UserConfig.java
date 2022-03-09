package de.deminosa.core.entitys.user;

import java.io.File;
import java.io.IOException;

import de.deminosa.core.YukiLib;
import de.deminosa.core.entitys.User;
import de.deminosa.core.utils.files.JIniFile;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:14:43 # 04.06.2021
*
*/

public class UserConfig {

	private final JIniFile config;
	
	/**
	 * <p>get the default config from cache</p>
	 * 
	 * @param u
	 */
	public UserConfig(User u) {
		File file = new File(YukiLib.get().getDataFolder() + "/core/cache/player/" 
					+ u.getEntity().getUniqueId().toString()+".ini");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		config = new JIniFile(YukiLib.get().getDataFolder() + "/core/cache/player/" 
				+ u.getEntity().getUniqueId().toString()+".ini");
	}
	
	/**
	 * <p>make your own player config</p>
	 * 
	 * @param u
	 * @param dir
	 */
	public UserConfig(User u, String dir) {
		File file = new File(YukiLib.get().getDataFolder() + "/"+dir+"/" 
					+ u.getEntity().getUniqueId().toString()+".ini");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		config = new JIniFile(YukiLib.get().getDataFolder() + "/core/cache/player/" 
				+ u.getEntity().getUniqueId().toString()+".ini");
	}
	
	public JIniFile getData() {
		return config;
	}
	
}

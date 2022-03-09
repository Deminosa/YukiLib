package de.deminosa.core.api;

import de.deminosa.core.YukiLib;
import de.deminosa.core.utils.files.YamlConfig;
import de.deminosa.core.utils.mysql.AsyncMySQL;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	00:13:34 # 08.07.2021
*
*/

public interface YukiLoader {

	/**
	 * <p>Call if the instance is loaded</p>
	 */
	public void load(YukiLib yukiLib);
	
	/**
	 * <p>AsyncMySQL the real MySQL Class<br />
	 * If you want the easy way, use MySQL ({@link de.deminosa.core.mysql.MySQL})</p>
	 * @param mysql
	 */
	public void connectMySQL(AsyncMySQL mysql);
	
	/**
	 * <p>Here you can modify the Core config after loading everything else</p>
	 * 
	 * @param config
	 */
	public void createConfig(YamlConfig config);
}

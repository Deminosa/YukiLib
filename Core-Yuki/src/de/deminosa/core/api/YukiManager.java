package de.deminosa.core.api;

import java.util.ArrayList;
import java.util.HashMap;

import de.deminosa.core.exeptions.YukiLibException;
import de.deminosa.core.manager.command.Command;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:07:20 # 23.01.2022
*
*/

public class YukiManager {

	private final HashMap<String, Command> commands;
	private final ArrayList<UserLoader> userLoader;
	
	public YukiManager() {
		commands = new HashMap<>();
		userLoader = new ArrayList<>();
	}
	
	public void registerCommand(String name, Command command) {
		if(!commands.containsKey(name)) {
			commands.put(name, command);
		}else {
			throw new YukiLibException("The Command '" + name + "' with class '" + command.getClass().getName() + "'"
					+ " is already register!");
		}
	}
	
	public void registerUserLoader(UserLoader loader) {
		if(userLoader.contains(loader)) return;
		
		userLoader.add(loader);
	}
	
	public ArrayList<UserLoader> getUserLoader(){
		return userLoader;
	}
	
}

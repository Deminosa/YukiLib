package de.deminosa.core.manager.command;

import java.util.HashMap;

import de.deminosa.core.entitys.User;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:23:47 # 08.06.2021
*
*/

public abstract class Command implements PlayerCommand{
	
	protected final HashMap<User, Object> CACHE;
	
	public Command() {
		CACHE = new HashMap<>();
	}
	
	public abstract boolean run(User u, String command, String[] args);
	
}

package de.deminosa.core.manager.command;

import de.deminosa.core.entitys.User;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:17:42 # 08.06.2021
*
*/

public interface PlayerCommand {

	public void executeCommand(User user, String[] args);
	public String getPermission();
	
}

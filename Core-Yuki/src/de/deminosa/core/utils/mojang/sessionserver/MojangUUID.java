package de.deminosa.core.utils.mojang.sessionserver;

import java.util.UUID;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	12:11:21 # 22.01.2021
*
*/

public class MojangUUID {

	private final UUID uuid;
	
	public MojangUUID(UUID uuid) {
		this.uuid = uuid;
	}
	
	public String getUUID() {
		return uuid.toString();
	}
}

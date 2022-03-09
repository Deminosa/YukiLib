package de.deminosa.core.utils.mojang;

import de.deminosa.core.utils.mojang.sessionserver.MojangSessionserver;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	18:22:40 # 29.07.2021
*
*/

public class MojangAPI {

	private final MojangSessionserver api;
	
	public MojangAPI() {
		api = new MojangSessionserver();
	}
	
	public MojangSessionserver getSessionServer() {
		return api;
	}
	
}

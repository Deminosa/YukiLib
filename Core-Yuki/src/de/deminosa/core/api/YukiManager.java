package de.deminosa.core.api;

import java.util.ArrayList;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:07:20 # 23.01.2022
*
*/

public class YukiManager {

	private final ArrayList<UserLoader> userLoader;
	
	public YukiManager() {
		userLoader = new ArrayList<>();
	}
	
	public void registerUserLoader(UserLoader loader) {
		if(userLoader.contains(loader)) return;
		
		userLoader.add(loader);
	}
	
	public ArrayList<UserLoader> getUserLoader(){
		return userLoader;
	}
	
}

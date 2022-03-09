package de.deminosa.webinterface.auth;

import java.util.ArrayList;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	11:16:26 # 02.01.2022
*
*/

public class SpigotPermissionAuth {

	private ArrayList<String> auth;
	
	public SpigotPermissionAuth() {
		auth = new ArrayList<>();
	}
	
	public boolean isAuth(String adress) {
		return auth.contains(adress);
	}
	
	public void add(String adress) {
		if(!isAuth(adress)) {
			auth.add(adress);
		}
	}
	
	public void remove(String adress) {
		if(isAuth(adress)) {
			auth.remove(adress);
		}
	}
	
}

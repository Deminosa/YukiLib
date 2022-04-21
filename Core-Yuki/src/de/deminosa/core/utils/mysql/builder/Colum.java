package de.deminosa.core.utils.mysql.builder;

import java.util.HashMap;

import javax.annotation.Nonnull;

import de.deminosa.core.utils.mysql.ColumType;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	13:21:32 # 21.04.2022
*
*/

public class Colum {

	private final HashMap<String, ColumType> map;
	
	public Colum() {
		map = new HashMap<>();
	}
	
	@Nonnull
	public void create(String columName, ColumType type) {
		map.put(columName, type);
	}
	
	public HashMap<String, ColumType> getMap(){
		return map;
	}
}

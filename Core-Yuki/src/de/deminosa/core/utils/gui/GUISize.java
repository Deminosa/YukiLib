package de.deminosa.core.utils.gui;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	17:12:05 # 15.07.2020
*
*/

public enum GUISize {

	INIT(0),
	ROW_1(9),
	ROW_2(18),
	ROW_3(27),
	ROW_4(36),
	ROW_5(45),
	ROW_6(54),
	
	CHEST(27),
	CHEST_DOUBLE(54);
	
	private int size;
	
	GUISize(int size){
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}

package de.deminosa.core.manager.messages;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:53:45 # 08.06.2021
*
*/

public class Text {

	private String txt = "";
	
	public Text(TextBuilder builder) {
		txt = builder.getText();
	}
	
	public String getString() {
		return txt;
	}
}

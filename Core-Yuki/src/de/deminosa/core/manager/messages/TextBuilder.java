package de.deminosa.core.manager.messages;

import org.bukkit.ChatColor;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:49:27 # 08.06.2021
*
*/

public class TextBuilder {

	private String msg;
	
	public void setText(String text) {
		this.msg = text;
	}
	
	/**
	 * <p>Make a alternative color code. Example: '&'</p>
	 * @param c
	 * @return
	 */
	public TextBuilder convertColorCodes(char c) {
		this.msg = ChatColor.translateAlternateColorCodes(c, msg);
		return this;
	}
	
	/**
	 * <p>Highlight the numbers. ConvertColorCodes() is also used!</p>
	 * @param code
	 * @param color
	 * @return
	 */
	public TextBuilder highlightNumbers(char code, int color) {
		char[] chars = msg.toCharArray();
		String txt = "";
		for(char c : chars) {
			switch(c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				txt += code + color;
				break;
			}
			txt += c;
		}
		msg = txt;
		convertColorCodes(code);
		return this;
	}
	
	public Text build() {
		return new Text(this);
	}
	
	protected String getText() {
		return msg;
	}
}

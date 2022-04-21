package de.deminosa.core.utils.mysql.builder;

import javax.annotation.Nonnull;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	13:46:43 # 21.04.2022
*
*/

public class Search {

	private final String where, match;
	
	public Search(@Nonnull String where, @Nonnull String match) {
		this.match = match;
		this.where = where;
	}

	public String getWhere() {
		return where;
	}

	public String getMatch() {
		return match;
	}
	
}

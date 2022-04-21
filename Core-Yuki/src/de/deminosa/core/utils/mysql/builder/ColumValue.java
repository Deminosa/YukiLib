package de.deminosa.core.utils.mysql.builder;

import javax.annotation.Nonnull;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	13:30:32 # 21.04.2022
*
*/

public class ColumValue {

	private final String columName;
	private Object columValue;
	
	@Nonnull
	public ColumValue(String name, @Nonnull Object obj) {
		columName = name;
		setColumValue(obj);
	}

	public String getColumName() {
		return columName;
	}

	public Object getColumValue() {
		return columValue;
	}

	public void setColumValue(Object columValue) {
		this.columValue = columValue;
	}
	
}

package de.deminosa.core.utils.mysql.builder;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	13:35:54 # 21.04.2022
*
*/

public class Update {

	private final Search search;
	private final ColumValue colum;
	
	public Update(Search search, ColumValue colum) {
		this.search = search;
		this.colum = colum;
	}

	public ColumValue getColum() {
		return colum;
	}

	public Search getSearch() {
		return search;
	}
	
}

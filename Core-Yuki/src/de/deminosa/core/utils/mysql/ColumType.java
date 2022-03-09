package de.deminosa.core.utils.mysql;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	12:22:38 # 01.06.2019
*
*/

public enum ColumType {
	
	VARCHAR_128("VARCHAR(128)"),
	VARCHAR_64("VARCHAR(64)"),
	VARCHAR_32("VARCHAR(32)"),
	VARCHAR_16("VARCHAR(16)"),
	VARCHAR_8("VARCHAR(8)"),
	VARCHAR_4("VARCHAR(4)"),
	VARCHAR_256("VARCHAR(256)"),
	VARCHAR_512("VARCHAR(512)"),
	VARCHAR_1024("VARCHAR(1024)"),
	VARCHAR_2048("VARCHAR(2048)"),
	INT("INT"),
	LONG("LONG"),
	DOUBLE("DOUBLE"),
	UUID("VARCHAR(48)");
	

	private String args;
	
	ColumType(String args) {
		this.args = args;
	}
	
	public String getArguments() {
		return args;
	}
	
}

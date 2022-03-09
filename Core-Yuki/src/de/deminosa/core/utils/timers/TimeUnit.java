package de.deminosa.core.utils.timers;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:29:02 # 08.06.2021
*
*/

public enum TimeUnit {

	SECONDS(20),
	MINUTES(20*60),
	HOURS(20*60*60),
	DAYS(20*60*60*24),
	MONTH(20*60*60*24*30);
	
	private long unit;
	
	private TimeUnit(long unit) {
		this.unit = unit;
	}
	
	public long getUnit() {
		return unit;
	}
}

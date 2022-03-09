package de.deminosa.core.utils.timers;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:25:46 # 08.06.2021
*
*/

public class Cooldown {

	private long currentTime = 0;
	private long saveTime = 0;
	private long t = 0;
	
	/**
	 * <p>Use i for your value and the TimeUnit from {@link de.deminosa.core.utils.timers.TimeUnit}<br />
	 * to set what you want.</p>
	 * @param i
	 * @param time
	 */
	public Cooldown(int i, TimeUnit time) {
		t = i * time.getUnit();
	}
	
	/**
	 * <p>Saves the values in the cache. Can be executed over and over to reset the cooldown.</p>
	 */
	public void start() {
		currentTime = System.currentTimeMillis()/1000;
		saveTime = currentTime + t;
	}
	
	/**
	 * <p>It is returned true if the timer is less than or equal to 0.</p>
	 * @return boolean
	 */
	public boolean isEnd() {
		return saveTime - (System.currentTimeMillis()/1000) <= 0;
	}
	
	/**
	 * <p>Get the remaining time in seconds.</p>
	 * @return long
	 */
	public long getRemainingTime() {
		return saveTime - (System.currentTimeMillis()/1000);
	}
	
}

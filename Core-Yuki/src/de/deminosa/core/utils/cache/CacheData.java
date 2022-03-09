package de.deminosa.core.utils.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	20:16:25 # 02.11.2021
*
*/

public class CacheData {

	private HashMap<String, Object> cacheData;
	private int timeStamp = 0;
	private final int timeout;
	
	/**
	 * TimeOut in seconds. If Timeout is reach, the data will be destroy!
	 * 
	 * @param timeout
	 */
	public CacheData(int timeout) {
		cacheData = new HashMap<>();
		this.timeout = timeout;
	}
	
	public void put(String key, Object obj) {
		cacheData.put(key, obj);
		updateTimeStamp();
	}
	
	public void remove(String key) {
		cacheData.remove(key);
		updateTimeStamp();
	}
	
	public void replace(String key, Object obj) {
		cacheData.replace(key, obj);
		updateTimeStamp();
	}
	
	public <T> T get(String key, Class<? extends T> clazz) {
		updateTimeStamp();
		return clazz.cast(cacheData.get(key));
	}
	
	public <T> T get(String key, Class<? extends T> clazz, T defaultVal) {
		updateTimeStamp();
		if(!cacheData.containsKey(key)) {
			return defaultVal;
		}
		return clazz.cast(cacheData.get(key));
	}
	
	public boolean containsKey(String key) {
		updateTimeStamp();
		return cacheData.containsKey(key);
	}
	
	public Set<String> getKeySet(){
		updateTimeStamp();
		return cacheData.keySet();
	}
	
	public Collection<Object> get(){
		updateTimeStamp();
		return cacheData.values();
	}
	
	public Set<Entry<String, Object>> entrySet(){
		updateTimeStamp();
		return cacheData.entrySet();
	}
	
	private void updateTimeStamp() {
		timeStamp = (int) (System.currentTimeMillis()/1000);
	}
	
	/**
	 * get the last update/use time for this data
	 * 
	 * @return time in seconds
	 */
	public int getLastTimeStamp() {
		return timeStamp;
	}

	public int getTimeout() {
		return timeout;
	}
}

package de.deminosa.core.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CoreID {

	private static final String UPPER_CASE = "ABCDEFGH";
	private static final String LOWER_CASE = UPPER_CASE.toLowerCase();
	private static final String NUMBERS = "0123456789";
	
	private static final String CHAR = UPPER_CASE + LOWER_CASE + NUMBERS;
	
	public static final char[] CHARS = CHAR.toCharArray();

	private static final Random RANDOM = ThreadLocalRandom.current();
	
	public static String generate(int size) {
		return generate(size, 0);
	}
	
	public static String generate(int size, int slpitAt) {
		String c = "";
		int a = 0;
		for(int i = 0; i < size; i++) {
			a++;
			c = c + CHARS[RANDOM.nextInt(CHARS.length)];
			if(slpitAt > 0 && a == slpitAt) {
				c = c + "-";
				a = 0;
			}
		}
		if(c.endsWith("-")) {
			c = c.substring(0, c.length()-1);
		}
		return c;
	}
	
}

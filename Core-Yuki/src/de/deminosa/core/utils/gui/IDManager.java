package de.deminosa.core.utils.gui;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IDManager {

	public static final char[] CHARS = {
			'a',
			'b',
			'c',
			'd',
			'e',
			'f',
			'g',
			'h',
			'i',
			'j',
			'k',
			'l',
			'm',
			'n',
			'o',
			'p',
			'q',
			'r',
			's',
			't',
			'u',
			'v',
			'w',
			'x',
			'y',
			'z',
			'A',
			'B',
			'C',
			'D',
			'E',
			'F',
			'G',
			'H',
			'I',
			'J',
			'K',
			'L',
			'M',
			'N',
			'O',
			'P',
			'Q',
			'R',
			'S',
			'T',
			'U',
			'V',
			'W',
			'X',
			'Y',
			'Z',
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9',
	};

	private static final Random RANDOM = ThreadLocalRandom.current();

	public static String generateID(int size) {
		String c = "";
		for(int i = 0; i < size; i++) {
			c = c + CHARS[RANDOM.nextInt(CHARS.length)];
		}
		return c;
	}
	
	public static String generateIDWithSlpit(int size, int slpitAt) {
		String c = "";
		int a = 0;
		for(int i = 0; i < size; i++) {
			a++;
			c = c + CHARS[RANDOM.nextInt(CHARS.length)];
			if(a == slpitAt) {
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

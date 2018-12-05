package me.stijn.adventofcode18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class day2 {

	public static void main(String[] args) throws IOException {
		ArrayList<String> list = Utils.getInput(2);
		String result = null;
		long start = System.currentTimeMillis();

		for (String s : list) {
			for (String s2 : list) {
				if (s.equals(s2))
					continue; //string is comparing to itself, cancel
				result = "";
				int same = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == s2.charAt(i)) { //compares if the chars are the same
						result += s.charAt(i);
						same++;
					}
				}
				if (same == s.length() - 1) { //checks if only 1 chars differs
					long timeElapsed = System.currentTimeMillis() - start;

					System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");			
					return;
				}
			}
		}
	}

	public static void part1(String[] args) throws NumberFormatException, IOException { 
		ArrayList<String> list = Utils.getInput(2);
		int result = 0, two = 0, three = 0;
		long start = System.currentTimeMillis();

		for (String s : list) {
			HashMap<Character, Integer> already = new HashMap<Character, Integer>();
			for (int i = 0; i < s.length(); i++) {
				char now = s.charAt(i);
				if (already.containsKey(now)) {
					already.put(now, already.get(now) + 1);
				} else {
					already.put(now, 1);
				}
			}
			if (already.containsValue(2))
				two++;
			if (already.containsValue(3))
				three++;
		}

		result = two * three;

		long timeElapsed = System.currentTimeMillis() - start;

		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

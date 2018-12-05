package me.stijn.adventofcode;

import java.io.IOException;
import java.text.ParseException;

public class day5 {

	public static void main(String[] args) throws IOException {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		String input = Utils.getString(5);
		long start = System.currentTimeMillis();
		char temp = '#';
		int changes = 1, best = 100000;
		for (char c : alphabet) {
			input = Utils.getString(5);
			input = input.replaceAll(Character.toString(Character.toLowerCase(c)), "");
			input = input.replaceAll(Character.toString(Character.toUpperCase(c)), "");
			changes = 1;
			while (changes > 0) {
				changes = 0;
				temp = '#';
				for (int i = 0; i < input.length(); i++) {
					char now = input.charAt(i);
					if ((Character.toUpperCase(temp) == Character.toUpperCase(now))) {
						if ((temp == Character.toLowerCase(temp) && now == Character.toUpperCase(now)
								|| (temp == Character.toUpperCase(temp) && now == Character.toLowerCase(now)))) { 
							input = input.substring(0, i - 1) + input.substring(i + 1);
							changes++;
							break;
						}
					}
					temp = now;
				}
			}
			if (input.length() < best) {
				best = input.length();
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out
				.println("Found answer: " + best + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static void part1(String[] args) throws IOException, InterruptedException, ParseException {
		String input = Utils.getString(5);
		long start = System.currentTimeMillis();
		char temp = '#';
		int changes = 1;
		while (changes > 0) {
			changes = 0;
			temp = '#';
			for (int i = 0; i < input.length(); i++) {
				char now = input.charAt(i);
				if ((Character.toUpperCase(temp) == Character.toUpperCase(now))) { // check if it is the same letter
					if ((temp == Character.toLowerCase(temp) && now == Character.toUpperCase(now)
							|| (temp == Character.toUpperCase(temp) && now == Character.toLowerCase(now)))) { // check
																												// if
																												// one
																												// is
																												// capitalized
																												// and
																												// the
																												// other
																												// isn't
						input = input.substring(0, i - 1) + input.substring(i + 1);
						changes++;
						break;
					}
				}
				temp = now;
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out
				.println("Found answer: " + input.length() + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

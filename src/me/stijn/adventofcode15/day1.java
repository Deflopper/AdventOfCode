package me.stijn.adventofcode15;

import java.io.IOException;

public class day1 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String input = Utils.getString(1);
		long start = System.currentTimeMillis();
		
		int floor = 0;
		int basement = 0;
		
		for (int i = 0; i < input.length(); i++) {
			char now = input.charAt(i);
			if (now == '(') {
				floor++;
			} else if (now == ')') {
				floor--;
			}
			if (floor < 0) {
				basement = i + 1;
				break;
			}
		}
		
		long timeElapsed = System.currentTimeMillis() - start;
		
		System.out.println("Found: " + basement + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static void part1(String[] args) throws NumberFormatException, IOException {
		String input = Utils.getString(1);
		long start = System.currentTimeMillis();
		
		int floor = 0;

		for (int i = 0; i < input.length(); i++) {
			char now = input.charAt(i);
			if (now == '(') {
				floor++;
			} else if (now == ')') {
				floor--;
			}
		}
		
		long timeElapsed = System.currentTimeMillis() - start;
		
		System.out.println("Found: " + floor + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

package me.stijn.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day1 {

	public static void part1(String[] args) throws IOException {
		ArrayList<String> list = Utils.getInput(1);
		int result = 0;

		for (String s : list) {
			result += Integer.valueOf(s);
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		ArrayList<Integer> alreadyseen = new ArrayList<Integer>();
		ArrayList<String> list = Utils.getInput(1);
		
		long start = System.currentTimeMillis();
		
		int result = 0;
		boolean found = false;
		while (!found) {
			for (int i = 0; i < list.size(); i++) {
				result += Integer.valueOf(list.get(i));
				if (alreadyseen.contains(result)) {
					found = true;
					break;
				}
				alreadyseen.add(result);
			}
		}
		
		long timeElapsed = System.currentTimeMillis() - start;
		
		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

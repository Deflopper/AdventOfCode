package me.stijn.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class day2 {

	public static void main(String[] args) throws IOException {
		String result = null;
		ArrayList<String> list = new ArrayList<String>();
		long start = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new FileReader("input\\day2.txt"));
		String line;
		while ((line = in.readLine()) != null) {
			list.add(line);
		}
		in.close();

		for (String s : list) {
			for (String s2 : list) {
				if (s.equals(s2))
					continue;
				result = "";
				int same = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == s2.charAt(i)) {
						result += s.charAt(i);
						same++;
					}
				}
				if (same == s.length() - 1) {
					long finish = System.currentTimeMillis();
					long timeElapsed = finish - start;

					System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");			
					return;
				}
			}
		}
	}

	public static void part1(String[] args) throws NumberFormatException, IOException {
		int result = 0, two = 0, three = 0;
		long start = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new FileReader("input\\day2.txt"));
		String line;
		while ((line = in.readLine()) != null) {
			HashMap<Character, Integer> already = new HashMap<Character, Integer>();
			for (int i = 0; i < line.length(); i++) {
				char now = line.charAt(i);
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
		in.close();

		result = two * three;

		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

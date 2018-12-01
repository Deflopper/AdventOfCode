package me.stijn.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day1 {

	public static void part1(String[] args) throws IOException {
		int result = 0;

		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Stijn\\Desktop\\adventofcode\\day1.txt"));
		String line;
		while ((line = in.readLine()) != null) {
			result += Integer.valueOf(line);
		}
		in.close();
		System.out.println(result);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		ArrayList<Integer> alreadyseen = new ArrayList<Integer>();
		ArrayList<String> list = new ArrayList<String>();
		
		long start = System.currentTimeMillis();
		
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Stijn\\Desktop\\adventofcode\\day1.txt"));
		String line;
		while ((line = in.readLine()) != null) {
			list.add(line);
		}
		in.close();
		
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
		
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		
		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

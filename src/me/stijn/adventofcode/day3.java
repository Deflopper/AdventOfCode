package me.stijn.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class day3 {
	static Integer[][] map = new Integer[2000][2000];

	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<String> list = Utils.getInput(3);
		int result = 0;
		long start = System.currentTimeMillis();
		
		Set<Integer> validId = new HashSet<Integer>();

		for (String s : list) {
			String replaced = s.replaceAll(" ", "");
			String[] split = replaced.split("[#@,:x]");
			validId.add(Integer.valueOf(split[1]));

			int sizex = Integer.valueOf(split[4]);
			int sizey = Integer.valueOf(split[5]);
			for (int i = 1; i <= sizex; i++) {
				for (int j = 1; j <= sizey; j++) {
					int i2 = i + Integer.valueOf(split[2]); // adds the start value of x to the size
					int j2 = j + Integer.valueOf(split[3]); // adds the start value of y to the size
					if (map[i2][j2] == null) { //if there is no value yet in that slot
						map[i2][j2] = Integer.valueOf(split[1]); // sets the value to the id because its empty
					} else if (map[i2][j2] >= 0 && map[i2][j2] != Integer.valueOf(split[1])) {  //checks if its already occupied or somehow comparing to itself
						validId.remove(map[i2][j2]); //removes the overlapping id
						validId.remove(Integer.valueOf(split[1])); //removes its own id
					}
				}
			}
		}
		
		long timeElapsed = System.currentTimeMillis() - start;

		for (Integer i : validId) {
			System.out.println("Found: " + i + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
		}

	}

	public static void part1(String[] args) throws IOException {
		ArrayList<String> list = Utils.getInput(3);
		int result = 0;
		long start = System.currentTimeMillis();
	
		for (String s : list) {
			String replaced = s.replaceAll(" ", "");
			String[] split = replaced.split("[@,:x]");

			int sizex = Integer.valueOf(split[3]);
			int sizey = Integer.valueOf(split[4]);

			for (int i = 1; i <= sizex; i++) {
				for (int j = 1; j <= sizey; j++) {
					int i2 = i + Integer.valueOf(split[1]); // adds the start value to the size
					int j2 = j + Integer.valueOf(split[2]); // adds the start value to the size
					if (map[i2][j2] == null) {
						map[i2][j2] = 1;
					} else {
						map[i2][j2]++;
					}
				}
			}
		}

		// loop to check which have more then 2 layers
		for (int i = 0; i < 2000; i++) {
			for (int j = 0; j < 2000; j++) {
				if (map[i][j] != null && map[i][j] >= 2) {
					result++;
				}
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;

		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

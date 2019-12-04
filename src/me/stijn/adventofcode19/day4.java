package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.stream.IntStream;

public class day4 {
	
	public static void main(String[] args) throws IOException {
		String[] input = Utils.getString(4).split("-");
		int startRange = Integer.valueOf(input[0]),endRange = Integer.valueOf(input[1]), amount = 0;
		
		while (startRange != endRange) {
			char[] number = String.valueOf(startRange).toCharArray();
			boolean dbl = false;
			int temp = -1, streak = 0, dblInt = -1;
			for (int j = 0; j < number.length;j++) {
				int i = Character.getNumericValue(number[j]);
				if (i == temp) {
					dbl = true;
					if (dblInt == -1) dblInt = i;
					if (++streak >= 2) {
						if (dblInt == i) {
							dbl = false;
							dblInt = -1;
						} else 
							dbl = true;
					}
				} else streak = 0;
				if (i < temp) {
					dbl = false; //always fail if the values is not increasing
					break;
				}
				temp = i;
			}
			if (dbl)
				amount++;
			startRange++;
		}
		System.out.println("Amount of possible passphrases part 2: " + amount); //1277
	}
	
	public static void part1(String[] args) throws IOException {
		String[] input = Utils.getString(4).split("-");
		int startRange = Integer.valueOf(input[0]),endRange = Integer.valueOf(input[1]), amount = 0;
		
		while (startRange != endRange) {
			char[] number = String.valueOf(startRange).toCharArray();
			boolean dbl = false;
			int temp = -1;
			for (char c : number) {
				int i = Character.getNumericValue(c);
				if (i == temp) {
					dbl = true;
				}
				if (i < temp) {
					dbl = false; //always fail if the values is not increasing
					break;
				}
				temp = i;
			}
			if (dbl)
				amount++;
			startRange++;
		}
		System.out.println("Amount of possible passphrases part 1: " + amount);
	}

}

package me.stijn.adventofcode15;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day2 {
	
	public static void main(String[] args) throws IOException{
		ArrayList<String> input = Utils.getInput(2);
		long start = System.currentTimeMillis();
		
		int sum = 0;
		
		for (String s : input) {
			String[] split = s.split("x");
			int l = Integer.valueOf(split[0]), w = Integer.valueOf(split[1]), h = Integer.valueOf(split[2]); //save all variables
			
	        int[] dimensions = new int[] {l, w, h};
	        Arrays.sort(dimensions);
	        
	        sum += dimensions[0] * 2;
	        sum += dimensions[1] * 2;
	        
	        sum += l * w * h;
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + sum + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}
	
	public static void part1(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(2);
		long start = System.currentTimeMillis();
		
		int sum = 0;
		
		for (String s : input) {
			String[] split = s.split("x");
			int l = Integer.valueOf(split[0]), w = Integer.valueOf(split[1]), h = Integer.valueOf(split[2]); //save all variables
			
			sum += 2 * (l * w);
			sum += 2 * (w * h);
			sum += 2 * (h * l);
			
	        int[] dimensions = new int[] {l, w, h};
	        Arrays.sort(dimensions);
	        
	        sum += dimensions[0] * dimensions[1];
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + sum + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}


}

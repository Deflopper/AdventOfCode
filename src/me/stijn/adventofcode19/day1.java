package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;

public class day1 {
	
	public static void main(String[] args) throws IOException {
		int sum = 0;
		ArrayList<String> input = Utils.getInput(1);
		for (String s : input) {
			int i = Integer.valueOf(s);
			i /= 3;
			i-=2;
			sum += i;
			for (;;) {
				i /= 3;
				i-=2;
				if (i <= 0)
					break;
				sum += i;
			}
		}
		
		System.out.println("Total: " + sum);
	} 
	
	public static void part1(String[] args) throws IOException {
		int sum = 0;
		ArrayList<String> input = Utils.getInput(1);
		for (String s : input) {
			int i = Integer.valueOf(s);
			i /= 3;
			i-=2;
			sum += i;
		}
		
		System.out.println("Total: " + sum);
	} 
	
	
}

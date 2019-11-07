package me.stijn.adventofcode17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day5 {

	static ArrayList<Integer> input = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		int pointer = 0;
		int steps = 0;
		input = Utils.getInputInteger(5);
		
		while (pointer < input.size()) {
			int orginVal = input.get(pointer);
			if (orginVal >= 3) 
				input.set(pointer, orginVal - 1);
			else
				input.set(pointer, orginVal + 1);
			pointer += orginVal;
			steps++;
		}
		System.out.println("Amount of steps: " + steps);
	}

	public static void part1(String[] args) throws IOException {
		int pointer = 0;
		int steps = 0;
		input = Utils.getInputInteger(5);
		
		while (pointer < input.size()) {
			int orginVal = input.get(pointer);
			input.set(pointer, orginVal + 1);
			pointer += orginVal;
			steps++;
		}
		System.out.println("Amount of steps: " + steps);
	}
}

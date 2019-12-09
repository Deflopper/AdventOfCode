package me.stijn.adventofcode19;

import java.io.IOException;

public class day9 {
	
	public static void main(String[] args) throws IOException {
		String input = Utils.getString(9);
		IntcodeComputer computer = new IntcodeComputer(input);
		System.out.println("Part 1: " + computer.run(new Integer[] {1}).get(1));
		System.out.println("Part 2: " + computer.run(new Integer[] {2}).get(1));
	}

}

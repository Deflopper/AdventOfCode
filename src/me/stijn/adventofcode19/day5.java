package me.stijn.adventofcode19;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class day5 {
	
	public static void main(String[] args) throws IOException {
		String program = Utils.getString(5);
		IntcodeComputer computer = new IntcodeComputer(program);
		ArrayList<BigInteger> part1 = computer.run(new Integer[] { 1 });
		ArrayList<BigInteger> part2 = computer.run(new Integer[] { 5 });
		System.out.println("Part 1: " + part1.get(part1.size()-1));
		System.out.println("Part 2: " + part2.get(part2.size()-1));
	}

}

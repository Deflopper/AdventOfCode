package me.stijn.adventofcode19;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class day15 {
	
	public static void main(String[] args) throws IOException {
		String in = Utils.getString(15);
		IntcodeComputer computer = new IntcodeComputer(in);
		
		
		ArrayList<BigInteger> output = null;
		for (;;) {
			Integer[] arr = new Integer[1];
			
			
			output = computer.run(arr);
			if (output.get(0).intValue() == 99)
				break;
			
		}
		
	}

}

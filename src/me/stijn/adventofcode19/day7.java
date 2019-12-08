package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class day7 {
	
	public static void main(String[] args) throws IOException { //part 2 not working yet
		String input = Utils.getString(7);
		//IntcodeComputer computer = new IntcodeComputer(input);
		ArrayList<IntcodeComputer> computers = new ArrayList();
		int highest = Integer.MIN_VALUE;
		Integer[] highestSettings = new Integer[4];
		
		ArrayList<Integer[]> settings = new ArrayList();
		
		phasegen: for (int i = 55555; i <= 99999; i++) { //generate phase settings posibilities
			String phase = String.valueOf(i);
			if (phase.length() != 5)
				continue;
			List<Integer> charArr = new ArrayList();
			for (int j = 0; j < 5;j++){
				char c = phase.charAt(j);
				if ((Character.getNumericValue(c) < 5 || Character.getNumericValue(c) > 9) || charArr.contains(c - '0'))
					continue phasegen;
				else
					charArr.add(c - '0');
			}
			settings.add(charArr.toArray(new Integer[4]));
		}
		
		for (int i = 0; i < 5; i++) {
			computers.add(new IntcodeComputer(input));
		}
		for (Integer[] setting : settings) {
			int output = 0;
			for (int i = 0; i < 5; i++) {
				output = computers.get(i).run(new Integer[] {setting[i], output}).get(0);
				System.out.println(output);
			}
			
			if (output > highest){
				highest = output;
				highestSettings = setting.clone();
			}
		}

		System.out.println("Highest: " + highest);
	}


	
	public static void part1(String[] args) throws IOException {
		String input = Utils.getString(7);
		IntcodeComputer computer = new IntcodeComputer(input);
		int highest = Integer.MIN_VALUE;
		Integer[] highestSettings = new Integer[4];
		
		ArrayList<Integer[]> settings = new ArrayList();
		
		phasegen: for (int i = 0; i <= 44444; i++) { //generate phase settings posibilities
			String phase = String.valueOf(i);
			if (phase.length() != 5)
				continue;
			List<Integer> charArr = new ArrayList();
			for (int j = 0; j < 5;j++){
				char c = phase.charAt(j);
				if (Character.getNumericValue(c) > 4 || charArr.contains(c - '0'))
					continue phasegen;
				else
					charArr.add(c - '0');
			}
			settings.add(charArr.toArray(new Integer[4]));
		}
		
		
		for (Integer[] setting : settings) {
			int output = 0;
			for (int i = 0; i < 5; i++) {
				output = computer.run(new Integer[] {setting[i], output}).get(0);
			}
			if (output > highest){
				highest = output;
				highestSettings = setting.clone();
			}
		}

		System.out.println("Highest: " + highest);
	}

}

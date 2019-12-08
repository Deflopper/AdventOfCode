package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class day7 {

	public static void main(String[] args) throws IOException {
		String input = Utils.getString(7);

		ArrayList<Integer[]> settings = new ArrayList();

		phasegen: for (int i = 55555; i <= 99999; i++) { // generate phase settings posibilities
			String phase = String.valueOf(i);
			if (phase.length() != 5)
				continue;
			List<Integer> charArr = new ArrayList();
			for (int j = 0; j < 5; j++) {
				char c = phase.charAt(j);
				if ((Character.getNumericValue(c) < 5 || Character.getNumericValue(c) > 9) || charArr.contains(c - '0'))
					continue phasegen;
				else
					charArr.add(c - '0');
			}
			settings.add(charArr.toArray(new Integer[4]));
		}

		ArrayList<Amplifier> amps = new ArrayList();
		int highest = Integer.MIN_VALUE;
		
		for (Integer[] setting : settings) {
			amps.clear();
			for (int i = 0; i < 5;i++) {
				Amplifier amp = new Amplifier(setting[i], input);
				amp.computer.setAmp(true);
				amps.add(amp);
			}
			int output = 0;
			while (amps.stream().allMatch(it -> !it.ended)) { //while no amp is giving the complete exit signal
				for (Amplifier amp : amps) 
					output = amp.run(output);
			}
			
			if (output > highest) 
				highest = output;
		}
		
		System.out.println("Highest possible output: " + highest);

	}
	
	public static class Amplifier {
		
		private boolean ended = false;
		private IntcodeComputer computer;
		private int phase;
		private Integer output;
		
		public Amplifier(int phase, String program) {
			computer = new IntcodeComputer(program);
			this.phase = phase;
			computer.setPhase(phase);
		}
		
		public int run(int input) {
			ArrayList<Integer> result = computer.run(new Integer[] {input});
			if (result.get(0) == 99) 
				ended = true;
			if (result.size() > 1)
				output = result.get(1);
			return output;
 		}
	}

	public static void part1(String[] args) throws IOException {
		String input = Utils.getString(7);
		IntcodeComputer computer = new IntcodeComputer(input);
		int highest = Integer.MIN_VALUE;
		Integer[] highestSettings = new Integer[4];

		ArrayList<Integer[]> settings = new ArrayList();

		phasegen: for (int i = 0; i <= 44444; i++) { // generate phase settings posibilities
			String phase = String.valueOf(i);
			if (phase.length() != 5)
				continue;
			List<Integer> charArr = new ArrayList();
			for (int j = 0; j < 5; j++) {
				char c = phase.charAt(j);
				if (Character.getNumericValue(c) > 4 || charArr.contains(c - '0'))
					continue phasegen;
				charArr.add(c - '0');
			}
			settings.add(charArr.toArray(new Integer[4]));
		}

		for (Integer[] setting : settings) {
			int output = 0;
			for (int i = 0; i < 5; i++) {
				output = computer.run(new Integer[] { setting[i], output }).get(0);
			}
			if (output > highest) {
				highest = output;
				highestSettings = setting.clone();
			}
		}

		System.out.println("Highest: " + highest);
	}

}

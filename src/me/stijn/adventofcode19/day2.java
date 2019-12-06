package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;

public class day2 {
	
	public static void main(String[] args) throws IOException {
		String[] program = Utils.getString(2).split(",");
		ArrayList<Integer> originArr = new ArrayList<>();
		for (String s : program)
			originArr.add(Integer.valueOf(s));
		
		ArrayList<Integer> intArr;
		int noun = 0, verb = 0, pointer = 0;
		
		for (;;) {
			intArr = (ArrayList<Integer>) originArr.clone();
			intArr.set(1, noun);
			intArr.set(2, verb);
			pointer = 0;
			
			loop: for(;;) {
				switch(intArr.get(pointer)) {
				case 1:
				case 2:
					int val1 = intArr.get(intArr.get(pointer + 1)), val2 = intArr.get(intArr.get(pointer + 2));
					int sum = intArr.get(pointer) == 1? val1 + val2 : val1 * val2;
					intArr.set(intArr.get(pointer + 3), sum);
					break;
				case 99:
					break loop;
				}
				pointer+=4;
			}
			
			if (intArr.get(0) == 19690720) 
				break;
			
			noun++;
			if (noun > 99) {
				verb++;
				noun = 0;
			}
		}

		System.out.println("Answer of part 2: " + (100 * noun + verb));
	}
	
	public static void part1(String[] args) throws IOException {
		String[] program = Utils.getString(2).split(",");
		ArrayList<Integer> intArr = new ArrayList<>();
		for (String s : program)
			intArr.add(Integer.valueOf(s));
		
		intArr.set(1, 12);
		intArr.set(2, 2);
		
		int pointer = 0;
		
		loop: for(;;) {
			switch(intArr.get(pointer)) {
			case 1:
			case 2:
				int val1 = intArr.get(intArr.get(pointer + 1)), val2 = intArr.get(intArr.get(pointer + 2));
				int sum = intArr.get(pointer) == 1? val1 + val2 : val1 * val2;
				intArr.set(intArr.get(pointer + 3), sum);
				break;
			case 99:
				break loop;
			}
			
			pointer+=4;
		}
		
		System.out.println("Value of index 0: " + intArr.get(0));
	}

}

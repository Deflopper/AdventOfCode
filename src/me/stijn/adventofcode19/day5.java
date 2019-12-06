package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class day5 {
	
	public static void main(String[] args) throws IOException {	//part 2 not working yet
		String input = Utils.getString(5);
		String[] program = input.split(",");
		ArrayList<Integer> intArr = new ArrayList<>();
		for (String s : program) {
			intArr.add(Integer.valueOf(s));
		}
		
		Scanner in = new Scanner(System.in);
		
		int pointer = 0;
		
		loop: for(;;) {
			char[] instruction = intArr.get(pointer).toString().toCharArray();
			int opcode = instruction.length > 1 ?Integer.valueOf(Character.getNumericValue(instruction[instruction.length -2]) + 
					"" + Character.getNumericValue(instruction[instruction.length -1])):
						Character.getNumericValue(instruction[0]);
			
			HashMap<Integer, Integer> modes = new HashMap();
			for (int i = instruction.length - 3; i >= 0; i--) {
				modes.put(instruction.length - 3 - i, Character.getNumericValue(instruction[i]));
			}
			
			Integer val1 = getParameter(modes, intArr, pointer, 1);
			Integer val2 = getParameter(modes, intArr, pointer, 2);
			switch(opcode) {
			case 1:
			case 2:
				int sum = opcode == 1? val1 + val2 : val1 * val2;
				intArr.set(intArr.get(pointer + 3), sum);
				pointer+=4;
				break;
			case 3:
				System.out.println("Input:");
				int val = Integer.valueOf(in.nextLine());
				if (modes.containsKey(0) && modes.get(0) == 1) 
					intArr.set(pointer + 1, val);
				else 
					intArr.set(intArr.get(pointer + 1), val);
				pointer+=2;
				break;
			case 4:
				int output = (modes.containsKey(0) && modes.get(0) == 1) ? intArr.get(pointer + 1) : intArr.get(intArr.get(pointer + 1));
				System.out.println("Output: " + output);
				pointer+=2;
				break;
			case 5:
			case 6:
				if (opcode == 5 ? val1 != 0 : val1 == 0) 
					pointer = val2;
				else 
					pointer+=3;
				break;
			case 7:
			case 8:
				int val3 = intArr.get(pointer + 3);

				if (opcode == 7 ? val1 < val2 : val1.equals(val2)) 
					intArr.set(val3, 1);
				else 
					intArr.set(val3, 0);
				pointer += 4;
				break;
			case 99:
				break loop;
			default:
				break loop;
			}
		}
		in.close();
	}
	
	public static Integer getParameter(HashMap<Integer, Integer> modes, ArrayList<Integer> arr, int pointer, int offset) {
		if (arr.size() > pointer + offset && arr.get(pointer + offset) != null)
			return (modes.containsKey(offset-1) && modes.get(offset-1) == 1) ? arr.get(pointer + offset) : arr.get(arr.get(pointer + offset));
		return -1;
	}
	
	public static void part1(String[] args) throws IOException {
		String input = Utils.getString(5);
		String[] program = input.split(",");
		ArrayList<Integer> intArr = new ArrayList<>();
		for (String s : program) {
			intArr.add(Integer.valueOf(s));
		}
		
		Scanner in = new Scanner(System.in);
		
		int pointer = 0;
		
		loop: for(;;) {
			char[] instruction = intArr.get(pointer).toString().toCharArray();
			int opcode = instruction.length > 1 ?Integer.valueOf(Character.getNumericValue(instruction[instruction.length -2]) + 
					"" + Character.getNumericValue(instruction[instruction.length -1])):
						Character.getNumericValue(instruction[0]);
			
			HashMap<Integer, Integer> modes = new HashMap();
			for (int i = instruction.length - 3; i >= 0; i--) {
				modes.put(instruction.length - 3 - i, Character.getNumericValue(instruction[i]));
			}
			
			switch(opcode) {
			case 1:
			case 2:
				int val1 = (modes.containsKey(0) && modes.get(0) == 1) ? intArr.get(pointer + 1) : intArr.get(intArr.get(pointer + 1));
				int val2 = (modes.containsKey(1) && modes.get(1) == 1) ? intArr.get(pointer + 2) : intArr.get(intArr.get(pointer + 2));
				int sum = opcode == 1? val1 + val2 : val1 * val2;
				intArr.set(intArr.get(pointer + 3), sum);
				pointer+=4;
				break;
			case 3:
				System.out.println("Input:");
				int val = Integer.valueOf(in.nextLine());
				if (modes.containsKey(0) && modes.get(0) == 1)
					intArr.set(pointer + 1, val);
				else
					intArr.set(intArr.get(pointer + 1), val);
				pointer+=2;
				break;
			case 4:
				int output = (modes.containsKey(0) && modes.get(0) == 1) ? intArr.get(pointer + 1) : intArr.get(intArr.get(pointer + 1));
				System.out.println("Output: " + output);
				pointer+=2;
				break;
			case 99:
				break loop;
			}
		}
		in.close();
	}

}

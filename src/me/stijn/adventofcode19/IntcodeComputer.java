package me.stijn.adventofcode19;

import java.util.ArrayList;
import java.util.HashMap;

public class IntcodeComputer {

	ArrayList<Integer> intArr,originArr;
	int pointer, inputIndex;
	Integer[] inputs;
	ArrayList<Integer> output = new ArrayList();

	public IntcodeComputer(String program) {
		String[] split = program.split(",");
		ArrayList<Integer> intArr = new ArrayList<>();
		for (String s : split) {
			intArr.add(Integer.valueOf(s));
		}
		this.intArr = intArr;
		this.originArr = (ArrayList<Integer>) intArr.clone();
	}

	public ArrayList<Integer> run(Integer[] inputs) {
		pointer = 0;
		inputIndex = 0;
		intArr = (ArrayList<Integer>) originArr.clone();
		output.clear();
		this.inputs = inputs;
		for (;;) {
			if (cycle() == 99)
				break;
		}
		return (ArrayList<Integer>) output.clone();
	}
	
	public ArrayList<Integer> run(Integer[] inputs, Boolean clearMem) {
		pointer = 0;
		inputIndex = 0;
		if (clearMem)
			intArr = (ArrayList<Integer>) originArr.clone();
		output.clear();
		this.inputs = inputs;
		for (;;) {
			if (cycle() == 99)
				break;
		}
		return (ArrayList<Integer>) output.clone();
	}

	private int cycle() {
		char[] instruction = intArr.get(pointer).toString().toCharArray();
		int opcode = instruction.length > 1
				? Integer.valueOf(Character.getNumericValue(instruction[instruction.length - 2]) + ""
						+ Character.getNumericValue(instruction[instruction.length - 1]))
				: Character.getNumericValue(instruction[0]);

		HashMap<Integer, Integer> modes = new HashMap();
		for (int i = instruction.length - 3; i >= 0; i--) {
			modes.put(instruction.length - 3 - i, Character.getNumericValue(instruction[i]));
		}
		Integer val1 = getParameter(modes, intArr, pointer, 1);
		Integer val2 = getParameter(modes, intArr, pointer, 2);
		switch (opcode) {
		case 1:
		case 2:
			int sum = opcode == 1 ? val1 + val2 : val1 * val2;
			intArr.set(intArr.get(pointer + 3), sum);
			pointer += 4;
			break;
		case 3:
			int val = inputs.length > inputIndex ? inputs[inputIndex] : 0;
			System.out.println("Input: " + val);
			inputIndex++;
			if (modes.containsKey(0) && modes.get(0) == 1)
				intArr.set(pointer + 1, val);
			else
				intArr.set(intArr.get(pointer + 1), val);
			pointer += 2;
			break;
		case 4:
			int output = (modes.containsKey(0) && modes.get(0) == 1) ? intArr.get(pointer + 1)
					: intArr.get(intArr.get(pointer + 1));
			System.out.println("Output: " + output);
			this.output.add(output);
			pointer += 2;
			break;
		case 5:
		case 6:
			if (opcode == 5 ? val1 != 0 : val1 == 0)
				pointer = val2;
			else
				pointer += 3;
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
		}
		return opcode;
	}

	public static Integer getParameter(HashMap<Integer, Integer> modes, ArrayList<Integer> arr, int pointer,
			int offset) {
		if (arr.size() > pointer + offset && arr.get(pointer + offset) != null)
			return (modes.containsKey(offset - 1) && modes.get(offset - 1) == 1) ? arr.get(pointer + offset)
					: arr.size() > (arr.get(pointer + offset)) ? arr.get(arr.get(pointer + offset)) : -1;
		return -1;
	}
}

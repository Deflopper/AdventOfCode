package me.stijn.adventofcode19;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class IntcodeComputer {
	private ArrayList<BigInteger> intArr, originArr;
	private int pointer = 0, inputIndex = 0;
	private Integer[] inputs;
	private ArrayList<BigInteger> output = new ArrayList();
	private BigInteger relativeBase = Big(0);

	private int phase = -1;
	private boolean isAmp = false;

	public IntcodeComputer(String program) {
		String[] split = program.split(",");
		ArrayList<BigInteger> intArr = new ArrayList<>();
		for (String s : split) {
			intArr.add(BigInteger.valueOf(Long.valueOf(s)));
		}
		this.intArr = intArr;
		this.originArr = (ArrayList<BigInteger>) intArr.clone();
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getPhase() {
		return phase;
	}

	public void setAmp(boolean bool) {
		this.isAmp = bool;
	}

	public boolean isAmp() {
		return isAmp;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<BigInteger> run(Integer[] inputs) {
		if (!isAmp) {
			pointer = 0;
			intArr = (ArrayList<BigInteger>) originArr.clone();
		}
		relativeBase = Big(0);
		output.clear();
		inputIndex = 0;
		this.inputs = inputs;
		for (;;) {
			int res = cycle();
			if (res == 99) // stopping whole program
				return new ArrayList<BigInteger>() {{
						add(Big(99));
						addAll(output);
					}
				};
			if (res == 98) // continue with next amp
				return new ArrayList<BigInteger>() {
					{
						add(Big(4));
						add(output.get(0));
					}
				};
		}
	}

	private int cycle() {
		char[] instruction = intArr.get(pointer).toString().toCharArray();
		int opcode = instruction.length <= 1 ? Character.getNumericValue(instruction[0])
				: Integer.valueOf(Character.getNumericValue(instruction[instruction.length - 2]) + ""
						+ Character.getNumericValue(instruction[instruction.length - 1]));
		HashMap<Integer, Integer> modes = new HashMap();
		for (int i = instruction.length - 3; i >= 0; i--) {
			modes.put(instruction.length - 3 - i, Character.getNumericValue(instruction[i]));
		}

		BigInteger val1 = getParameter(modes, 1, false), val2 = getParameter(modes, 2, false);
		switch (opcode) {
		case 1:
		case 2:
			BigInteger sum;
			if (opcode == 1) {
				sum = val1.add(val2);
			} else {
				sum = val1.multiply(val2);
			}
			set(getParameter(modes, 3, true).intValue(), sum);
			pointer += 4;
			break;
		case 3:
			BigInteger val = Big(inputs.length > inputIndex ? inputs[inputIndex] : 0);
			if (phase != -1 && inputIndex == 0) {
				val = Big(phase);
				inputIndex = phase = -1;
			}
			inputIndex++;
			set(getParameter(modes, 1, true).intValue(), val);
			pointer += 2;
			break;
		case 4:
			BigInteger output = getParameter(modes, 1, false);
			//System.out.println("Output: " + output);
			this.output.add(output);

			pointer += 2;
			if (isAmp)
				return 98;
			break;
		case 5:
		case 6:
			if (opcode == 5 ? val1.compareTo(Big(0)) != 0 : val1.compareTo(Big(0)) == 0)
				pointer = val2.intValue();
			else
				pointer += 3;
			break;
		case 7:
		case 8:
			BigInteger val3 = getParameter(modes, 3, true);
			if (opcode == 7 ? val1.compareTo(val2) == -1 : val1.equals(val2))
				set(val3.intValue(), Big(1));
			else
				set(val3.intValue(), Big(0));
			pointer += 4;
			break;
		case 9:
			relativeBase = relativeBase.add(val1);
			pointer += 2;
			break;
		}
		return opcode;
	}

	private void set(int index, BigInteger sum) {
		while (intArr.size() <= index)
			intArr.add(null);
		intArr.set(index, sum);
	}

	public BigInteger getParameter(HashMap<Integer, Integer> modes, int offset, boolean output) {
		if (intArr.size() <= pointer + offset || intArr.get(pointer + offset) == null)
			return Big(-1);
		int mode = 0;
		if (modes.containsKey(offset - 1))
			mode = modes.get(offset - 1);
		switch (mode) {
		case 0:
			if (output)
				return intArr.get(pointer + offset);
			if (Big(intArr.size()).compareTo(intArr.get(pointer + offset)) == 1
					&& intArr.get(intArr.get(pointer + offset).intValue()) != null) {
				return intArr.get(intArr.get(pointer + offset).intValue());
			} else
				return Big(0);
		case 1:
			if (output)
				return Big(pointer + offset);
			return intArr.get(pointer + offset);
		case 2:
			if (output)
				return intArr.get(pointer + offset).add(relativeBase);
			return intArr.get(intArr.get(pointer + offset).add(relativeBase).intValue());
		}
		return Big(-1);
	}

	public static BigInteger Big(int i) {
		return BigInteger.valueOf(i);
	}

}
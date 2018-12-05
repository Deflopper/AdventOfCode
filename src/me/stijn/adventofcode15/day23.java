package me.stijn.adventofcode15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day23 {
	static Register a = new Register();
	static Register b = new Register();
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(23);
		long start = System.currentTimeMillis();
		
		a.value = 1;

		for (int i = 0; i <= input.size();) {
			String[] split = input.get(i).split(" ");
			
			switch (split[0]) {
			case "hlf":
				getRegister(split[1]).half();
				i++;
				break;
			case "tpl":
				getRegister(split[1]).triple();
				i++;
				break;
			case "inc":
				getRegister(split[1]).increase();
				i++;
				break;
			case "jmp":
				i += Integer.valueOf(split[1]);
				break;
			case "jie":
				if (getRegister(split[1].replaceAll(",", "")).isEven()) {
					i += Integer.valueOf(split[2]);
				} else {
					i++;
				}
				break;
			case "jio":
				if (getRegister(split[1].replaceAll(",", "")).getValue() == 1) {
					i += Integer.valueOf(split[2]);
				} else {
					i++;
				}
				break;
			}
			if (i >= input.size()) {
				System.out.println("STOP");
				break;
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + b.getValue() + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static void part1(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(23);
		long start = System.currentTimeMillis();

		for (int i = 0; i <= input.size();) {
			String[] split = input.get(i).split(" ");
			//System.out.println("executed: " + split[0] + " : " + split[1]);
			switch (split[0]) {
			case "hlf":
				getRegister(split[1]).half();
				i++;
				break;
			case "tpl":
				getRegister(split[1]).triple();
				i++;
				break;
			case "inc":
				getRegister(split[1]).increase();
				i++;
				break;
			case "jmp":
				i += Integer.valueOf(split[1]);
				break;
			case "jie":
				if (getRegister(split[1].replaceAll(",", "")).isEven()) {
					i += Integer.valueOf(split[2]);
				} else {
					i++;
				}
				break;
			case "jio":
				if (getRegister(split[1].replaceAll(",", "")).getValue() == 1) {
					i += Integer.valueOf(split[2]);
				} else {
					i++;
				}
				break;
			}
			if (i >= input.size()) {
				System.out.println("STOP");
				break;
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + b.getValue() + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static Register getRegister(String s) {
		if (s.equals("a")) {
			return a;
		} else if (s.equals("b")) {
			return b;
		}
		return null;
	}

	static public class Register {
		int value = 0;

		public Register() {

		}

		public void half() {
			value = value / 2;
		}

		public void triple() {
			value *= 3;
		}

		public void increase() {
			value++;
		}

		public boolean isEven() {
			return ((value % 2) == 0);
		}

		public int getValue() {
			return value;
		}

	}
}

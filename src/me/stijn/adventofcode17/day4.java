package me.stijn.adventofcode17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day4 {

	static ArrayList<String> input = new ArrayList<>();
	
	static Map<String, ArrayList<String>> alreadyUsed = new HashMap<>();
	
	static Map<String, ArrayList<char[]>> alreadyUsedTwo = new HashMap<>();

	public static void part1(String[] args) throws IOException {
		input = Utils.getInput(4);
		int valid = 0;

		for (String s : input) {
			String[] split = s.split(" ");
			boolean failed = false;
			for (String word : split) {
				if (alreadyUsed.containsKey(s) && alreadyUsed.get(s).contains(word)) {
					// invalid passphrase
					failed = true;
					break;
				} else {
					ArrayList<String> list = new ArrayList<>();
					if (alreadyUsed.containsKey(s))
						list = alreadyUsed.get(s);
					list.add(word);
					alreadyUsed.put(s, list);
				}
			}
			if (!failed)
				valid++;
		}
		System.out.println("Valid passphrases: " + valid);
	}

	public static void main(String[] args) throws IOException {
		input = Utils.getInput(4);
		boolean failed = false;
		int valid = 0;
		
		for (String s : input) {
			String[] split = s.split(" ");
			for (String word : split) {
				
				
			}
			if (!failed)
				valid++;
		}
		
		System.out.println("Valid passphrases: " + valid);
	}

}

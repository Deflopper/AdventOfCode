package me.stijn.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	/**
	 * Get the input in arraylist
	 * @param i Day 
	 * @return ArrayList with input
	 * @throws IOException
	 */
	public static ArrayList<String> getInput(Integer i) throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader("input\\day" + i + ".txt"));
		String line;
		while ((line = in.readLine()) != null) {
			list.add(line);
		}
		in.close();
		return list;
	}

}

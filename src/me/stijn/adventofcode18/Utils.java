package me.stijn.adventofcode18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	/**
	 * Get the input in arraylist
	 * @param i Day 
	 * @return ArrayList with input
	 * @throws IOException
	 */
	public static ArrayList<String> getInput(Integer i) throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader("input\\18\\day" + i + ".txt"));
		String line;
		while ((line = in.readLine()) != null) {
			list.add(line);
		}
		in.close();
		return list;
	}
	
	public static String getString(Integer i) throws IOException {
		Path path = Paths.get("input\\18\\day" + i + ".txt");
		List<String> lines = Files.readAllLines(path);
		return lines.get(0);
	}

}

package me.stijn.adventofcode15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day3 {
	
	public static void main(String[] args) throws IOException {
		String input = Utils.getString(3);
		long start = System.currentTimeMillis();

		Integer[][] map = new Integer[1000][1000];

		int houses = 0;
		int x = 500, y = 500;
		int robotx = 500, roboty = 500;
		int santax = 500, santay = 500;

		map[500][500] = 1;

		boolean santa = true;
		for (int i = 0; i < input.length(); i++) {
			char now = input.charAt(i);
			santa = !santa;
			
			if (santa) {
				x = santax;
				y = santay;
			} else {
				x = robotx;
				y= roboty;
			}
			
			switch (now) {
			case '>':
				y++;
				break;
			case 'v':
				x--;
				break;
			case '<':
				y--;
				break;
			case '^':
				x++;
				break;
			}

			if (map[x][y] == null) {
				map[x][y] = 1;
			} else {
				map[x][y]++;
			}
			
			if (santa) {
				santax = x;
				santay = y;
			} else {
				robotx = x;
				roboty = y;
			}
		}

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (map[i][j] != null && map[i][j] > 0) {
					houses++;
				}
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + houses + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static void part1(String[] args) throws IOException {
		String input = Utils.getString(3);
		long start = System.currentTimeMillis();

		Integer[][] map = new Integer[1000][1000];

		int houses = 0;
		int x = 500, y = 500;

		map[500][500] = 1;

		for (int i = 0; i < input.length(); i++) {
			char now = input.charAt(i);
			switch (now) {
			case '>':
				y++;
				break;
			case 'v':
				x--;
				break;
			case '<':
				y--;
				break;
			case '^':
				x++;
				break;
			}

			if (map[x][y] == null) {
				map[x][y] = 1;
			} else {
				map[x][y]++;
			}
		}

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (map[i][j] != null && map[i][j] > 0) {
					houses++;
				}
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + houses + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

}

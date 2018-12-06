package me.stijn.adventofcode18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class day6 {
	static Integer[][] map = new Integer[1000][1000];
	static ArrayList<Area> areas = new ArrayList<Area>();

	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<String> list = Utils.getInput(6);
		long start = System.currentTimeMillis();
		int region = 0;
		ArrayList<Integer> infinite = new ArrayList<Integer>();

		// generate the area's
		for (int i = 0; i < list.size(); i++) {
			String[] split = list.get(i).split(", ");
			new Area(i, Integer.valueOf(split[0]), Integer.valueOf(split[1]));
		}

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				int sum = 0;
				for (int i2 = 0; i2 < areas.size(); i2++) {
					double distance = Math.abs((i - areas.get(i2).getX())) + Math.abs((j - areas.get(i2).getY()));
					sum += distance;
				}
				if (sum < 10000) {
					region++;
				}
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + region + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}
	
	
	
	
	

	public static void part1(String[] args) throws IOException, InterruptedException {
		ArrayList<String> list = Utils.getInput(6);
		long start = System.currentTimeMillis();
		int result = 0;
		ArrayList<Integer> infinite = new ArrayList<Integer>();

		for (int i = 0; i < list.size(); i++) {
			String[] split = list.get(i).split(", ");
			new Area(i, Integer.valueOf(split[0]), Integer.valueOf(split[1]));
		}

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				Area top = areas.get(0);
				for (int i2 = 0; i2 < areas.size(); i2++) {
					double topdistance = Math.abs((i - top.getX())) + Math.abs((j - top.getY()));
					double distance = Math.abs((i - areas.get(i2).getX())) + Math.abs((j - areas.get(i2).getY()));
					if (distance < topdistance) {
						top = areas.get(i2);
					}
				}

				map[i][j] = top.getID();
				//checks if it is at the edge and there by probably infinite
				if (i == 0 || i == 999 || j == 0 || j == 999) {
					if (!infinite.contains(top.getID())) {
						infinite.add(top.getID());
					}
				}

				// checks if there are any just as close by
				double topdistance = Math.abs((i - top.getX())) + Math.abs((j - top.getY()));
				for (int i2 = 0; i2 < areas.size(); i2++) {
					double distance = Math.abs((i - areas.get(i2).getX())) + Math.abs((j - areas.get(i2).getY()));
					if (topdistance == distance && areas.get(i2) != top) {
						map[i][j] = -1;
						break;
					}
				}

			}
		}

		// checks for the biggest finite area
		HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (map[i][j] >= 0) {
					if (!infinite.contains(map[i][j])) {
						if (!data.containsKey(map[i][j])) {
							data.put(map[i][j], 1);
						} else {
							data.put(map[i][j], data.get(map[i][j]) + 1);
						}
					}
				}
			}
		}

		int best = 0;
		for (Integer i : data.keySet()) {
			if (data.get(i) > best) {
				best = data.get(i);
			}
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + best + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static class Area {

		int x, y, id;

		public Area(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
			areas.add(this);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getID() {
			return id;
		}
	}

}

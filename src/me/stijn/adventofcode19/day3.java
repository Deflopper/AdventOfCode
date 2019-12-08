package me.stijn.adventofcode19;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class day3 {
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(3);
		
		HashMap<Point2D, GridCell> map = new HashMap(); 
		double x,y;
		for (int iteration, i = 0; i < 2; i++) {
			String line = input.get(i);
			y = x = 0;
			iteration = 1;
			for (String s : line.split(",")) {
				char dir = s.charAt(0);
				int amount = Integer.valueOf(s.substring(1));
				Point2D p = addToPath(map, new Point2D.Double(x, y), amount, dir, (char) (i + '0'), iteration);
				x = p.getX();
				y = p.getY();
				iteration += amount;
			}
		}
		
		//calculate lowest amount of steps intersection
		int smallest = Integer.MAX_VALUE;
		for (Point2D p : map.keySet())
			if (map.get(p).c == 'X' && map.get(p).steps < smallest)
				smallest = map.get(p).steps;
		
		System.out.println("Smallest intersection after: " + smallest + " steps");
	}

	public static void part1(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(3);
		
		HashMap<Point2D, GridCell> map = new HashMap(); 
		double x,y;
		
		for (int i = 0; i < 2; i++) {
			String line = input.get(i);
			y = x = 0;
			for (String s : line.split(",")) {
				char dir = s.charAt(0);
				int amount = Integer.valueOf(s.substring(1));
				Point2D p = addToPath(map, new Point2D.Double(x,y), amount, dir, (char)(i + '0'),-1);
				x = p.getX();
				y = p.getY();
			}
		}
		
		//calculate closest intersection
		int smallest = Integer.MAX_VALUE;
		for (Point2D p : map.keySet()) 
			if (map.get(p).c == 'X') {
				int distance = (int) (Math.abs(p.getX()) + Math.abs(p.getY()));
				if (distance < smallest)
					smallest = distance;
			}
			
		System.out.println("Smallest distance to a crossing point: " + smallest);
	}
	
	public static class GridCell{
		char c;
		int steps;
		
		public GridCell(int steps, char c) {
			this.c = c;
			this.steps = steps;
		}
	}
	
	/**
	 * Add the gridcells to the given map
	 * @param map to add the gridcells to
	 * @param p1 starting point
	 * @param amount amount of cells to add
	 * @param dir direction in which to add the cells
	 * @param c character to add into the cells
	 * @param globalItteration number of global iterations before this operation
	 * @return last point added to the map
	 */
	public static Point2D addToPath(HashMap<Point2D, GridCell> map, Point2D.Double p1, int amount, char dir, char c, int globalItteration) {
		int x = (int) p1.x, y = (int) p1.y, iteration = 0, steps;
		while (iteration != amount) {
			switch (dir) {
			case 'R':
			case 'L':
				y = dir == 'L' ? y-1 : y+1;
				break;
			case 'U':
			case 'D':
				x = dir == 'U' ? x-1 : x+1;
				break;
			}
			Point2D p = new Point2D.Double(x, y);
			if (map.containsKey(p) && map.get(p).c != c) { 
				steps = globalItteration == -1 ? 0 : globalItteration + iteration + map.get(p).steps;
				map.put(p, new GridCell(steps, 'X'));
			} else if (!map.containsKey(p)) {
				steps = globalItteration == -1 ? 0 : globalItteration + iteration;
				map.put(p, new GridCell(steps, c));
			}
			iteration++;
		}
		return new Point2D.Double(x,y);
	}
}


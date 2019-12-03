package me.stijn.adventofcode19;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class day3 {
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(3),path = new ArrayList();
		
		HashMap<Point2D, Character> map = new HashMap(); 
		double x,y;
		
		for (int i = 0; i < 2; i++) {
			String line = input.get(i);
			x = 0;
			y = 0;
			for (String s : line.split(",")) {
				char dir = s.charAt(0);
				int amount = Integer.valueOf(s.substring(1));
				Point2D p = addToPath(map, new Point2D.Double(x,y), amount, dir, (char)(i + '0'));
				x = p.getX();
				y = p.getY();
			}
		}
		
		//calculate closest intersection
		int smallest = Integer.MAX_VALUE;
		for (Point2D p : map.keySet()) {
			if (map.get(p) == 'X') {
				int distance = (int) (Math.abs(p.getX()) + Math.abs(p.getY()));
				if (distance < smallest)
					smallest = distance;
			}
		}

		System.out.println("Smallest distance to a crossing point: " + smallest);
	}
	
	public static Point2D addToPath(HashMap<Point2D, Character> map, Point2D.Double p1, int amount, char dir, char c) {
		int x = (int) p1.x, y = (int) p1.y, iteration = 0;
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
			if (map.containsKey(p) && map.get(p) != c) 
				map.put(p, 'X');
			else 
				map.put(p, c);
			iteration++;
		}
		return new Point2D.Double(x,y);
	}
}


package me.stijn.adventofcode19;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class day11 {
	
	public static Map<Point2D.Double, Integer> map = new HashMap();
	
	public static void main(String[] args) throws IOException {
		String in = Utils.getString(11);
		PaintMachine paint = new PaintMachine(in);
		paint.start();
		System.out.println("Amount of panels painted at least once: " + map.size());
		map.clear();
		map.put(new Point2D.Double(0,0), 1);
		paint.start();
		
		double minX = 0,maxX = 0,minY = 0,maxY = 0;
		for (Point2D.Double p : map.keySet()) {
			if (p.x > maxX) maxX = p.x;
			if (p.x < minX) minX = p.x;
			if (p.y > maxY) maxY = p.y;
			if (p.y < minY) minY = p.y;
		}
		
		for (double j = maxY; j >= minY; j--) {
			System.out.println();
			for (double i = minX; i < maxX; i++) {
				if (getColorAt(new Point2D.Double(i,j)) == 1) 
					System.out.print("#");
				else
					System.out.print(" ");
			}
		}
	}
	
	public static int getColorAt(Point2D.Double point) {
		if (map.containsKey(point))
			return map.get(point);
		return 0;
	}
	
	public static class PaintMachine {
		
		Direction direction = Direction.UP;
		Point2D.Double pointer = new Point2D.Double(0,0);
		IntcodeComputer computer;
		
		public PaintMachine(String program) {
			computer = new IntcodeComputer(program);
			computer.setPainter(true);
		}
		
		public void start() {
			computer.reset();
			direction = Direction.UP;
			pointer = new Point2D.Double(0,0);
			ArrayList<BigInteger> output = new ArrayList();
			for (;;) {
				Integer[] input = new Integer[] {getColorAt(pointer)};
				output = computer.run(input);
				System.out.println("output: " + output);
				if (output.get(0).intValue() == 99)
					break;
				map.put(pointer, output.get(1).intValue());
				int targetDir = output.get(2).intValue() == 0? direction.ordinal() - 1 : direction.ordinal() + 1;
				if (targetDir < 0)
					targetDir += 4;
				else if (targetDir > 3)
					targetDir -= 4;
				direction = Direction.values()[targetDir];
				pointer = new Point2D.Double(pointer.x + direction.move.x, pointer.y + direction.move.y);
			}
		}
	}
	
	
	public static enum Direction {
		LEFT(0, new Point2D.Double(-1,0)), UP(-1, new Point2D.Double(0,1)), RIGHT(1, new Point2D.Double(1,0)), DOWN(-1, new Point2D.Double(0,-1));
		
		int code;
		Point2D.Double move;
		
		Direction(int code, Point2D.Double move) {
			this.code = code;
			this.move = move;
		}
	}

}

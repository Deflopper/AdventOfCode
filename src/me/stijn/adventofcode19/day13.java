package me.stijn.adventofcode19;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day13 {
	
	public static Map<Point2D, ObjectType> screen = new HashMap();
	
	public static final boolean DRAW_GAME = true;
	
	public static void main(String[] args) throws IOException {
		String in = Utils.getString(13);
		IntcodeComputer computer = new IntcodeComputer(in);
		computer.setMemoryAdress(0, BigInteger.valueOf(2));
		computer.setArcade(true);
		ArrayList<BigInteger> output;
		int score = 0,pendingInput = -2;
		Point2D ball = null, paddle = null;
		for (;;) {
			output = computer.run(new Integer[] { pendingInput });
			if (output.get(0).intValue() == 99)
				break;
			if (output.get(0).intValue() == 97) {
				if (DRAW_GAME)
					drawScreen(score);
				pendingInput = ball.getX() > paddle.getX() ? 1 : ball.getX() < paddle.getX() ? -1 : 0;
				continue;
			}
			if (output.get(1).intValue() == -1 && output.get(2).intValue() == 0)
				score = output.get(3).intValue();
			else {
				switch (ObjectType.values()[output.get(3).intValue()]) {
				case BALL:
					ball = new Point2D.Double(output.get(1).intValue(),  output.get(2).intValue());
					break;
				case PADDLE:
					paddle = new Point2D.Double(output.get(1).intValue(),  output.get(2).intValue());
					break;
				default:
					break;
				}
				screen.put(new Point2D.Double(output.get(1).intValue(),  output.get(2).intValue()), ObjectType.values()[output.get(3).intValue()]);
			}
		}
		System.out.println("Final score: " + score);
	}
 	
	public static void drawScreen(int score) {
		String output = "";
		System.out.flush();  
		if (screen.isEmpty())
			return;
		int maxX= 0, maxY = 0;
		for (Point2D p : screen.keySet()) {
			if (p.getX() > maxX)
				maxX = (int) p.getX();
			if (p.getY() > maxY)
				maxY = (int) p.getY();
		}
		output += "Score: " + score; 
		for (int y = 0; y<=maxY;y++) {
			output += "\n";
			for (int x = 0; x<=maxX;x++) {
				if (screen.containsKey(new Point2D.Double(x,y)))
					output += screen.get(new Point2D.Double(x,y)).icon;
				else 
					output += " "; 
			}
		}
		System.out.println(output);
	}
	
	public static void part1(String[] args) throws IOException {
		String in = Utils.getString(13);
		IntcodeComputer computer = new IntcodeComputer(in);
		computer.setArcade(true);
		ArrayList<BigInteger> output;
		int blocks = 0;
		for (;;) {
			output = computer.run(new Integer[0]);
			if (output.get(0).intValue() == 99)
				break;
			screen.put(new Point2D.Double(output.get(1).intValue(),  output.get(2).intValue()), ObjectType.values()[output.get(3).intValue()]);
			if (output.get(3).intValue() == ObjectType.BLOCK.ordinal())
				blocks++;
		}
		
		System.out.println("Amount of blocks on screen: " + blocks);
	}
	
	public static enum ObjectType{
		EMPTY(' '),WALL('W'),BLOCK('#'),PADDLE('-'),BALL('x');
		
		char icon;
		
		ObjectType(char icon) {
			this.icon = icon;
		}
	}
}

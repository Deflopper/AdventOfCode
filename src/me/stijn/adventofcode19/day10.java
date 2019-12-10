package me.stijn.adventofcode19;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class day10 {

	public static void main(String[] args) throws IOException {
		Station station = getBestStation();
		System.out.println("Amount of asteroids visible from best location: " + station.visibleAsteroids);
		station.startLaser();
	}

	public static Station getBestStation() throws IOException {
		ArrayList<String> input = Utils.getInput(10);
		int xsize = input.size();
		int ysize = input.get(0).length();

		HashMap<Point2D.Double, Integer> points = new HashMap();

		for (int i = 0; i < xsize; i++) {
			String s = input.get(i);
			for (int j = 0; j < ysize; j++) {
				char c = s.charAt(j);
				if (c == '#')
					points.put(new Point2D.Double(j, i), 0);
			}
		}

		for (Point2D.Double p : points.keySet()) {
			inner: for (Point2D.Double p2 : points.keySet()) {
				for (Point2D.Double p3 : points.keySet()) {
//					if (p2.distance(p) == 0)
//						continue inner;
					if (p3.distance(p2) != 0 && p3.distance(p) != 0 && checkIfInLine(p, p2, p3)) 
						continue inner;
				}
				points.put(p, points.get(p) + 1);
			}
		}
		int highest = Integer.MIN_VALUE;
		Point2D.Double highestPoint = null;
		for (Point2D.Double p : points.keySet()) {
			int amount = points.get(p);
			if (highestPoint == null || amount > highest) {
				highest = amount;
				highestPoint = p;
			}
		}
		ArrayList<Point2D.Double> temp = new ArrayList(points.keySet());
		return new Station(highestPoint, temp, highest);
	}

	private static boolean checkIfInLine(Point2D.Double startPoint, Point2D.Double endPoint,
			Point2D.Double pointToCheck) {
		double dxc = pointToCheck.x - startPoint.x, dyc = pointToCheck.y - startPoint.y;
		double dxl = endPoint.x - startPoint.x, dyl = endPoint.y - startPoint.y;
		double cross = dxc * dyl - dyc * dxl;

		if (Math.abs(dxl) >= Math.abs(dyl)) {
			if (dxl > 0 ? startPoint.x <= pointToCheck.x && pointToCheck.x <= endPoint.x
					: endPoint.x <= pointToCheck.x && pointToCheck.x <= startPoint.x) {
				return cross == 0;
			}
		} else {
			if (dyl > 0 ? startPoint.y <= pointToCheck.y && pointToCheck.y <= endPoint.y
					: endPoint.y <= pointToCheck.y && pointToCheck.y <= startPoint.y) {
				return cross == 0;
			}
		}
		return false;
	}

	public static class Station {
		int visibleAsteroids;
		Point2D.Double loc;
		double laserAngle;
		ArrayList<Point2D.Double> asteroids = new ArrayList();

		public Station(Point2D.Double loc, ArrayList<Point2D.Double> asteroids, int visibleAsteroids) {
			this.loc = loc;
			this.asteroids = asteroids;
			this.visibleAsteroids = visibleAsteroids;
		}

		public float getAngleTo(Point2D.Double target) {
			float angle = (float) Math.toDegrees(Math.atan2(target.y - loc.y, target.x - loc.x));
			if (angle < -90 ) 
				angle += 360;
			return angle;
		}

		public void startLaser() {
			int destroyed = 0;
			Point2D.Double num200 = null;
			while (destroyed < 200) {
				ArrayList<Point2D.Double> queue = getFirstToVapor();
				Collections.sort(queue, new Comparator<Point2D.Double>() {
				    public int compare(Point2D.Double obj1, Point2D.Double obj2) {
				        return Float.compare(getAngleTo(obj1),getAngleTo(obj2));
				    }
				});
				
				for (Point2D.Double p : queue) {
					asteroids.remove(p);
					destroyed++;
					if (destroyed == 200) {
						num200 = p;
						break;
					}
				}
			}
			
			System.out.println("Winning bet: " + (int)((num200.x * 100) + num200.y));
		}

		public ArrayList<Point2D.Double> getFirstToVapor() {
			ArrayList<Point2D.Double> queue = new ArrayList();
			inner: for (Point2D.Double p2 : asteroids) {
				for (Point2D.Double p3 : asteroids) {
					if (p2.distance(loc) == 0)
						continue inner;
					if (checkIfInLine(loc, p2, p3) && p3.distance(p2) != 0 && p3.distance(loc) != 0) {
						continue inner;
					}
				}
				queue.add(p2);
			}
			return queue;
		}
	}

}

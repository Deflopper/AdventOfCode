package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.geometry.Point3D;

public class day12 {
	
	public static HashMap<Point3D, Point3D> map = new HashMap();
	
	public static <E> void main(String[] args) throws IOException {
		ArrayList<String> in = Utils.getInput(12);
		
		int steps = 1000, index = 0;
		
		for (String s : in) {
			s = s.replaceAll("[^\\d,-]", "");
			String[] split = s.split(",");
			map.put(new Point3D(Integer.valueOf(split[0]),Integer.valueOf(split[1]),Integer.valueOf(split[2])), new Point3D(0,0,0));
		}
		
		
		while (index < steps) {
			for (Point3D p : map.keySet()) {
				for (Point3D p2 : map.keySet()) {
					if (p == p2)
						continue;
					Point3D vel = map.get(p);
					if (p.getX() != p2.getX()) 
						 if (Double.compare(p.getX(), p2.getX()) < 0) vel = vel.add(1,0,0); else vel = vel.subtract(1,0,0);
					if (p.getY() != p2.getY()) 
						 if (Double.compare(p.getY(), p2.getY()) < 0) vel = vel.add(0,1,0); else vel = vel.subtract(0,1,0);
					if (p.getZ() != p2.getZ()) 
						 if (Double.compare(p.getZ(), p2.getZ()) < 0) vel = vel.add(0,0,1); else vel = vel.subtract(0,0,1);
					map.put(p, vel);
				}
			}
			Iterator<Point3D> it = map.keySet().iterator();
			HashMap<Point3D, Point3D> newMap = new HashMap();
			while(it.hasNext()) {
				Point3D p = it.next();
				newMap.put(p.add(map.get(p)), map.get(p));
			}
			map = newMap;
			index++;
		}
		
		int sum = 0;
		for (Point3D p : map.keySet()) {
			Point3D vel = map.get(p);
			int pot = (int) (Math.abs(p.getX()) + Math.abs(p.getY()) + Math.abs(p.getZ()));
			int kin = (int) (Math.abs(vel.getX()) + Math.abs(vel.getY()) + Math.abs(vel.getZ()));
			sum += pot * kin;
		}
		System.out.println("Total energy in system: " + sum);
	}
	
}

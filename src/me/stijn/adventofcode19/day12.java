package me.stijn.adventofcode19;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point3D;

public class day12 {
	
	public static ArrayList<Moon> moons = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> in = Utils.getInput(12);
		
		BigInteger steps = BigInteger.valueOf(1000), index = BigInteger.valueOf(0);
		
		for (String s : in) {
			s = s.replaceAll("[^\\d,-]", "");
			String[] split = s.split(",");
			moons.add(new Moon(new Point3D(Integer.valueOf(split[0]),Integer.valueOf(split[1]),Integer.valueOf(split[2]))));
		}
		
		int repX = 0, repY = 0, repZ = 0;
        for (Set<List<Integer>> seenX = new HashSet<>(), seenY = new HashSet<>(), seenZ = new HashSet<>(); repX == 0 || repY == 0 || repZ == 0;) {
			List<Integer> moonsX = new ArrayList<>(), moonsY = new ArrayList<>(), moonsZ = new ArrayList<>();
			for (Moon m : moons) {
				moonsX.add((int) m.loc.getX());
				moonsX.add((int) m.velocity.getX());
				moonsY.add((int) m.loc.getY());
				moonsY.add((int) m.velocity.getY());
				moonsZ.add((int) m.loc.getZ());
				moonsZ.add((int) m.velocity.getZ());
			}
			if (repX == 0 && !seenX.add(moonsX)) 
				repX = index.intValue();
			if (repY == 0 && !seenY.add(moonsY)) 
				repY = index.intValue();
			if (repZ == 0 && !seenZ.add(moonsZ)) 
				repZ = index.intValue();
			
			step();
			index = index.add(BigInteger.valueOf(1));
		}
		
		System.out.println("Amount of steps before return to initial state " + Utils.lcmFind(Utils.lcmFind(repX, repY), BigInteger.valueOf(repZ)));
	}
	
	public static void part1(String[] args) throws IOException {
		ArrayList<String> in = Utils.getInput(12);
		
		int steps = 1000, index = 0;
		
		for (String s : in) {
			s = s.replaceAll("[^\\d,-]", "");
			String[] split = s.split(",");
			moons.add(new Moon(new Point3D(Integer.valueOf(split[0]),Integer.valueOf(split[1]),Integer.valueOf(split[2]))));
		}
		
		for (; index < steps; index++) {
			step();
		}
		
		int sum = 0;
		for (Moon m : moons) {
			sum += (int) (Math.abs(m.velocity.getX()) + Math.abs(m.velocity.getY()) + Math.abs(m.velocity.getZ())) * 
					(int) (Math.abs(m.loc.getX()) + Math.abs(m.loc.getY()) + Math.abs(m.loc.getZ()));
		}
		System.out.println("Total energy in system: " + sum);
	}
	
	public static void step() {
		for (Moon p : moons) {
			for (Moon p2 : moons) {
				if (p.loc == p2.loc)
					continue;
				Point3D vel = p.velocity;
				if (p.loc.getX() != p2.loc.getX())
					vel = Double.compare(p.loc.getX(), p2.loc.getX()) < 0 ? vel.add(1, 0, 0) : vel.subtract(1, 0, 0);
				if (p.loc.getY() != p2.loc.getY())
					vel = Double.compare(p.loc.getY(), p2.loc.getY()) < 0 ? vel.add(0, 1, 0) : vel.subtract(0, 1, 0);
				if (p.loc.getZ() != p2.loc.getZ())
					vel = Double.compare(p.loc.getZ(), p2.loc.getZ()) < 0 ? vel.add(0, 0, 1) : vel.subtract(0, 0, 1);

				p.velocity = vel;
			}
		}
		for (Moon m : moons) 
			m.loc = m.loc.add(m.velocity); 
	}
	
	public static class Moon {
		Point3D loc, velocity;
		
		public Moon(Point3D loc) {
			this.loc = loc;
			this.velocity = new Point3D(0,0,0);
		}

		public boolean equals(Moon obj) {
			return loc.equals(obj.loc) && velocity.equals(obj.velocity);
		}
	}
}

package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;

public class day6 {

	private static Planet com;
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> input = Utils.getInput(6);

		ArrayList<Planet> planets = new ArrayList(), queue = new ArrayList(), children = new ArrayList();
		com = new Planet("COM", null);
		planets.add(com);
		
		Planet pointer = com;
		for (;;) { //load input into planets
			children.clear();
			if (!queue.isEmpty()) {
				pointer = queue.get(0);
				queue.remove(0);
			}
			for (String s : input) {
				String[] split = s.split("\\)");
				if (pointer.name.equals(split[0])) { //found child
					Planet p = new Planet(split[1], pointer);
					planets.add(p);
					children.add(p);
				}
			}
			if (!children.isEmpty()) {
				pointer.children.addAll(children);
				queue.addAll(children);
			}
			if (queue.isEmpty())
				break;
		}
		
		//part 1
		int sum = 0;
		for (Planet p : planets) {
			sum += p.getDistanceFromCom();
		}

		System.out.println("Part 1, result of all direct and indirect orbits: " + sum);
		
		//part 2
		ArrayList<Planet> youTree = treeToCom(planets, getPlanetByName(planets, "YOU")); //get tree to com from you
		ArrayList<Planet> sanTree = treeToCom(planets, getPlanetByName(planets, "SAN")); //get tree to com from san
		
		for (Planet p : youTree) {
			if (sanTree.contains(p)) {
				System.out.println("Part 2, minimal orbit transfers required: " + Integer.valueOf(youTree.indexOf(p) + sanTree.indexOf(p) - 2)); //subtract 2 because SAN and YOU are included
				break;
			}
		}
	}
	
	public static ArrayList<Planet> treeToCom(ArrayList<Planet> planets, Planet planet){
		ArrayList<Planet> tree = new ArrayList();
		Planet pointer = planet;
		for (;;) {
			tree.add(pointer);
			pointer = pointer.parent;
			if (pointer.name.equals("COM"))
				break;
		}
		return tree;
	}
	
	public static Planet getPlanetByName(ArrayList<Planet> planets, String name) {
		for (Planet p : planets) {
			if (p.name.equals(name))
				return p;
		}
		return null;
	}

	public static class Planet {
		String name;
		Planet parent;
		ArrayList<Planet> children = new ArrayList();

		public Planet(String name, Planet parent) {
			this.name = name;
			this.parent = parent;
		}
		
		public int getDistanceFromCom() {
			if (parent == null || name.equals("COM"))
				return 0;
			int distance = 0;
			Planet pointer = this;
			while (!pointer.name.equals("COM")) {
				pointer = pointer.parent;
				distance++;
			}
			return distance;
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
}

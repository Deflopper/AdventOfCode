package me.stijn.adventofcode19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class day14 {
	
	public static ArrayList<Recipe> recipes = new ArrayList();
	public static HashMap<Material, Integer> over = new HashMap();
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> in = Utils.getInput(14);
		
		for (String s : in) {
			String[] split = s.split(" => ");
			String[] product = split[1].split(" ");
			String[] ingredients = split[0].split(", ");
			
			HashMap<Material, Integer> input = new HashMap(), output = new HashMap();
			
			for (String ing : ingredients) {
				String[] ingSplit = ing.split(" ");
				input.put(new Material(ingSplit[1]), Integer.valueOf(ingSplit[0]));
			}
		
			output.put(new Material(product[1]), Integer.valueOf(product[0]));
			
			Recipe rec = new Recipe(input, output);
			recipes.add(rec);
		}
		
		System.out.println(getOresFor(new Material("FUEL"), 1));
		
		
	}
	
	public static HashMap<Material, Integer> getOresFor(Material m, Integer amount){
		
		return null;
	}
	
	public static class Recipe {
		
		HashMap<Material, Integer> ingredients, output;
		
		public Recipe(HashMap<Material, Integer> ingredients, HashMap<Material, Integer> output) {
			this.ingredients = ingredients;
			this.output = output;
		}
	}
	
	
	public static class Material {
		String name;
		public Material(String name) {
			this.name = name;
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return this.hashCode() == obj.hashCode();
		}

		@Override
		public String toString() {
			return name;
		}

		
	}
}

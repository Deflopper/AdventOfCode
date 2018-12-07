package me.stijn.adventofcode18;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class day7 {
	static HashMap<Character, Step> steps = new HashMap<Character, Step>();
	static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // "ABCDEF".toCharArray();
	static String result = "";

	public static void part1(String[] args) throws IOException, InterruptedException {
		
		ArrayList<String> list = Utils.getInput(7);
		long start = System.currentTimeMillis();
		// create the steps
		for (String s : list) {
			String[] split = s.split(" ");
			if (steps.containsKey(split[7].charAt(0))) {
				Step step = steps.get(split[7].charAt(0));
				step.setRequired(step.getRequired() + split[1]);
			} else {
				new Step(split[7].charAt(0), split[1]);
			}
			if (!steps.containsKey(split[1].charAt(0))) {
				new Step(split[1].charAt(0), "");
			}
		}

		boolean solved = false;
		while (!solved) {
			ArrayList<Character> tempsteps = new ArrayList<Character>();
			for (Step s : steps.values()) {
				if (s.getRequired() != null && s.getRequired().isEmpty()) {
					tempsteps.add(s.getId());
				}
			}
			if (tempsteps.isEmpty()) { // checks if there are no more available options
				solved = true;
				break;
			}

			Collections.sort(tempsteps); // sorts the tempsteps alphabeticly

			result += tempsteps.get(0);

			for (Step s : steps.values()) {
				if (s.getRequired().contains(Character.toString(tempsteps.get(0)))) {
					s.setRequired(s.getRequired().replaceAll(Character.toString(tempsteps.get(0)), ""));
				}
			}

			steps.remove(tempsteps.get(0));
		}

		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + result + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// PrintWriter writer = new PrintWriter("input\\test.txt", "UTF-8"); //generates
		// timetable for debugging

		ArrayList<String> list = Utils.getInput(7);
		long start = System.currentTimeMillis();
		// create the steps
		for (String s : list) {
			String[] split = s.split(" ");
			if (steps.containsKey(split[7].charAt(0))) {
				Step step = steps.get(split[7].charAt(0));
				step.setRequired(step.getRequired() + split[1]);
			} else {
				new Step(split[7].charAt(0), split[1]);
			}
			if (!steps.containsKey(split[1].charAt(0))) {
				new Step(split[1].charAt(0), "");
			}
		}

		ArrayList<Worker> workers = new ArrayList<Worker>();

		for (int i = 0; i < 6; i++) {
			Worker w = new Worker(i);
			workers.add(w);
		}

		boolean solved = false;
		int seconds = 0;
		while (!solved) {
			ArrayList<Character> tempsteps = new ArrayList<Character>();
			ArrayList<Step> remove = new ArrayList<Step>();
			boolean completed = true;
			for (Step s : steps.values()) {
				if (!s.isComplete()) {
					completed = false;
				}
				if (s.getRequired() != null && s.getRequired().isEmpty()) {
					if (!s.isWorkingOn()) {
						tempsteps.add(s.getId());
					}
				}
			}

			if (completed) { // checks if there are no more available options
				solved = true;
				break;
			}
			Collections.sort(tempsteps); // sorts the tempsteps alphabeticly

			for (Character s : tempsteps) {
				for (Worker w : workers) {
					if (w.getStep() == null) {
						w.setStep(steps.get(s));
						break;
					}

				}
			}

			/*
			 * writer.print("   "); for (Worker w : workers) { writer.print(w.id + ":"); }
			 * writer.println(); writer.print(seconds + " -");
			 */
			for (Worker w : workers) {
				/*
				 * if (w.getStep() != null) { writer.print(w.getStep().getId() + ":"); } else {
				 * writer.print(".:"); }
				 */
				w.tick();
			}
			/*
			 * writer.println(); writer.println();
			 */
			seconds++;
		}
		// writer.close();
		long timeElapsed = System.currentTimeMillis() - start;
		System.out.println("Found: " + seconds + " in: " + Float.valueOf(timeElapsed / 1000F) + " seconds");
	}

	public static class Worker {
		int id, seconds;
		Step s;

		public Worker(int id) {
			this.id = id;
		}

		public void setStep(Step s) {
			this.s = s;
			seconds = new String(alphabet).indexOf(s.getId()) + 62;
			s.workingOn(true);
		}

		public Step getStep() {
			return s;
		}

		public void tick() {
			if (seconds > 0) {
				seconds--;
				if (seconds == 1) {
					result += s.getId();
					for (Step s : steps.values()) {
						if (s.getRequired().contains(Character.toString(this.s.getId()))) {
							s.setRequired(s.getRequired().replaceAll(Character.toString(this.s.getId()), ""));
						}
					}
					s.workingOn(false);
					s.setComplete(true);
					s = null;
				}
			}
		}

	}

	public static class Step {
		char id;
		String required;
		boolean workingon = false, complete = false;

		public Step(char id, String required) {
			this.id = id;
			this.required = required;
			steps.put(id, this);
		}

		public void setComplete(Boolean b) {
			complete = b;
		}

		public boolean isComplete() {
			return complete;
		}

		public void workingOn(Boolean b) {
			workingon = true;
		}

		public boolean isWorkingOn() {
			return workingon;
		}

		public void setRequired(String r) {
			required = r;
		}

		public char getId() {
			return id;
		}

		public String getRequired() {
			return required;
		}

	}

}

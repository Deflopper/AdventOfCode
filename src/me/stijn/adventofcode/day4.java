package me.stijn.adventofcode;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class day4 {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		List list = Utils.getInput(4);

		HashMap<Integer, Integer> timeasleep = new HashMap<Integer, Integer>(); //contains: Guard id and integer value of how many minutes he has slept
		HashMap<Integer, List> minuteasleep = new HashMap<Integer, List>();//contains: Minute and list of guardid's asleep on that minute

		long start = System.currentTimeMillis();

		Collections.sort(list, new Comparator<String>() { // sorts the list by date
			public int compare(String o1, String o2) {
				if (o1 == null || o2 == null)
					return 0;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				String date = (o1).substring((o1).indexOf("[") + 1, (o1).indexOf("]"));
				String date2 = (o2).substring((o2).indexOf("[") + 1, (o2).indexOf("]"));
				Date d1 = null, d2 = null;
				try {
					d1 = dateFormat.parse(date);
					d2 = dateFormat.parse(date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return d1.compareTo(d2);
			}
		});
		
		Date temp = null;
		int activeshift = 0;
		
		for (Object o : list) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String s = (String) o;
			String date = (s).substring((s).indexOf("[") + 1, (s).indexOf("]")); //get the date out of string
			s = s.replace("[" + date + "] ", ""); //removes the date from string
			Date d1 = dateFormat.parse(date); //gets the date string into Date object
			
			if (s.contains("begins shift")) {
				activeshift = Integer.parseInt(s.replaceAll("[\\D]", ""));
			} else if (s.contains("falls asleep")) {
				temp = d1;
			} else if (s.contains("wakes up")) {
		        long thisTime = d1.getTime(); //time now
		        long anotherTime = temp.getTime(); //time when started sleeping
		        
		        int minutesAsleep = (int) TimeUnit.MILLISECONDS.toMinutes(thisTime - anotherTime);
		      
		        if (!timeasleep.containsKey(activeshift)) {
		        	timeasleep.put(activeshift, minutesAsleep);
		        } else {
		        	timeasleep.put(activeshift, minutesAsleep + timeasleep.get(activeshift));
		        }
		        
		        for (int i = 0; i < minutesAsleep; i++) {
		        	int time = i + temp.getMinutes();
		        	if (minuteasleep.containsKey(time)) {
		        		List li = minuteasleep.get(time);
		        		li.add(activeshift);
		        		minuteasleep.put(time, li);
		        	} else {
		        		ArrayList li = new ArrayList();
		        		li.add(activeshift);
		        		minuteasleep.put(time,li);
		        	}
		        }
			}
		}
		
		//calculate best guard and day
		int favoriteguard = -1, favoriteguardamount = -1, favoriteminute = -1;
		for (Integer i : minuteasleep.keySet()) {
			HashMap<Integer, Integer> daytotal = new HashMap<Integer, Integer>(); //contains: Guard ID / amount of sleep for the day
			for (Object o : minuteasleep.get(i)) { //fils the hashmap with data
				if (!daytotal.containsKey(o)) {
					daytotal.put((Integer) o, 1);
				} else {
					daytotal.put((Integer) o, daytotal.get(o) + 1);
				}
			}
			int daytop = -1, guardoftheday = -1;
			for (Integer j : daytotal.keySet()) { //calculates best guard of the day
				if (daytotal.get(j) > daytop) {
					daytop = daytotal.get(j);
					guardoftheday = j;
				}
			}
			if (daytop > favoriteguardamount) { //checks if best guard of the day was better then previous all time favorite
				favoriteguardamount = daytop;
				favoriteguard = guardoftheday;
				favoriteminute = i;
			}
		}
		
		
		long timeElapsed = System.currentTimeMillis() - start;
		 System.out.println("Found answer: " + Integer.valueOf(favoriteguard * favoriteminute)+ " in: " + Float.valueOf(timeElapsed /
		 1000F) + " seconds");
	}
	
	

	public static void part1(String[] args) throws IOException, InterruptedException, ParseException {
		List list = Utils.getInput(4);

		HashMap<Integer, Integer> timeasleep = new HashMap<Integer, Integer>(); //contains: Guard id and integer value of how many minutes he has slept
		HashMap<Integer, List> minuteasleep = new HashMap<Integer, List>();//contains: Minute and list of guardid's asleep on that minute

		long start = System.currentTimeMillis();

		Collections.sort(list, new Comparator<String>() { // sorts the list by date
			public int compare(String o1, String o2) {
				if (o1 == null || o2 == null)
					return 0;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				String date = (o1).substring((o1).indexOf("[") + 1, (o1).indexOf("]"));
				String date2 = (o2).substring((o2).indexOf("[") + 1, (o2).indexOf("]"));
				Date d1 = null, d2 = null;
				try {
					d1 = dateFormat.parse(date);
					d2 = dateFormat.parse(date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return d1.compareTo(d2);
			}
		});
		
		Date temp = null;
		int activeshift = 0;
		
		for (Object o : list) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String s = (String) o;
			String date = (s).substring((s).indexOf("[") + 1, (s).indexOf("]")); //get the date out of string
			s = s.replace("[" + date + "] ", ""); //removes the date from string
			Date d1 = dateFormat.parse(date); //gets the date string into Date object
			
			if (s.contains("begins shift")) {
				activeshift = Integer.parseInt(s.replaceAll("[\\D]", ""));
			} else if (s.contains("falls asleep")) {
				temp = d1;
			} else if (s.contains("wakes up")) {
				
		        long thisTime = d1.getTime(); //time now
		        long anotherTime = temp.getTime(); //time when started sleeping
		        
		        int minutesAsleep = (int) TimeUnit.MILLISECONDS.toMinutes(thisTime - anotherTime);
		      
		        if (!timeasleep.containsKey(activeshift)) {
		        	timeasleep.put(activeshift, minutesAsleep);
		        } else {
		        	timeasleep.put(activeshift, minutesAsleep + timeasleep.get(activeshift));
		        }
		        
		        for (int i = 0; i < minutesAsleep; i++) {
		        	int time = i + temp.getMinutes();
		        	if (minuteasleep.containsKey(time)) {
		        		List li = minuteasleep.get(time);
		        		li.add(activeshift);
		        		minuteasleep.put(time, li);
		        	} else {
		        		ArrayList li = new ArrayList();
		        		li.add(activeshift);
		        		minuteasleep.put(time,li);
		        	}
		        }
			}
		}
		
		//calculate which one has slept for the longest time
		int max = 0, holder = 0;
		for (Integer i : timeasleep.keySet()) {
			if (timeasleep.get(i) > max) {
				max = timeasleep.get(i);
				holder = i;
			}
		}
		
		//calculate best day
		int favoriteday = -1, favoritedayamount = -1;;
		for (Integer i : minuteasleep.keySet()) {
			if (minuteasleep.get(i).contains(holder)) {
				int amount = 0;
				for (Object s : minuteasleep.get(i)) {
					if (s.equals(holder)) {
						amount++;
					}
				}
				if (amount > favoritedayamount) {
					favoriteday = i;
					favoritedayamount = amount;
				}
			}
		}

		
		long timeElapsed = System.currentTimeMillis() - start;
		 System.out.println("Found answer: " + Integer.valueOf(favoriteday * holder)+ " in: " + Float.valueOf(timeElapsed /
		 1000F) + " seconds");
	}

}

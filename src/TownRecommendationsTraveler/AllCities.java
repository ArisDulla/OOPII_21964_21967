package TownRecommendationsTraveler;

import java.util.ArrayList;
import java.util.HashMap;

public class AllCities {

	private static ArrayList<City> newArrayTowns;
	private static ArrayList<City> arrayTowns;
	private static HashMap<String, ArrayList<String>> hm;

	public AllCities() {

		newArrayTowns = new ArrayList<City>();
		arrayTowns = new ArrayList<City>();
		hm = new HashMap<String, ArrayList<String>>();

		hm.put("Monday", new ArrayList<String>());
		hm.put("Tuesday", new ArrayList<String>());
		hm.put("Friday", new ArrayList<String>());
		hm.put("Wednesday", new ArrayList<String>());
		hm.put("Thursday", new ArrayList<String>());
		hm.put("Sunday", new ArrayList<String>());
		hm.put("Saturday", new ArrayList<String>());
	}

	public static ArrayList<City> getArrayTowns() {
		return arrayTowns;
	}

	public static ArrayList<City> getNewArrayTowns() {
		return newArrayTowns;
	}

	public static HashMap<String, ArrayList<String>> getHm() {
		return hm;
	}	
} 

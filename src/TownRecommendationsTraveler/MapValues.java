package TownRecommendationsTraveler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class MapValues {

	/**
	 * Get timestamp form town
	 * 
	 * @param town
	 * @return timestamp
	 */
	private static String getDate(City town) {

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Date dateFormat = new java.util.Date(town.getDate() * 1000);
		String weekday = sdf.format(dateFormat);

		return weekday;
	}

	/**
	 * SET VALUES IN HASH MAP
	 * 
	 * @param town
	 * @param hms
	 */
	public static void setValues(City town, HashMap<String, ArrayList<String>> hm) {

		String day, nameCity;

		day = getDate(town);
		nameCity = town.getCityName();

		Iterator<String> it = hm.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();

			if (key.equals(day)) {
				hm.get(key).add(nameCity);
			}
		}
	}

	/**
	 * PRINT HASH MAP
	 * 
	 * @param hm
	 */
	protected static void printHashMap(HashMap<String, ArrayList<String>> hm) {

		String[] days = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Saturday", "Sunday" };
		System.out.println("\n");
		for (String i : days) {
			System.out.println(i + "   " + hm.get(i));

		}
		/*
		 * Iterator<Entry<String, ArrayList<String>>> it = hm.entrySet().iterator();
		 * 
		 * while (it.hasNext()) { Map.Entry pair = (Map.Entry) it.next();
		 * System.out.println(pair.getKey() + " = " + pair.getValue()); }
		 */
	}
}

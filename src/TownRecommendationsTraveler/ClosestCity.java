package TownRecommendationsTraveler;

import java.util.ArrayList;

public class ClosestCity {

	public static String nearbyCity(PerceptronTraveller traveller, ArrayList<String> namesCities) {

		double min = 1;
		String nearbyCity = null;
		double distance;
		String cityName;

		ArrayList<City> cities = AllCities.getArrayTowns();
		/**
		 * Search recommended cities
		 */

		// namesCities = traveller.recommend();

		/**
		 * search closest City
		 */
		for (String x : namesCities) {
			for (City c : cities) {

				cityName = c.getCityName();

				if (cityName.equals(x)) {

					distance = c.getDistance();
					if (distance < min) {
						min = distance;
						nearbyCity = x;
					}
				}
			}
		}
		if (nearbyCity == null) {

			return "there are no cities in the array";
		}
		/**
		 * @return closest City
		 */
		return nearbyCity;
	}
}

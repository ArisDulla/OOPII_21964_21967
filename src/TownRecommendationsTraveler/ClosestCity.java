package TownRecommendationsTraveler;

import java.util.ArrayList;

public class ClosestCity {

	protected static String nearbyCity(PerceptronTraveller traveller, ArrayList<City> cities) {
		ArrayList<City> recommendedCities = new ArrayList<City>();
		double min = 1;
		String nearbyCity = null;
		double distance;
		String cityName;
		ArrayList<String> namesCities;

		/**
		 * Search recommended cities
		 */
		namesCities = traveller.recommend(cities, recommendedCities);

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

			System.out.println("there are no cities in the array");
		}
		/**
		 * @return closest City
		 */
		return nearbyCity;
	}
}

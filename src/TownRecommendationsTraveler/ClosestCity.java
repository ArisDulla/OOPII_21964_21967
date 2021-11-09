package TownRecommendationsTraveler;

import java.util.ArrayList;

/**
 * 
 * Closest city
 *
 */
public class ClosestCity {

	protected static String nearbyCity(PerceptronTraveller traveller, City[] cities) {

		double min = 1;
		String nearbyCity = null;
		double distance;
		String cityName;
		ArrayList<String> namesCities;

		namesCities = traveller.recommend(cities);

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
		return nearbyCity;
	}
}

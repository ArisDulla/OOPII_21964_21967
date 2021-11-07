package TownRecommendationsTraveler;

import java.util.ArrayList;

/**
 * 
 * Closest city
 *
 */
public class ClosestCity {

	protected String nearbyCity(ArrayList<String> namesCities, City[] cities) {

		double min = 1;
		String nearbyCity = null;
		double distance;
		String cityName;

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
		return nearbyCity;
	}
}

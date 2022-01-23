package TownRecommendationsTraveler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class PerceptronYoungTraveller extends Traveller implements PerceptronTraveller {

	@Override
	public ArrayList<String> recommend() {
		double[] weightsBias = new double[] { 1, 1, -1, 0, 1, 1, 1, 1, 1, 0, -1.6 };
		// [0] = CAFE , [1] = SEA, [2] = MUSEUMS, [3] = RESTAURANTS, [4] = STADIUM,
		// [5] = BAR ,[6] = AMUSEMENT PARK, [7] = KLEVIN, [8] = CLOUNDS, [9] = COORDS
		// [10] = BIAS

		ArrayList<String> namesCities = new ArrayList<String>();
		double[] array;
		double wx;
		int len;

		ArrayList<City> cities = AllCities.getArrayTowns();

		len = cities.size();
		for (int j = 0; j < len; j++) {
			wx = 0;
			array = cities.get(j).getVectorFeatures();

			for (int i = 0; i < 10; i++) {
				wx = wx + array[i] * weightsBias[i];
			}

			// heavy Side step ---------------
			wx += weightsBias[10];
			if (wx > 0) {
				namesCities.add(cities.get(j).getCityName());
				recommendedCities.add(cities.get(j));
			}
		}
		return namesCities;
	}

	@Override
	public ArrayList<String> recommend(boolean flag, ArrayList<String> namesCities) {

		ArrayList<String> convertNamesCities = new ArrayList<String>();
		int i;
		i = 0;
		if (flag) {
			for (String word : namesCities) {

				convertNamesCities.add(i++, word.toUpperCase());
			}
		} else {
			for (String word : namesCities) {

				convertNamesCities.add(i++, word.toLowerCase());
			}
		}
		return convertNamesCities;
	}

	/**
	 * --Ascending Coords--
	 */
	@Override
	public ArrayList<String> sortReccomendations(ArrayList<City> list) {

		ascendingCoordsCompare coordsCompare = new ascendingCoordsCompare();
		ArrayList<String> namesCities = new ArrayList<String>();

		Collections.sort(list, coordsCompare);

		for (City city : list) {
			namesCities.add(city.getCityName());
		}
		return namesCities;
	}

	@Override
	public ArrayList<City> getCitiesRecommend() {

		return recommendedCities;
	}

	@Override
	public Map<City, Double> personalized(int[] candidateTravellerCriteria) {

		return personalizedRecommend(candidateTravellerCriteria);
	}
}

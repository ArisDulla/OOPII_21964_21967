package TownRecommendationsTraveler;

import java.util.ArrayList;

/**
 * 
 * Perceptron young traveller
 *
 */
public class PerceptronYoungTraveller implements PerceptronTraveller {

	@Override
	public ArrayList<String> recommend(City[] cities) {
		double[] weightsBias = new double[] { 1, 1, -1, 0, 1, 1, 1, 1, 1, 0, -1.6 };
		// [0] = CAFE , [1] = SEA, [2] = MUSEUMS, [3] = RESTAURANTS, [4] = STADIUM,
		// [5] = BAR ,[6] = AMUSEMENT PARK, [7] = KLEVIN, [8] = CLOUNDS, [9] = COORDS
		// [10] = BIAS

		ArrayList<String> namesCities = new ArrayList<String>();
		double[] array;
		double wx;

		for (int j = 0; j < 10; j++) {
			wx = 0;
			array = cities[j].getVectorFeatures();

			for (int i = 0; i < 10; i++) {
				wx = wx + array[i] * weightsBias[i];
			}

			// heavy Side step ---------------
			wx += weightsBias[10];
			if (wx > 0) {
				namesCities.add(cities[j].getCityName());
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
}

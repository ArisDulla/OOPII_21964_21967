package TownRecommendationsTraveler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Traveller {

	public ArrayList<City> recommendedCities;

	public Map<City, Double> personalizedRecommend(int[] candidateTravellerCriteria) {

		recommendedCities = new ArrayList<City>();
		int len;
		ArrayList<City> arrayTowns;
		arrayTowns = AllCities.getArrayTowns();

		// STREAM
		Map<City, Double> cityToRank = arrayTowns.stream()
				.collect(Collectors.toMap(i -> i, i -> innerDot(i.getVectorFeatures(), candidateTravellerCriteria)));

		// SORT
		Map<City, Double> result = cityToRank.entrySet().stream().filter(i -> i.getValue() >= 0)
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		// TOP 5
		len = result.size();
		if (len > 5) {
			result = result.entrySet().stream().limit(5).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		}

		recommendedCities = new ArrayList<>(result.keySet());

		return result;
	}

	private static double innerDot(double[] ds, int[] candidateTraveller) {

		double sum = 0;

		for (int i = 0; i < ds.length; i++)

			sum += ds[i] * candidateTraveller[i];

		return sum;
	}

}

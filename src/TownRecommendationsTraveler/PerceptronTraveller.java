package TownRecommendationsTraveler;

import java.util.ArrayList;

/**
 * 
 * Interface Perceptron traveller
 *
 */
public interface PerceptronTraveller {

	public ArrayList<String> recommend(City[] feature);

	public ArrayList<String> recommend(boolean flag, ArrayList<String> feature);

	public String getClosestCityName(ArrayList<String> namesCities, City[] cities);
}

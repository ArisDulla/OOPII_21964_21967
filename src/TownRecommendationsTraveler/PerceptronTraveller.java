package TownRecommendationsTraveler;

import java.util.ArrayList;
import java.util.Map;

public interface PerceptronTraveller {

	public ArrayList<String> recommend();

	public ArrayList<String> recommend(boolean flag, ArrayList<String> cities);

	public ArrayList<String> sortReccomendations(ArrayList<City> list);

	public Map<City, Double> personalized(int[] candidateTravellerCriteria);

	public ArrayList<City> getCitiesRecommend();

}
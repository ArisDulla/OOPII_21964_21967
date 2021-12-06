package TownRecommendationsTraveler;

import java.util.ArrayList;

public interface PerceptronTraveller {

	public ArrayList<String> recommend(ArrayList<City> cities, ArrayList<City> recommendedCities);

	public ArrayList<String> recommend(boolean flag, ArrayList<String> cities);

	public ArrayList<String> sortReccomendations(ArrayList<City> list);
}

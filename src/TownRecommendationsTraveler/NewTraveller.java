package TownRecommendationsTraveler;

import java.util.ArrayList;
import static TownRecommendationsTraveler.ClosestCity.nearbyCity;

public class NewTraveller {

	/**
	 * CREAT NEW TRAVELLER and recommend cities
	 * 
	 * @param arrayCities
	 * @param age
	 */
	public static void createNewTraveller(ArrayList<City> arrayCities, int age) {

		ArrayList<City> recommendedCities = new ArrayList<City>();

		ArrayList<String> namesCities;
		ArrayList<String> convertNamesCities;
		String closestCity;
		boolean flag;
		PerceptronTraveller newTraveller = null;

		flag = true; // SET BOOLEAN ------------

		if (age >= 16 && age <= 115) {
			if ((age >= 16) && (age <= 25)) {
				newTraveller = new PerceptronYoungTraveller();

			} else if (age <= 60) {
				newTraveller = new PerceptronMiddleTraveller();

			} else if (age <= 115) {
				newTraveller = new PerceptronElderTraveller();
			}
			if (newTraveller != null) {

				namesCities = newTraveller.recommend(arrayCities, recommendedCities);
				System.out.println("\n\nRecommened:");
				for (String city : namesCities) {
					System.out.println("  ---  " + city);
				}
				convertNamesCities = newTraveller.recommend(flag, namesCities);
				System.out.println("\n\n BOOLEAN = " + flag);
				for (String city : convertNamesCities) {
					System.out.println(" --- " + city);
				}
				///////////////////////////////////////
				// import static TownRecommendationsTraveler.ClosestCity.nearbyCity;
				/**
				 * closest City
				 */
				closestCity = nearbyCity(newTraveller, arrayCities);

				System.out.println("\n\nclosest city = " + closestCity);

				ArrayList<City> list = new ArrayList<City>();

				for (int i = 0; i < recommendedCities.size(); i++) {
					list.add(recommendedCities.get(i));
				}
				newTraveller.sortReccomendations(list);
			}
		}
	}
}

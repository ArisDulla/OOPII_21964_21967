package TownRecommendationsTraveler;

import java.util.ArrayList;
import static TownRecommendationsTraveler.ClosestCity.nearbyCity;

/**
 * 
 * Create new traveller
 *
 */
public class NewTraveller {

	public static void createNewTraveller(City[] arrayCities) {

		ArrayList<String> namesCities;
		ArrayList<String> convertNamesCities;
		String closestCity;
		int age;
		boolean flag;

		PerceptronTraveller newTraveller = null;

		flag = true; // SET BOOLEAN ------------
		age = 19; // SET AGE ------------------

		if (age >= 16 && age <= 115) {
			if ((age >= 16) && (age <= 25)) {
				newTraveller = new PerceptronYoungTraveller();

			} else if (age <= 60) {
				newTraveller = new PerceptronMiddleTraveller();

			} else if (age <= 115) {
				newTraveller = new PerceptronElderTraveller();
			}
			if (newTraveller != null) {

				namesCities = newTraveller.recommend(arrayCities);
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
				// nearbyCity(newTraveller, arrayCities);
				// import static TownRecommendationsTraveler.ClosestCity.nearbyCity;

				closestCity = nearbyCity(newTraveller, arrayCities);

				System.out.println("\n\nclosest city = " + closestCity);
			}
		}
	}
}

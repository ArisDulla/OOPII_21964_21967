package TownRecommendationsTraveler;

import static TownRecommendationsTraveler.Inputs.*;

import java.util.ArrayList;
import java.util.HashMap;

import static TownRecommendationsTraveler.MapValues.*;

/**
 * @author Aris ,Nikolas
 * @since 2021-12-7
 */

public class Main {

	public static void main(String[] args) throws Exception {

		ArrayList<City> arrayTowns = new ArrayList<City>();
		HashMap<String, ArrayList<String>> hm;
		int age, num;
		JacksonTester tester = new JacksonTester();
		City newCity;
		ArrayList<City> newArrayTowns = new ArrayList<City>();

		/**
		 * READ IN FILE and load
		 */
		tester.call(arrayTowns);

		/**
		 * CREAT HASH MAP and SET VALUES FORM ARAY TOWNS and store
		 */
		hm = creatHashMap();
		for (City f : arrayTowns)
			setValues(f, hm);

		// -----------------------------------------------------------------------------------------------------
		// MENU
		// 1. ADD NEW CITY
		// 2. Recommend City
		// 3. PRINT hash map
		// 4. EXIT

		do {

			num = inputMenu();

			/**
			 * ADD NEW CITY
			 */
			if (num == 1) {
				newCity = City.createNewCity(arrayTowns);

				if (newCity != null) {
					arrayTowns.add(newCity);
					newArrayTowns.add(newCity);
					setValues(newCity, hm);
				}
			}
			/**
			 * input AGE and Recommend City
			 */
			else if (num == 2) {
				age = inputAge();
				NewTraveller.createNewTraveller(arrayTowns, age);
			}
			/**
			 * PRINT HASH MAP
			 */
			else if (num == 3) {

				printHashMap(hm);
			}
			/**
			 * EXIT
			 */
			else if (num == 4) {
				System.out.println("EXIT thank you!!");
				break;
			}
			/**
			 * WRONG INPUT
			 */
			else {
				System.out.println("TYPE AGAIN");
			}

		} while (true);
		/////////////////////////////////////////////// END MENU
		//
		/**
		 * SAVE NEW CITIES IN FILE
		 */
		if (newArrayTowns.size() > 0)
			tester.addNewCity(newArrayTowns);
	}
}
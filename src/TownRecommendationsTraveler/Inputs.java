package TownRecommendationsTraveler;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Inputs {

	/**
	 * INPUT NAME CITY
	 */
	protected static boolean inputNameCity(ArrayList<City> cities, String[] cityNameDomain, JLabel statusLabel) {

		String city = cityNameDomain[0];
		String domain = cityNameDomain[0];

		int flag = 0;
		if (match(city, cities, statusLabel)) {
			flag = 1;
		}
		
		if (!city.matches("[a-z]+")) {
			flag = 1;
		}
	
		if (!domain.matches("[a-z]+")) {
			flag = 1;
		}
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Search in array if exists is the same name
	 */
	public static boolean match(String nameCity, ArrayList<City> cities, JLabel statusLabel) {

		
		for (City x : cities) {
			if (nameCity.equals(x.getCityName())) {
				statusLabel
						.setText("Yparchei eidi --- " + x.getCityName() + "  Timestamp = " + x.getDate() + " ----  ");
				return true;
			}
		}
		return false;
	}
}
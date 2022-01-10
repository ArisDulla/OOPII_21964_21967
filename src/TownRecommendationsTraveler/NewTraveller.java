package TownRecommendationsTraveler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static TownRecommendationsTraveler.ClosestCity.nearbyCity;

public class NewTraveller {

	/**
	 * CREAT NEW TRAVELLER and recommend cities
	 * 
	 * @param arrayCities
	 * @param age
	 */
	public static void createNewTraveller(ArrayList<Integer> explicitly, JScrollPane jsp2, ArrayList<City> arrayTowns) {

		// System.out.println(explicitly);
		ArrayList<City> recommendedCities = new ArrayList<City>();

		ArrayList<String> namesCities;
		ArrayList<String> convertNamesCities;
		String closestCity;
		boolean flag;
		PerceptronTraveller newTraveller = null;
		ArrayList<String> namesCities1;
		ArrayList<City> list;
		int age = explicitly.get(0);

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

				namesCities = newTraveller.recommend(arrayTowns, recommendedCities);
				convertNamesCities = newTraveller.recommend(flag, namesCities);

				///////////////////////////////////////
				// import static TownRecommendationsTraveler.ClosestCity.nearbyCity;
				/**
				 * closest City
				 */
				closestCity = nearbyCity(newTraveller, arrayTowns);

				list = new ArrayList<City>();

				for (int i = 0; i < recommendedCities.size(); i++) {
					list.add(recommendedCities.get(i));
				}
				namesCities1 = newTraveller.sortReccomendations(list); // ----------------------------------------

				String[] columnNames = { "Recommened", "BOOLEAN " + flag, "Closest city", "classification", " <--> " };

				Object[][] data = new Object[namesCities.size() + 2][5];

				for (int i = 0; i < namesCities.size(); i++) {

					data[i][0] = namesCities.get(i);
				}
				for (int i = 0; i < convertNamesCities.size(); i++) {

					data[i][1] = convertNamesCities.get(i);
				}

				data[0][2] = closestCity;

				for (int i = 0; i < convertNamesCities.size(); i++) {

					data[i][3] = namesCities1.get(i);
				}
				if (age > 25 && age <= 60) {
					for (int i = 0; i < convertNamesCities.size(); i++) {

						data[i][4] = list.get(i).getDate();
					}
				} else {
					for (int i = 0; i < convertNamesCities.size(); i++) {

						data[i][4] = list.get(i).getDistance() * 15325;
					}
				}

				DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
				JTable studentsTable = new JTable(dtm);
				studentsTable.setFont(new Font("monospaced", Font.PLAIN, 30));
				studentsTable.setRowHeight(30);

				JTableHeader tableHeader = studentsTable.getTableHeader();
				tableHeader.setBackground(Color.LIGHT_GRAY);
				tableHeader.setForeground(Color.black);
				Font headerFont = new Font("Verdana", Font.PLAIN, 24);
				tableHeader.setFont(headerFont);

				jsp2.setPreferredSize(new Dimension(1400, 400));
				jsp2.getViewport().add(studentsTable);

			}
		}
	}
}

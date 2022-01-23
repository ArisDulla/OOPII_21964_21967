package TownRecommendationsTraveler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static TownRecommendationsTraveler.ClosestCity.nearbyCity;

public class NewTraveller {

	public static PerceptronTraveller startRecommend(ArrayList<Integer> explicitly, JScrollPane jsp2,
			JLabel statusLabel) {

		boolean flag = true;
		int[] candidateTravellerCriteria;
		String[] columnNames = { "Recommened", "BOOLEAN " + flag, "Closest city", "classification", " <--> " };
		Object[][] data;
		PerceptronTraveller newTraveller = null;
		ArrayList<String> convertNamesCities, namesCities;
		ArrayList<City> cities;
		int age;
		int len;
		String closestCity;
		ArrayList<String> namesCities1;

		candidateTravellerCriteria = new int[10];
		age = (int) explicitly.get(0);
		if ((age >= 16) && (age <= 25)) {
			newTraveller = new PerceptronYoungTraveller();

		} else if ((age > 25) && (age <= 60)) {
			newTraveller = new PerceptronMiddleTraveller();

		} else if ((age > 60) && (age <= 115)) {
			newTraveller = new PerceptronElderTraveller();
		}
		candidateTravellerCriteria[0] = (int) explicitly.get(1);
		candidateTravellerCriteria[1] = (int) explicitly.get(2);
		candidateTravellerCriteria[2] = (int) explicitly.get(3);
		candidateTravellerCriteria[3] = (int) explicitly.get(4);
		candidateTravellerCriteria[4] = (int) explicitly.get(5);
		candidateTravellerCriteria[5] = (int) explicitly.get(6);
		candidateTravellerCriteria[6] = (int) explicitly.get(7);
		candidateTravellerCriteria[7] = 0;
		candidateTravellerCriteria[8] = 0;
		candidateTravellerCriteria[8] = 0;

		Map<City, Double> result = newTraveller.personalized(candidateTravellerCriteria);

		// TABLE
		data = new Object[5][5];

		namesCities = new ArrayList<>();
		len = result.size();

		if (len != 0) {

			cities = new ArrayList<>(result.keySet());

			for (City x : cities) {
				namesCities.add(x.getCityName());
			}

			convertNamesCities = newTraveller.recommend(flag, namesCities);
			closestCity = nearbyCity(newTraveller, namesCities);
			namesCities1 = newTraveller.sortReccomendations(cities);

			for (int i = 0; i < len; i++) {

				data[i][0] = namesCities.get(i);
				data[i][1] = convertNamesCities.get(i);
				data[i][3] = namesCities1.get(i);

			}
			data[0][2] = closestCity;

			if (age > 25 && age <= 60) {

				for (int i = 0; i < len; i++) {

					data[i][4] = cities.get(i).getDate();
				}
			} else {
				for (int i = 0; i < len; i++) {

					data[i][4] = cities.get(i).getDistance() * 15325;
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

			jsp2.setPreferredSize(new Dimension(900, 400));
			jsp2.getViewport().add(studentsTable);

		} else {

			statusLabel.setText("not found CITIES");
			return null;

		}
		return newTraveller;
	}
}

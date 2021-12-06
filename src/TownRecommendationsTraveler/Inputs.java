package TownRecommendationsTraveler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {

	/**
	 * INPUT NAME CITY
	 */
	protected static boolean inputNameCity(ArrayList<City> cities, String[] cityNameDomain) throws Exception {

		String city = null;
		String domain = null;
		int notInt1 = 0;
		int notInt = 0;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader stdin1 = new BufferedReader(new InputStreamReader(System.in));

		while (notInt == 0) {
			try {
				System.out.print("Type city name: ");
				city = stdin.readLine();

				if (!city.matches("[a-z]+")) {
					throw new NumberFormatException();

				}
				if (match(city, cities)) {
					throw new Exception();
				}
				break;

			} catch (NumberFormatException e) {
				System.out.println("That is not String lowercase, please try again.");

			} catch (Exception w) {
				return true;
			}
		}

		while (notInt1 == 0) {
			try {
				System.out.print("Type city DOMAIN: ");
				domain = stdin1.readLine();

				if (!domain.matches("[a-z]+")) {
					throw new NumberFormatException();
				}
				break;

			} catch (NumberFormatException e) {
				System.out.println("That is not String lowercase, please try again.");

			}
		}
		cityNameDomain[0] = String.valueOf(city);
		cityNameDomain[1] = String.valueOf(domain);

		return false;
	}

	/**
	 * Search in array if exists is the same name
	 * 
	 */
	public static boolean match(String nameCity, ArrayList<City> cities) {

		for (City x : cities) {

			if (nameCity.equals(x.getCityName())) {
				System.out.println("\n\nyparchei eidi");
				System.out.println("\n --- " + x.getCityName() + "  Timestamp = " + x.getDate() + " ----  ");
				return true;
			}
		}
		return false;
	}

	/**
	 * INPUT AGE
	 * 
	 * @return AGE
	 * @throws Exception WRONG INPUT
	 */
	public static int inputAge() throws Exception {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int age;

		age = -1;
		while (age != 0) {
			try {
				System.out.print("Enter ur age : ");
				age = input.nextInt();

				if (age < 16 || age > 115) {
					throw new IllegalArgumentException();
				}
				break;

			} catch (InputMismatchException exception) {
				System.out.println("Integers only, please.");
				input.next();
			} catch (IllegalArgumentException a) {
				System.out.println("Age can not be negative or more than 115  >>  age >= 16 and age <= 115 ");
			}
		}
		return age;
	}

	/**
	 * INPUT NUM OF MENU
	 * 
	 * @return number
	 * @throws Exception WRONG INPUT
	 */
	public static int inputMenu() throws Exception {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int num;

		num = -1;
		while (num != 0) {
			try {
				System.out.println("\n\n" + "                      MENU \n"
						+ "-----------------------------------------------------------------\n" + "\n\n"
						+ "1. ADD NEW CITY    \n" + "2. Recommend City  \n" + "3. PRINT hash map  \n" + "4. EXIT");
				System.out.print("\n\nEnter num : ");
				num = input.nextInt();

				if (num <= 0 || num > 4) {
					throw new IllegalArgumentException();
				}
				break;

			} catch (InputMismatchException exception) {

				System.out.println("Integers only, please.");
				input.next();

			} catch (IllegalArgumentException a) {
				System.out.println("WRONG INPUT 1-4 ");
			}
		}
		return num;
	}
}

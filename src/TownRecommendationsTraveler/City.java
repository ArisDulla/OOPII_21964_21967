package TownRecommendationsTraveler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.WikipediaNoArcticleException;

import static TownRecommendationsTraveler.RetrieveData.*;
import static TownRecommendationsTraveler.GeodesicDistance.*;
import static TownRecommendationsTraveler.SearchWithinArticle.*;
import static TownRecommendationsTraveler.NormalizeFeatures.*;

/**
 * 
 * City
 *
 */
public class City {

	private final int sizeVectorFeatures = 10; // Size of the vector representation features
	// Eνα vector representation ως ένα array με 10 features (κριτήρια) της πόλης.
	private double[] vectorFeatures = new double[sizeVectorFeatures];
	// [0] = CAFE , [1] = SEA, [2] = MUSEUMS, [3] = RESTAURANTS, [4] = STADIUM,
	// [5] = BAR ,[6] = AMUSEMENT PARK, [7] = KLEVIN, [8] = CLOUNDS, [9] = COORDS

	private String cityName;

	/**
	 * Constructor city
	 * 
	 * The Elements ( VECTORS ) set in array vectors:
	 * 
	 * @param cafe
	 * @param sea
	 * @param museums
	 * @param restaurant
	 * @param stadium
	 * @param bar
	 * @param amusementPark
	 * @param klevin
	 * @param clounds
	 * @param coords
	 * @param cityName
	 */
	public City(double cafe, double sea, double museums, double restaurant, double stadium, double bar,
			double amusementPark, double klevin, double clounds, double coords, String cityName) {

		this.vectorFeatures[0] = cafe;
		this.vectorFeatures[1] = sea;
		this.vectorFeatures[2] = museums;
		this.vectorFeatures[3] = restaurant;
		this.vectorFeatures[4] = stadium;
		this.vectorFeatures[5] = bar;
		this.vectorFeatures[6] = amusementPark;
		this.vectorFeatures[7] = klevin;
		this.vectorFeatures[8] = clounds;
		this.vectorFeatures[9] = coords;
		this.cityName = cityName;
	}

	/////////////
	// Setters

	public void setVectorFeatures(double[] vectorFeatures) {
		this.vectorFeatures = vectorFeatures;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	///////////
	// Getters

	public double[] getVectorFeatures() {
		return vectorFeatures;
	}

	public double getDistance() {
		return vectorFeatures[9];
	}

	public String getCityName() {
		return cityName;
	}
	//////////

	protected static City[] createObjects() throws IOException, WikipediaNoArcticleException {
		City[] arrayTowns = new City[10];

		System.out.println(" \n\n" + " ---- RETRIEVE DATA loading...");

		arrayTowns[0] = createNewCity("rome", "it"); // ROME
		arrayTowns[1] = createNewCity("corfu", "gr"); // CORFU
		arrayTowns[2] = createNewCity("berlin", "de"); // BERLIN
		arrayTowns[3] = createNewCity("london", "uk"); // LONDON
		arrayTowns[4] = createNewCity("zurich", "ch"); // ZURICH
		arrayTowns[5] = createNewCity("toronto", "ca"); // TORONTO
		arrayTowns[6] = createNewCity("boston", "us"); // BOSTON
		arrayTowns[7] = createNewCity("moscow", "ru"); // MOSCOW
		arrayTowns[8] = createNewCity("beijing", "cn"); // BEIJING
		arrayTowns[9] = createNewCity("paris", "fr"); // WELLINGTON

		return arrayTowns;
	}

	private static City createNewCity(String city, String domain) throws IOException, WikipediaNoArcticleException {
		double lat, lon, temperature, clounds, distance, coords, klevin, cloodsNormalize, articleVectors[];

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		int flag;
		String article = null, unit = "K";
		int[] lenghtVector;

		/////////////// ------- OPEN DATA WEATHER MAP
		// RETRIEVE DATA ## import static TownRecommendationsTraveler.RetrieveData.*;

		flag = 1;
		while (flag == 1) { // ------------------------------------- ##TRY CATCH NAME CITY
			try {
				/**
				 * GET ARTICLE
				 *
				 */
				article = getArticleWikipedia(city);
				flag = 0;
				break;

			} catch (WikipediaNoArcticleException e) {
				System.out.println("\n" + e.getMessage());
				System.out.print("Type a correct city name: ");
				city = stdin.readLine();
				flag = 1;
				continue;
			}
		}
		/**
		 * 
		 * GET LON
		 */
		lon = getLonOpenWeatherMap(city, domain);
		/**
		 * 
		 * GET LAT
		 */
		lat = getLatOpenWeatherMap(city, domain);
		/**
		 * 
		 * GET TEMPERATURE
		 */
		temperature = getTemperatureOpenWeatherMap(city, domain);
		/**
		 * 
		 * GET CLOUNDS
		 */
		clounds = getCloundsOpenWeatherMap(city, domain);

		///////////////////
		// GEODESIC DISTANCE ## import static GeodesicDistance

		/**
		 * 
		 * Get distance athens CITY
		 */
		distance = distance(37.9795, 23.7162, lat, lon, unit);

		//////////////////////////////////////////
		// Search with in article and count words ## import static SearchWithinArticle

		lenghtVector = searchWordsInArticle(article);

		////////////////////
		// Normalize Features ## import static NormalizeFeatures

		articleVectors = articleNormalize(lenghtVector);// #1
		coords = distanceNormalize(distance); // #2
		klevin = tempNormalize(temperature); // #3
		cloodsNormalize = cloodsNormalize(clounds); // #4

		////////////////////
		// CREATE NEW CITY #1 #2 #3 #4

		City newCity = new City(articleVectors[0], articleVectors[1], articleVectors[2], articleVectors[3],
				articleVectors[4], articleVectors[5], articleVectors[6], klevin, cloodsNormalize, coords, city);

		return newCity;
	}

	////////////
	// To String
	public String toString() {

		return "VECTOR \n\n CAFE =  " + vectorFeatures[0] + "\n\n SEA   " + vectorFeatures[1] + "\n\n Museums  "
				+ vectorFeatures[2] + "\n\n restaurant   " + vectorFeatures[3] + "\n\n Stadium   " + vectorFeatures[4]
				+ "\n\n bar  " + vectorFeatures[5] + "\n\nAmusementPark  " + vectorFeatures[6] + "\n\nklevin  "
				+ vectorFeatures[7] + "\n\nclounds  " + vectorFeatures[8] + "\n\ncoords  " + vectorFeatures[9];
	}
}

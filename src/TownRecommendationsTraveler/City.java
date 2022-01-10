package TownRecommendationsTraveler;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import exception.OpenDataNoDataException;
import exception.WikipediaNoArcticleException;

import static TownRecommendationsTraveler.RetrieveData.*;
import static TownRecommendationsTraveler.GeodesicDistance.*;
import static TownRecommendationsTraveler.SearchWithinArticle.*;
import static TownRecommendationsTraveler.NormalizeFeatures.*;

import static TownRecommendationsTraveler.Inputs.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

	public City() {
	}

	// Comparator<City>
	private final int sizeVectorFeatures = 10; // Size of the vector representation features
	// Eνα vector representation ως ένα array με 10 features (κριτήρια) της πόλης.
	private double[] vectorFeatures = new double[sizeVectorFeatures];
	// [0] = CAFE , [1] = SEA, [2] = MUSEUMS, [3] = RESTAURANTS, [4] = STADIUM,
	// [5] = BAR ,[6] = AMUSEMENT PARK, [7] = KLEVIN, [8] = CLOUNDS, [9] = COORDS

	private String cityName;
	private long date;

	/**
	 * Constructor city The Elements ( VECTORS 0-1) set in array vectors:
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

		Instant instant = Instant.now();
		this.date = instant.getEpochSecond();
	}

	/**
	 * CREAT NEW CITY
	 * 
	 * @param cities (add new city in array (cities))
	 * @param statusLabel 
	 * @return new city
	 * @throws Exception WRONG INPUT
	 */
	public static City createNewCity(ArrayList<City> cities,String[] cityNameDomain, JLabel statusLabel)  {
		double lat, lon = 0, temperature, clounds, distance, coords, klevin, cloodsNormalize, articleVectors[];

		// ------------- STEPS----------------------
		//
		// 1. Input new city
		// 2. Collect DATA form WIKI AND OPEN DATA
		// 3. Get distance athens to new CITY
		// 4. Search with in article and count words
		// 5. Normalize DATA
		// 6. create new city (DATA) -

		int flag, flag2;
		String article = null, unit = "K";
		int[] lenghtVector;
		double[] array = new double[4];
		String city;

		////////////////////// OPEN DATA WEATHER MAP //////////////////////////////////
		/**
		 * RETRIEVE DATA ## import static TownRecommendationsTraveler.RetrieveData.*;
		 */

		/**
		 * input name new city
		 */
		if (inputNameCity(cities, cityNameDomain,statusLabel)) {
			return null;
		}
		flag = 1;
		while (flag == 1) { // ------------------------------------- ##TRY CATCH NAME CITY
			try {
				/**
				 * GET ARTICLE
				 */
				article = getArticleWikipedia(cityNameDomain[0]);
				flag = 0;
				break;

			} catch (WikipediaNoArcticleException e) {

				statusLabel.setText(e.getMessage());
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		/**
		 * GET LON , LAT ,TEMPERATUR ,clounds form OpenWeatherMap
		 */
		flag2 = 1;
		while (flag2 == 1) { // ------------------------------------- ##TRY CATCH NAME CITY
			try {
				/**
				 * GET DATA form OpenWeatherMap
				 *
				 */
				getDataOpenWeatherMap(cityNameDomain[0], cityNameDomain[1], array);
				flag2 = 0;
				break;

			} catch (OpenDataNoDataException e) {

				statusLabel.setText(e.getMessage());
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		if (flag2 == 1) {
			return null;
		}

		city = cityNameDomain[0];

		lon = array[0];
		lat = array[1];
		temperature = array[2];
		clounds = array[3];

		//////////////////////////////////////////////////////////////
		/**
		 * Get distance athens CITY ## import static GeodesicDistance
		 */
		distance = distance(37.9795, 23.7162, lat, lon, unit);

		//////////////////////////////////////////////////////////////
		/**
		 * Search with in article and count words ## import static SearchWithinArticle
		 */
		lenghtVector = searchWordsInArticle(article);

		////////////////////////////////////////////////////////
		/**
		 * Normalize Features ## import static NormalizeFeatures
		 */
		articleVectors = articleNormalize(lenghtVector);// #1
		coords = distanceNormalize(distance); // #2
		klevin = tempNormalize(temperature); // #3
		cloodsNormalize = cloodsNormalize(clounds); // #4

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		/**
		 * CREATE NEW CITY #1 #2 #3 #4
		 */
		City newCity = new City(articleVectors[0], articleVectors[1], articleVectors[2], articleVectors[3],
				articleVectors[4], articleVectors[5], articleVectors[6], klevin, cloodsNormalize, coords, city);
		/*
		 * @return new object city
		 */
		return newCity;

	}
/////////////////////////////////////////////////////////////////////////////////////////////////
// Setters

	protected void setVectorFeatures(double[] vectorFeatures) {
		this.vectorFeatures = vectorFeatures;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setCafeVector(double cafe) {
		this.vectorFeatures[0] = cafe;
	}

	public void getSeaVector(double sea) {
		this.vectorFeatures[1] = sea;
	}

	public void setMuseumsVector(double museums) {
		this.vectorFeatures[2] = museums;
	}

	public void setRestaurantVector(double restaurant) {
		this.vectorFeatures[3] = restaurant;
	}

	public void setStadiumVector(double stadium) {
		this.vectorFeatures[4] = stadium;
	}

	public void setBarVector(double bar) {
		this.vectorFeatures[5] = bar;
	}

	public void setAmusementParkVector(double amusement) {
		this.vectorFeatures[6] = amusement;
	}

	public void setKlevinVector(double klevin) {
		this.vectorFeatures[7] = klevin;
	}

	public void setCloundsVector(double clounds) {
		this.vectorFeatures[8] = clounds;
	}

	public void setDistance(double distance) {
		this.vectorFeatures[9] = distance;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

////////////////////////////////////////////////////////////////////////////////////////////////
// Getters
	public long getDate() {
		return date;
	}

	public double getCafeVector() {
		return vectorFeatures[0];
	}

	public double getSeaVector() {
		return vectorFeatures[1];
	}

	public double getMuseumsVector() {
		return vectorFeatures[2];
	}

	public double getRestaurantVector() {
		return vectorFeatures[3];
	}

	public double getStadiumVector() {
		return vectorFeatures[4];
	}

	public double getBarVector() {
		return vectorFeatures[5];
	}

	public double getAmusementParkVector() {
		return vectorFeatures[6];
	}

	public double getKlevinVector() {
		return vectorFeatures[7];
	}

	public double getCloundsVector() {
		return vectorFeatures[8];
	}

	public double getDistance() {
		return vectorFeatures[9];
	}

	public String getCityName() {
		return cityName;
	}

	protected double[] getVectorFeatures() {
		return vectorFeatures;
	}
}
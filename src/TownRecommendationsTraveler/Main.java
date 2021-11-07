package TownRecommendationsTraveler;

import java.io.IOException;

import exception.WikipediaNoArcticleException;

/**
 * 
 * @author Aris ,Nikolas
 * 
 *         MAIN
 *
 */

public class Main {

	public static void main(String[] args) throws IOException, WikipediaNoArcticleException {

		City[] arrayTowns;

		arrayTowns = City.createObjects();

		NewTraveller.createNewTraveller(arrayTowns);
	}
}

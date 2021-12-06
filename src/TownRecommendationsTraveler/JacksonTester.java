package TownRecommendationsTraveler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {

	/**
	 * READ DATA FORM FILE and save in arrayTowns
	 * 
	 * @param arrayTowns copy cities
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ParseException
	 */
	protected void call(ArrayList<City> arrayTowns)
			throws JsonParseException, JsonMappingException, IOException, ParseException {

		JacksonTester tester = new JacksonTester();
		ArrayList<City> x;

		/**
		 * CREAT NEW FILE FLAG OFF
		 */
		// tester.writeJSON(arrayTowns);

		/**
		 * READ FORM FILE
		 */
		x = tester.readJSON();

		// test
		// arrayTowns=(ArrayList<City>) x.clone();

		/**
		 * copy cities to array
		 */
		arrayTowns.addAll(x);
	}

	/**
	 * CREAT NEW FILE
	 * 
	 * @param arrayTowns
	 *
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	protected void writeJSON(ArrayList<City> arrayTowns)
			throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		File resultFile = new File("cities.json");
		mapper.writeValue(resultFile, arrayTowns);
	}

	/**
	 * READ DATA FORM FILE
	 * 
	 * @return arrayCities
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ParseException
	 */
	protected ArrayList<City> readJSON() throws JsonParseException, JsonMappingException, IOException, ParseException {

		ObjectMapper mapper = new ObjectMapper();

		ArrayList<City> x = new ArrayList<>();

		// mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		File resultFile = new File("cities.json");
		x = mapper.readValue(resultFile, mapper.getTypeFactory().constructCollectionType(List.class, City.class));

		return x;
	}

	/**
	 * ADD NEW CITIES IN FILE
	 * 
	 * @param newArrayTowns
	 */
	@SuppressWarnings("unchecked")
	protected void addNewCity(ArrayList<City> newArrayTowns) {

		JSONParser jsonParser = new JSONParser();

		try {
			Object obj = jsonParser.parse(new FileReader("cities.json"));
			JSONArray jsonArray = (JSONArray) obj;

			// "cafeVector":0.0,"restaurantVector":0.0,"cloundsVector":0.4,"stadiumVector":0.3,
			// "cityName":"rome","distance":0.06856826654895085,"museumsVector":0.7,"amusementParkVector":0.0,
			// "seaVector":0.5,"klevinVector":0.6950340136054423,"barVector":0.0}

			/**
			 * SET NEW CITIES IN FILE
			 */
			for (City newCity : newArrayTowns) {

				JSONObject city1 = new JSONObject();

				city1.put("date", newCity.getDate());
				city1.put("cafeVector", newCity.getCafeVector());
				city1.put("restaurantVector", newCity.getRestaurantVector());
				city1.put("cloundsVector", newCity.getCloundsVector());
				city1.put("stadiumVector", newCity.getStadiumVector());
				city1.put("cityName", newCity.getCityName());
				city1.put("distance", newCity.getDistance());
				city1.put("museumsVector", newCity.getMuseumsVector());
				city1.put("amusementParkVector", newCity.getAmusementParkVector());
				city1.put("seaVector", newCity.getSeaVector());
				city1.put("klevinVector", newCity.getKlevinVector());
				city1.put("barVector", newCity.getBarVector());

				jsonArray.add(city1);
			}

			FileWriter file = new FileWriter("cities.json");
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
}
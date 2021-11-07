package TownRecommendationsTraveler;

import java.io.IOException;
import java.net.URL;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import exception.WikipediaNoArcticleException;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

/**
 * 
 * @author aris
 * 
 *         Retrieve data, Open Weather Map
 */

public class RetrieveData {

	private final static String appid = "5e2b77ded0426c75a14930d4f5ec54bb";

	// GET LOT
	protected static double getLonOpenWeatherMap(String city, String domain) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
				OpenWeatherMap.class);

		double lon;

		lon = weather_obj.getCoord().getLon();

		return lon;
	}

	// GET LON
	protected static double getLatOpenWeatherMap(String city, String domain) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
				OpenWeatherMap.class);

		double lat;

		lat = weather_obj.getCoord().getLat();

		return lat;
	}

	// GET TEMPERATURE
	protected static double getTemperatureOpenWeatherMap(String city, String domain) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
				OpenWeatherMap.class);

		double temperature;

		temperature = (weather_obj.getMain()).getTemp();

		return temperature;
	}

	// GET CLOUNDS
	protected static double getCloundsOpenWeatherMap(String city, String domain) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
				OpenWeatherMap.class);

		double clounds;

		clounds = weather_obj.getClouds().getAll();

		return clounds;
	}

	// GET ARTICLE WIKPEDIA
	protected static String getArticleWikipedia(String city) throws IOException, WikipediaNoArcticleException {

		String article = "";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client
				.resource(UriBuilder.fromUri("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="
						+ city + "&format=json&formatversion=2").build());
		ObjectMapper mapper = new ObjectMapper();

		String json = service.accept(MediaType.APPLICATION_JSON).get(String.class);
		if (json.contains("pageid")) {
			MediaWiki mediaWiki_obj = mapper.readValue(json, MediaWiki.class);
			article = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		} else
			throw new WikipediaNoArcticleException(city);

		return article;
	}
}
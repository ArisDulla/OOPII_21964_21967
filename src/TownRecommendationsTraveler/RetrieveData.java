package TownRecommendationsTraveler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import exception.OpenDataNoDataException;
import exception.WikipediaNoArcticleException;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

/**
 * Retrieve data, Open Weather Map ,Wikipedia
 */
public class RetrieveData {

	private final static String appid = "5e2b77ded0426c75a14930d4f5ec54bb";

	/**
	 * Retrieve data form Open Weather Map
	 */
	protected static void getDataOpenWeatherMap(String city, String domain, double[] array)
			throws IOException, OpenDataNoDataException {

		ObjectMapper mapper = new ObjectMapper();
		double lon, lat, temperature, clounds;

		try {
			OpenWeatherMap weather_obj = mapper.readValue(new URL(
					"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
					OpenWeatherMap.class);

			lon = weather_obj.getCoord().getLon();
			lat = weather_obj.getCoord().getLat();
			temperature = (weather_obj.getMain()).getTemp();
			clounds = weather_obj.getClouds().getAll();

		} catch (JsonParseException e) {
			throw new OpenDataNoDataException(city);
		} catch (MalformedURLException e) {
			throw new OpenDataNoDataException(city);
		} catch (NullPointerException e) {
			throw new OpenDataNoDataException(city);
		} catch (Exception e) {
			throw new OpenDataNoDataException(city);
		}

		array[0] = lon;
		array[1] = lat;
		array[2] = temperature;
		array[3] = clounds;

	}

	/**
	 * Retrieve Article form Wikipedia
	 */
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
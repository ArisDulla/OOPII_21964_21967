package TownRecommendationsTraveler;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

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
public class RetrieveData implements Runnable {

	private final static String appid = "5e2b77ded0426c75a14930d4f5ec54bb";
	private String city, domain;
	private double[] array;
	private ObjectMapper mapper;

	public RetrieveData(String city2, String country, double[] array, ObjectMapper mapper) {

		this.city = city2;
		this.domain = country;
		this.array = array;
		this.mapper = mapper;
	}

	/**
	 * Retrieve data form Open Weather Map
	 */
	@Override
	public void run() {

		// ObjectMapper mapper = new ObjectMapper();
		double lon = 0, lat = 0, temperature = 0, clounds = 0;

		try {

			WriteManyLogs.getObj().writeToLog(Level.INFO, " Retrieve data form Open Weather Map");

			OpenWeatherMap weather_obj = mapper.readValue(new URL(
					"http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + domain + "&APPID=" + appid + ""),
					OpenWeatherMap.class);

			lon = weather_obj.getCoord().getLon();
			lat = weather_obj.getCoord().getLat();
			temperature = (weather_obj.getMain()).getTemp();
			clounds = weather_obj.getClouds().getAll();

		} catch (Exception e) {

			WriteManyLogs.getObj().writeToLog(Level.WARNING,
					" THE CITY WAS NOT FOUND Exception Retrieve data form Open Weather Map ");

			throw new RuntimeException();
		}

		array[0] = lon;
		array[1] = lat;
		array[2] = temperature;
		array[3] = clounds;
	}

	/**
	 * Retrieve Article form Wikipedia
	 */
	protected String getArticleWikipedia(String city) throws IOException, WikipediaNoArcticleException {

		WriteManyLogs.getObj().writeToLog(Level.INFO, " Retrieve Article form Wikipedia ");

		String article = "";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client
				.resource(UriBuilder.fromUri("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="
						+ city + "&format=json&formatversion=2").build());

		String json = service.accept(MediaType.APPLICATION_JSON).get(String.class);

		if (json.contains("pageid")) {
			MediaWiki mediaWiki_obj = mapper.readValue(json, MediaWiki.class);
			article = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		} else {

			WriteManyLogs.getObj().writeToLog(Level.WARNING,
					" THE CITY WAS NOT FOUND  Exception Retrieve Article form Wikipedia ");
			throw new WikipediaNoArcticleException(city);
		}

		return article;
	}

	public static String value(String city, String country, double[] array)
			throws InterruptedException, IOException, WikipediaNoArcticleException, OpenDataNoDataException {

		AtomicReference<Throwable> errorReference = new AtomicReference<>();

		String article1;
		ObjectMapper mapper = new ObjectMapper();
		RetrieveData data = new RetrieveData(city, country, array, mapper);
		Thread thread = new Thread(data);
		thread.setUncaughtExceptionHandler((th, ex) -> {
			errorReference.set(ex);
		});

		// Data Open Weather Map
		thread.start();

		// Data WIKI
		article1 = data.getArticleWikipedia(city);

		thread.join();

		Throwable newThreadError = errorReference.get();
		if (newThreadError != null) {
			throw new OpenDataNoDataException(city);
		}

		return article1;
	}
}
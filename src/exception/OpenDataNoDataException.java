package exception;

public class OpenDataNoDataException extends Exception {

	private static final long serialVersionUID = 1L;

	String cityName;

	public OpenDataNoDataException(String in_cityName) {
		this.cityName = in_cityName;
	}

	public String getMessage() {

		return "THE CITY WAS NOT FOUND >< " + cityName + ">< ---  TYPE AGAIN -----";
	}
}

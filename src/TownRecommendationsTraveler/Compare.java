package TownRecommendationsTraveler;

import java.util.Comparator;

public class Compare {
}

/**
 * --Descending Coords-- (elder traveller)
 */
class descendingCoordsCompare implements Comparator<City> {

	public int compare(City x, City y) {
		if (x.getDistance() > y.getDistance())
			return -1;
		if (x.getDistance() < y.getDistance())
			return 1;
		else
			return 0;
	}
}

/**
 * --Ascending Coords-- (young traveller)
 */
class ascendingCoordsCompare implements Comparator<City> {

	public int compare(City x, City y) {
		if (x.getDistance() < y.getDistance())
			return -1;
		if (x.getDistance() > y.getDistance())
			return 1;
		else
			return 0;
	}
}

/**
 * --Ascending timestamp-- (middle traveller)
 */
class ascendingTimeCompare implements Comparator<City> {

	public int compare(City x, City y) {
		if (x.getDate() < y.getDate())
			return -1;
		if (x.getDate() > y.getDate())
			return 1;
		else
			return 0;
	}
}

package TownRecommendationsTraveler;

/**
 * 
 * Convert features to vector
 *
 */
public class NormalizeFeatures {

	// Distance normalize
	protected static double distanceNormalize(double distance) {
		double maxDist = 15325, sum;

		sum = distance / maxDist;
		return sum;
	}

	// Cloods normalize
	protected static double cloodsNormalize(double cloods) {
		double sum;

		if (cloods == 0) {
			return cloods;
		} else {
			sum = cloods / 100;
		}
		return sum;
	}

	// Temp normalize
	protected static double tempNormalize(double temp) {
		double min = 184.0, max = 331.0, sum;

		sum = (temp - min) / (max - min);
		return sum;
	}

	// Article normalize
	protected static double[] articleNormalize(int[] lengthFeatureKeyWords) {
		double lengthDoubleVectorKeyWords[];
		lengthDoubleVectorKeyWords = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		int i;

		for (i = 0; i < lengthDoubleVectorKeyWords.length; i++) {
			if (lengthFeatureKeyWords[i] == 0) {
				lengthDoubleVectorKeyWords[i] = 0.0;

			} else if (lengthFeatureKeyWords[i] >= 10) {
				lengthDoubleVectorKeyWords[i] = 1.0;

			} else {
				lengthDoubleVectorKeyWords[i] = lengthFeatureKeyWords[i] / 10.0;
			}
		}
		return lengthDoubleVectorKeyWords;
	}
}
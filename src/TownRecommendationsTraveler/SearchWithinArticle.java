package TownRecommendationsTraveler;

/**
 * 
 * Search with in article
 *
 */
public class SearchWithinArticle {

	protected static int[] searchWordsInArticle(String articleWikipediaStirngText) {

		String[][] vectorsKeyWords;
		String articleWikipedia[];
		int lenghtArticleWikipedia, lengthFeatureKeyWords[], lengthRowArrayVectorsKeyWords,
				lenghtColumnArrayVectorsKeyWords, flag;

		vectorsKeyWords = new String[][] { { "cafe", "Cafe", "CAFE" }, { "sea", "Sea", "SEA" },
				{ "museums", "Museums", "MUSEUMS", }, { "restaurant", "Restaurant", "RESTAURANT" },
				{ "stadium", "Stadium", "STADIUM" }, { "bar", "Bar", "BAR" },
				{ "amusementPark", "AmusementPark", "AMUSEMENTPARK" } };

		lengthFeatureKeyWords = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		articleWikipedia = articleWikipediaStirngText.split(" "); // convert string article to array
		lenghtArticleWikipedia = articleWikipedia.length;

		lenghtColumnArrayVectorsKeyWords = vectorsKeyWords.length;

		int i, j, v;
		flag = 0;
		for (i = 0; i < lenghtArticleWikipedia; i++) {
			for (j = 0; j < lenghtColumnArrayVectorsKeyWords; j++) {
				lengthRowArrayVectorsKeyWords = vectorsKeyWords[j].length;

				for (v = 0; v < lengthRowArrayVectorsKeyWords; v++) {
					if (vectorsKeyWords[j][v].equals(articleWikipedia[i])) {
						flag = 1;
					}
					if (flag == 1) {
						lengthFeatureKeyWords[j]++;
						flag = 0;
					}
				}
			}
		}
		return lengthFeatureKeyWords;
	}
}

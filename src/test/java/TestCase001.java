package test.java;

import main.java.pageEvents.CommonEvents;
import main.java.pageEvents.ImbdPage;
import main.java.pageEvents.WikiPage;
import main.java.utils.JSONReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase001 extends BaseTest {
        @Test
    public void releaseDateAndCountryCheck() throws InterruptedException, IOException, ParseException {
            CommonEvents commonEvents = new CommonEvents();
            ImbdPage imbdPage = new ImbdPage();
            WikiPage wikiPage = new WikiPage();
            JSONReader jsonReader = new JSONReader();
            JSONObject data = jsonReader.getJsonData("testdata.json");

            commonEvents.waitForPageToLoad();
            imbdPage.imdbSearchMovie((String) data.get("MovieTitle"));
            imbdPage.scrollToMovieDetails();
            String imdbMovieReleaseDate = imbdPage.imdbGetValue("CountryOfOrigin");
            String imdbMovieCountryOfOrigin = imbdPage.imdbGetValue("ReleaseDate");
            System.out.println(imdbMovieCountryOfOrigin);
            System.out.println(imdbMovieReleaseDate);
            commonEvents.launchURLInANewTab((String) data.get("wikiURL"));
            wikiPage.wikiSearchMovie((String) data.get("MovieTitle"));
            wikiPage.scrollToMovieDetails();
            String wikiMovieReleaseDate =wikiPage.wikiGetValue("ReleaseDate");
            String wikiMovieCountryOfOrigin = wikiPage.wikiGetValue("CountryOfOrigin");
            System.out.println(wikiMovieReleaseDate);
            System.out.println(wikiMovieCountryOfOrigin);
                boolean doesReleaseDateMatch = wikiMovieReleaseDate.contains("December 17") && imdbMovieReleaseDate.contains("December 17");
                boolean doesCountryMatch = imdbMovieCountryOfOrigin.matches(wikiMovieCountryOfOrigin);
                System.out.println(doesCountryMatch);
                System.out.println(doesReleaseDateMatch);

            }

}

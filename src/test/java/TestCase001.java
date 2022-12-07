package test.java;

import main.java.pageEvents.CommonEvents;
import main.java.pageEvents.ImbdPage;
import main.java.pageEvents.WikiPage;
import main.java.utils.JSONReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestCase001 extends BaseTest {
        @Test
    public void ReleaseDateAndCountryCheck() throws InterruptedException, IOException, ParseException {
            CommonEvents commonEvents = new CommonEvents();
            ImbdPage imbdPage = new ImbdPage();
            WikiPage wikiPage = new WikiPage();
            JSONReader jsonReader = new JSONReader();
            JSONObject data = jsonReader.getJsonData("testdata.json");
            SoftAssert softAssert = new SoftAssert();

            commonEvents.waitForPageToLoad();
            imbdPage.imdbSearchMovie((String) data.get("MovieTitle"));
            imbdPage.scrollToMovieDetails();
            String imdbMovieReleaseDate = imbdPage.imdbGetValue("ReleaseDate");
            String imdbMovieCountryOfOrigin = imbdPage.imdbGetValue("CountryOfOrigin");
            commonEvents.launchURLInANewTab((String) data.get("wikiURL"));
            wikiPage.wikiSearchMovie((String) data.get("MovieTitle"));
            wikiPage.scrollToMovieDetails();
            String wikiMovieReleaseDate =wikiPage.wikiGetValue("ReleaseDate");
            String wikiMovieCountryOfOrigin = wikiPage.wikiGetValue("CountryOfOrigin");
                BaseTest.logger.info("Comparing the values of country of Origin between the two websites");
            softAssert.assertTrue(wikiMovieCountryOfOrigin.matches(imdbMovieCountryOfOrigin),"Country of Origin not matches");
            if(wikiMovieCountryOfOrigin.matches(imdbMovieCountryOfOrigin)){
                    BaseTest.logger.info("Country of Origin matches between these two website for "+(data.get("MovieTitle")));}else{BaseTest.logger.info("Country of Origin does not match between these two website for "+(data.get("MovieTitle")));}
                BaseTest.logger.info("Comparing the values of Release date between the two websites");
            softAssert.assertTrue(wikiMovieReleaseDate.contains(imdbMovieReleaseDate),"Release date does not matches");
            if(wikiMovieReleaseDate.contains(imdbMovieReleaseDate)){
                        BaseTest.logger.info("Release date matches between these two website for "+(data.get("MovieTitle")));
                }else{BaseTest.logger.info("Release date does not match between these two website for "+(data.get("MovieTitle")));}
            softAssert.assertAll();
            }

}

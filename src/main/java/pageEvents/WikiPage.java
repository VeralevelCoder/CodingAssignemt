package main.java.pageEvents;

import main.java.utils.ElementFetch;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

import java.io.IOException;

public class WikiPage extends CommonEvents {

    ElementFetch elementFetch = new ElementFetch();

    public WikiPage() throws IOException, ParseException {
    }

    public void wikiSearchMovie(String movieName) throws InterruptedException {
        BaseTest.logger.info("Searching for the "+movieName+" in searchbar");
        final WebElement SearchBox = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.wikiSearchBox);
        enterTextSelection(SearchBox,movieName);
    }
    public String wikiGetValue(String movieDetail){
        BaseTest.logger.info("Retrieving movie details of "+movieDetail);
        final WebElement MovieReleaseDate = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.wikiMovieReleaseDate);
        final WebElement getImdbMovieCountryOfOrigin = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.wikiMovieCountryOfOrigin);

        switch (movieDetail){
            case "ReleaseDate":
                return getText(MovieReleaseDate);
            case "CountryOfOrigin":
                return getText(getImdbMovieCountryOfOrigin);
            default:
                throw new RuntimeException(movieDetail+" is Not a valid Movie detail");
        }
    }
    public void scrollToMovieDetails() throws InterruptedException {
        final WebElement MovieDetails = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.wikiMovieDetails);
        scrollToElement(MovieDetails);
    }

}

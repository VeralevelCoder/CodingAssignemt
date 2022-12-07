package main.java.pageEvents;

import main.java.utils.ElementFetch;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ImbdPage extends CommonEvents {
    ElementFetch elementFetch = new ElementFetch();

    public ImbdPage() throws IOException, ParseException {
    }

    public void imdbSearchMovie(String movieName) throws InterruptedException {
        final WebElement imdbSearchBox = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.imdbSearchBox);
        enterTextSelection(imdbSearchBox,movieName);
    }
    public String imdbGetValue(String movieDetail){
        final WebElement imdbMovieReleaseDate = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.imdbMovieReleaseDate);
        final WebElement getImdbMovieCountryOfOrigin = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.getImdbMovieCountryOfOrigin);

        switch (movieDetail){
            case "ReleaseDate":
                return getText(imdbMovieReleaseDate);
            case "CountryOfOrigin":
                return getText(getImdbMovieCountryOfOrigin);
            default:
                throw new RuntimeException(movieDetail+" is Not a valid Movie detail");
        }
    }
    public void scrollToMovieDetails() throws InterruptedException {
        final WebElement imdbMovieDetails = elementFetch.getWebElement("XPATH", main.java.pageObject.HomePage.imdbMovieDetails);
        scrollToElement(imdbMovieDetails);
    }
}

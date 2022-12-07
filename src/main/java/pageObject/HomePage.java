package main.java.pageObject;

public interface HomePage {

    String imdbSearchBox="//input[@id='suggestion-search']";
    String imdbMovieDetails="//section[@data-testid='Details']";
    String imdbMovieReleaseDate="//a[contains(text(),'Release date')]//parent::li/div//a";
    String getImdbMovieCountryOfOrigin="//button[contains(text(),'Country of origin')]//parent::li/div//a";
    String wikiSearchBox="//input[@id='searchInput']";
    String wikiMovieDetails="//table[@class='infobox vevent']";
    String wikiMovieReleaseDate="//div[contains(text(),'Release date')]/ancestor::tr/td//li";
    String wikiMovieCountryOfOrigin="//th[contains(text(),'Country')]/ancestor::tr/td";

}

package main.java.pageEvents;

import main.java.utils.ElementFetch;
import main.java.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.java.BaseTest;

import java.io.IOException;
import java.time.Duration;

import static test.java.BaseTest.driver;

public class CommonEvents {
    ElementFetch elementFetch = new ElementFetch();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    JSONReader jsonReader = new JSONReader();

    public CommonEvents() throws IOException, ParseException {
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        BaseTest.logger.info("Scrolling to the given element");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        Assert.assertTrue(element.isDisplayed(),"Element is not displayed");
    }
    public void enterText(WebElement element,String text){
        element.sendKeys(text);
    }
    public void enterTextSelection(WebElement element,String text) throws InterruptedException {
        BaseTest.logger.info("Enter "+text+" and clicking enter");
        element.sendKeys(text);
        Thread.sleep(1000);
        new Actions(driver).keyDown(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        new Actions(driver).keyDown(Keys.ENTER).perform();
    }
    public String getText(WebElement element){
        return element.getText();
    }
    public void waitForPageToLoad(){
        Duration Timeout = Duration.ofSeconds(20);
        BaseTest.logger.info("Waiting for the Page to load");
        WebDriverWait wait = new WebDriverWait(driver, Timeout);
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
    public void launchURLInANewTab(String url){
        BaseTest.logger.info("Switching to a new tab and opening a "+url);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
    }
}

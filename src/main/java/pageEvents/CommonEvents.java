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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }
    public void enterText(WebElement element,String text){
        element.sendKeys(text);
    }
    public void enterTextSelection(WebElement element,String text) throws InterruptedException {
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
        WebDriverWait wait = new WebDriverWait(driver, Timeout);
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
    public void launchURLInANewTab(String url){
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
    }
}

package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class GoogleAutoSuggestionTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept SSL certificate warnings
        options.addArguments("--disable-blink-features=AutomationControlled"); // hides automation
        options.addArguments("--disable-infobars"); // removes Chrome infobar
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleAutoSuggestion() {
        driver.get("https://www.google.com");

        // Type in search box
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("physio");

        // FluentWait setup
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // max wait time
                .pollingEvery(Duration.ofMillis(500))       // check every 500ms
                .ignoring(NoSuchElementException.class);    // ignore these exceptions

        // Wait until suggestions are visible
        List<WebElement> suggestions = wait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(By.xpath("//ul[@role='listbox']//li//span"));
                return elements.size() > 0 ? elements : null;
            }
        });

        // Desired suggestion
        String valueToSelect = "physiotherapy near me";
        boolean found = false;
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase(valueToSelect)) {
                suggestion.click();
                found = true;
                break;
            }
        }

        // Assertion to ensure the suggestion was found and clicked
        Assert.assertTrue(found, "The desired suggestion '" + valueToSelect + "' was not found.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

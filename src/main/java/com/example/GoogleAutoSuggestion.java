package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class GoogleAutoSuggestion {
    public static void main(String[] args) {
       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept SSL certificate warnings
        options.addArguments("--disable-blink-features=AutomationControlled"); // hides automation
        options.addArguments("--disable-infobars"); // removes Chrome infobar
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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
                if (elements.size() > 0) {
                    return elements;
                } else {
                    return null;
                }
            }
        });

        // Select the desired suggestion
        String valueToSelect = "physiotherapy near me";
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase(valueToSelect)) {
                suggestion.click();
                break;
            }
        }

        // Optional: Close browser after 5 seconds
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
        driver.quit();
    }
}

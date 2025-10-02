package com.ajax.exercises;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class GoogleAutoSuggestion{
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Google
        driver.get("https://www.google.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(55));
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //span[contains(text(),'Stay')] ")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("selenium");
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[@role='listbox']//li")));

        for (WebElement suggestion : suggestions) {

            if (suggestion.getText().toLowerCase().equalsIgnoreCase("selenium benefits")) {
                Thread.sleep(5000);
                System.out.println(suggestion.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", suggestion);
                break;


            }
        }


/*
        // Wait for autosuggestions to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent()); // wait for alert
            Alert alert = driver.switchTo().alert();
            System.out.println("Popup found: " + alert.getText());
            alert.accept(); // or alert.dismiss()
        } catch (TimeoutException e) {
            System.out.println("No popup appeared.");
        }
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[@role='listbox']//li//div[@role='option']//span")
                )
        );

        // Print all suggestions
        System.out.println("Autosuggestions found:");
        for (WebElement suggestion : suggestions) {
            System.out.println(suggestion.getText());
        }

        // Click a specific suggestion (example: "selenium webdriver")
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase("selenium webdriver")) {
                suggestion.click();
                break;
            }
        }

        // Verify page title contains selected suggestion
        String pageTitle = driver.getTitle();
        if (pageTitle.toLowerCase().contains("selenium webdriver")) {
            System.out.println("Test Passed: Correct suggestion clicked");
        } else {
            System.out.println("Test Failed: Wrong suggestion selected");
        }

        // Close browser
        driver.quit();*/
    }
}

package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

public class MultipleWindows {
    public static void main(String[] args) throws InterruptedException {
        // Set path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept SSL certificate warnings
        options.addArguments("--disable-blink-features=AutomationControlled"); // hides automation
        options.addArguments("--disable-infobars"); // removes Chrome infobar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriver driver = new ChromeDriver(options);

        try {
            // 1. Navigate to Google
            driver.get("https://www.google.com");

            // Store the original window handle
            String originalWindow = driver.getWindowHandle();

            // 2. Open new window
            ((ChromeDriver) driver).switchTo().newWindow(org.openqa.selenium.WindowType.WINDOW);

            // 3. Navigate to fidelity.com in new window
            driver.get("https://www.fidelity.com");

            Thread.sleep(2000); // optional wait

            // 4. Close new window
            driver.close();

            // 5. Switch back to original Google window
            driver.switchTo().window(originalWindow);

            // 6. Search for "physio" in Google
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("physio");
            searchBox.submit();

            Thread.sleep(3000); // optional wait to see results

        } finally {
            // Close browser
            driver.quit();
        }
    }
}

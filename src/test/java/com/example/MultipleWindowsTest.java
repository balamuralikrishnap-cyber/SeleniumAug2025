package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MultipleWindowsTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept SSL certificate warnings
        options.addArguments("--disable-blink-features=AutomationControlled"); // hides automation
        options.addArguments("--disable-infobars"); // removes Chrome infobar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testMultipleWindows() throws InterruptedException {
        // 1. Navigate to Google
        driver.get("https://www.google.com");

        // Store the original window handle
        String originalWindow = driver.getWindowHandle();

        // 2. Open new window
        driver.switchTo().newWindow(WindowType.WINDOW);

        // 3. Navigate to fidelity.com in new window
        driver.get("https://www.fidelity.com");

        Thread.sleep(2000); // optional wait

        // Optional assertion: check page title contains "Fidelity"
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("fidelity"),
                "Fidelity page did not open correctly");

        // 4. Close new window
        driver.close();

        // 5. Switch back to original Google window
        driver.switchTo().window(originalWindow);

        // 6. Search for "physio" in Google
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("physio");
        searchBox.submit();

        Thread.sleep(3000); // optional wait to see results

        // Optional assertion: check title contains search term
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("physio"),
                "Google search results page did not open correctly");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

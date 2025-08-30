package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MultipleWindowsTest {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriver getDriver() {
        return tlDriver.get();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Accept SSL certificate warnings
        options.addArguments("--disable-blink-features=AutomationControlled"); // hides automation
        options.addArguments("--disable-infobars"); // removes Chrome infobar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        tlDriver.set(driver);
    }

    @Test
    public void testMultipleWindows() throws InterruptedException {
        WebDriver driver = getDriver();

        // 1. Navigate to Google
        driver.get("https://www.google.com");

        // Store the original window handle
        String originalWindow = driver.getWindowHandle();

        // 2. Open new window
        driver.switchTo().newWindow(WindowType.WINDOW);

        // 3. Navigate to fidelity.com in new window
        driver.get("https://www.fidelity.com");

        Thread.sleep(2000); // optional wait

        // Optional assertion
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

        Thread.sleep(3000); // optional wait

        // Optional assertion
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("physio"),
                "Google search results page did not open correctly");
    }

    @AfterMethod
    public void tearDown() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}

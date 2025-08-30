package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class Dropdown {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriver getDriver() {
        return tlDriver.get();
    }

    @BeforeMethod
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        tlDriver.set(driver);
    }

    @Test
    public void testDropdown() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);

        // Select by value
        select.selectByValue("2");
        Thread.sleep(1000);

        // Select by index (0-based)
        select.selectByIndex(1);

        // Select by visible text
        select.selectByVisibleText("Two");
    }

    @AfterMethod
    public void tearDown() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}

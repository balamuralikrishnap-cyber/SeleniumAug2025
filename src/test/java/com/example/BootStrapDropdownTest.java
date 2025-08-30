package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class BootStrapDropdownTest {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> tlWait = new ThreadLocal<>();

    private WebDriver getDriver() {
        return tlDriver.get();
    }

    private WebDriverWait getWait() {
        return tlWait.get();
    }

    @BeforeMethod
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        tlDriver.set(driver);
        tlWait.set(wait);
    }

    @Test
    public void testBootstrapDropdown() {
        WebDriver driver = getDriver();
        WebDriverWait wait = getWait();

        driver.get("https://getbootstrap.com/docs/4.0/components/dropdowns/");

        WebElement dropdownBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='dropdown']/button)[1]"))
        );
        dropdownBtn.click();

        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("(//div[@class='dropdown-menu show'])[1]/a"))
        );

        System.out.println("Dropdown options:");
        for (WebElement opt : options) {
            System.out.println(" - " + opt.getText());
        }

        boolean optionFound = false;
        for (WebElement opt : options) {
            if (opt.getText().trim().equals("Another action")) {
                opt.click();
                optionFound = true;
                break;
            }
        }

        Assert.assertTrue(optionFound, "'Another action' option should be present in the dropdown");
    }

    @AfterMethod
    public void tearDown() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
            tlWait.remove();
        }
    }
}

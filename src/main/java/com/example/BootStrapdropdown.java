package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
public class BootStrapdropdown
{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testBootstrapDropdown() {
        driver.get("https://getbootstrap.com/docs/4.0/components/dropdowns/");

        // Locate dropdown button
        WebElement dropdownBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='dropdown']/button)[1]"))
        );
        dropdownBtn.click();

        // Get all dropdown options
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("(//div[@class='dropdown-menu show'])[1]/a"))
        );

        System.out.println("Dropdown options:");
        for (WebElement opt : options) {
            System.out.println(" - " + opt.getText());
        }

        // Select option dynamically (e.g., "Another action")
        for (WebElement opt : options) {
            if (opt.getText().trim().equals("Another action")) {
                opt.click();
                break;
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

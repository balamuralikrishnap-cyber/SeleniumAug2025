package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

public class MultiSelectDropdownTest {

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
    public void testMultiSelectDropdown() throws InterruptedException {
        WebDriver driver = getDriver();

        // Navigate to sample page
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

        // Switch to iframe
        driver.switchTo().frame("iframeResult");

        // Locate multi-select element
        WebElement multiSelectElement = driver.findElement(By.name("cars"));
        Select multiSelect = new Select(multiSelectElement);

        // Assert that the select element allows multiple selections
        Assert.assertTrue(multiSelect.isMultiple(), "Dropdown is not multi-select");

        // Select options
        multiSelect.selectByVisibleText("Volvo");
        multiSelect.selectByValue("opel");

        Thread.sleep(1000); // optional wait

        // Verify selected options
        List<String> selectedTexts = multiSelect.getAllSelectedOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(selectedTexts.contains("Volvo"), "Volvo not selected");
        Assert.assertTrue(selectedTexts.contains("Opel"), "Opel not selected");

        // Deselect an option
        multiSelect.deselectByVisibleText("Volvo");
        Thread.sleep(1000);

        selectedTexts = multiSelect.getAllSelectedOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertFalse(selectedTexts.contains("Volvo"), "Volvo should be deselected");

        // Deselect all options
        multiSelect.deselectAll();
        Assert.assertTrue(multiSelect.getAllSelectedOptions().isEmpty(), "All options should be deselected");
    }

    @AfterMethod
    public void tearDown() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}

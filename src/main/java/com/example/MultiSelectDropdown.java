package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MultiSelectDropdown {
    public static void main(String[] args) throws InterruptedException {
        // Set driver path if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Sample page with multi-select dropdown
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

        // Switch to iframe (W3Schools example is inside iframe)
        driver.switchTo().frame("iframeResult");

        // Locate multi-select element
        WebElement multiSelectElement = driver.findElement(By.name("cars"));

        // Create Select object
        Select multiSelect = new Select(multiSelectElement);

        // Check if it’s multi-select
        System.out.println("Is multiple? " + multiSelect.isMultiple());

        // Select options
        multiSelect.selectByVisibleText("Volvo");
        multiSelect.selectByValue("opel");
        multiSelect.selectByIndex(2); // Saab

        Thread.sleep(2000);

        // Get all selected options
        List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
        System.out.println("Selected options:");
        for (WebElement option : selectedOptions) {
            System.out.println(option.getText());
        }

        // Deselect an option
        multiSelect.deselectByVisibleText("Volvo");
        Thread.sleep(2000);

        // Deselect all
        multiSelect.deselectAll();

        driver.quit();
    }
}

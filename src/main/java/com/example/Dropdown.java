package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {

    @Test
    public void testDropdown() throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);

        // Select by visible text
       // select.selectByVisibleText("Option 2");

        // Select by value
        select.selectByValue("2");
        Thread.sleep(30000);

        // Select by index (0-based)
        select.selectByIndex(1);
        select.selectByVisibleText("Two");



    }
}

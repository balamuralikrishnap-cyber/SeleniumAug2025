package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LocateAndInteractElements {

    //https://demoqa.com/text-box

    @Test
    public void testElementInteraction()
    {
        WebDriver driver= new ChromeDriver();
        driver.get("https://demoqa.com/text-box");

        WebElement fullName=driver.findElement(By.xpath(" //input[contains(@placeholder,'Full')]"));
        fullName.clear();
        fullName.sendKeys("Bala");


    }
}

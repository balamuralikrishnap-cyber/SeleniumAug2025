package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ValidatePageTitle {
    @Test
    public void testPageTitle()
    {
        WebDriver driver= new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

    }
}

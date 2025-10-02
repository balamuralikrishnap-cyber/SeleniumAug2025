package com.ajax.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class AjaxDynamicLoading5 {
    @Test
    public void testAddAndRemoveCheckbox()
    {
        WebDriver driver= new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//input[contains(@label,'blah')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='checkbox-example']/p[@id='message']")));
        System.out.println(driver.findElement(By.xpath("//form[@id='checkbox-example']/p[@id='message']")).getText());

    }
}

package com.ajax.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class AjaxDynamicLoading4 {
    @Test
    public void testWaitForElementToBeClickable()
    {
        WebDriver driver= new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[contains(text(),'Enable')]")).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement textInput=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='input-example']/input")));
        textInput.sendKeys("enabled");




    }
}

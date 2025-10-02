package com.ajax.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class AjaxDynamicLoading1 {
    @Test
    public  void testAjaxDynamicLoading1()
    {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        // navigate to https://the-internet.herokuapp.com/dynamic_loading/1
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        // clik on start button with xpath //div[@id='start']/button[text()='Start']
        driver.findElement(By.xpath("//div[@id='start']/button[text()='Start']")).click();
        // finish text
        WebElement finshText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        System.out.println(finshText.getText());
        System.out.println(finshText.getDomProperty("innerText"));



    }
}

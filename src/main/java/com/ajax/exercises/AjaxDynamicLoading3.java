package com.ajax.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class AjaxDynamicLoading3 {
    @Test
    public void  ajaxDynamicLoading3()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/ajax");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//button[@id='ajaxButton']")).click();
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));
        WebElement content=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/p")));
        System.out.println(content.getDomProperty("innerText"));





    }
}

package com.ajax.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AjaxDynamicLoading6 {
    @Test
    public void testYahooSearchAutoSuggestions()
    {
        WebDriver driver=new ChromeDriver();
        driver.get("https://search.yahoo.com/");
        driver.findElement(By.xpath("//input[@name='p']")).sendKeys("selenium");
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> eleList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='sa-list']/li[contains(@class,'sa-item')]/span[contains(@class,'sa-item')]")));
        for(int i=0;i<Math.min(3,eleList.size());i++)
            System.out.println(eleList.get(i).getText());

    }

}

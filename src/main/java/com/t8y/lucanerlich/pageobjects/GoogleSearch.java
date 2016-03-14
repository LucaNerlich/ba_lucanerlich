package com.t8y.lucanerlich.pageobjects;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by lucan on 11.03.2016.
 */
public class GoogleSearch extends Base {
    public GoogleSearch(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
    }

    public void searchFor(String term){
        visit("http://www.google.com");
        WebElement element = find(By.name("q"));
        element.sendKeys("term");
        element.submit();
    }
}

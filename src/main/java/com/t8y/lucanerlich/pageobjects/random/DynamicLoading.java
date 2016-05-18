package com.t8y.lucanerlich.pageobjects.random;

import com.t8y.lucanerlich.pageobjects.Base;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading extends Base {
    By startButton = By.cssSelector("#start button");
    //By startButton = By.id("start");
    By finishText = By.id("finish");

    public DynamicLoading(WebDriver driver, BrowserMobProxy proxy) {
        super(proxy, driver);
    }

    public void loadExample(String exampleNumber) {
        visit("http://the-internet.herokuapp.com/dynamic_loading/" + exampleNumber);
        click(startButton);
    }

    public Boolean finishTextPresent() {
        return waitForIsDisplayed(finishText, 10);
    }
}

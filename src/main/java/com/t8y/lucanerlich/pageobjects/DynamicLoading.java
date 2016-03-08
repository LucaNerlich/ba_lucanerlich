package com.t8y.lucanerlich.pageobjects;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading extends Base {
    By startButton = By.cssSelector("#start button");
    By finishText = By.id("finish");

    public DynamicLoading(WebDriver driver, BrowserMobProxy proxy) {
        super(proxy, driver);
    }

    public void loadExample(String exampleNumber) {
        visit("/dynamic_loading/" + exampleNumber);
        click(startButton);
    }

    public Boolean finishTextPresent() {
        return waitForIsDisplayed(finishText, 10);
    }
}

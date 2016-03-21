package com.t8y.lucanerlich.pageobjects.webspecial.volkswagen.de;

import com.t8y.lucanerlich.pageobjects.Base;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by lnerlich on 21.03.2016.
 */
public class Video extends Base {

    By videoBox = By.id("mep_0");

    public Video(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
        visit("webspecial.volkswagen.de/more-than-a-car/com/en");

        waitForIsDisplayed(videoBox, 10);
        WebElement imageBox = driver.findElement(By.className("image-wrapper"));
        System.out.println(imageBox.getLocation());

        //WebElement videoPlayButton = find(By.className("mejs-button mejs-playpause-button mejs-play"));
    }

    public void playVideo() {
        click(videoBox);
    }
}

package com.t8y.lucanerlich.pageobjects.webspecial.volkswagen.de;

import com.t8y.lucanerlich.pageobjects.Base;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lnerlich on 21.03.2016.
 */
public class Video extends Base {

    By videoBox = By.id("mep_0");

    public Video(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
        visit("http://webspecial.volkswagen.de/more-than-a-car/com/en");
    }

    public void playVideo() {
        click(videoBox);
    }
}

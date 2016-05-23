package com.t8y.lucanerlich.pageobjects.volkswagencarnet.com;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lucan on 23.05.2016.
 */
public class Kundenportal extends CarNetBase {

    public Kundenportal(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
    }

    public void visitKundenportal(){
        //click "Kundenportal"
        visitCarNetHome();
        click(By.xpath("/html/body/header/div[2]/ul[2]/li[4]/a"));
    }
}

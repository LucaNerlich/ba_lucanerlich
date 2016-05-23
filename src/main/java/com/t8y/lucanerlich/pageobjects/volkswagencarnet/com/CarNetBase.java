package com.t8y.lucanerlich.pageobjects.volkswagencarnet.com;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;

/**
 * Created by lucan on 23.05.2016.
 */
public class CarNetBase extends com.t8y.lucanerlich.pageobjects.Base {

    String baseUrlCarNet = "http://volkswagen-carnet.com/de/de/start.html";

    public CarNetBase(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
    }

    public void visitCarNetHome(){
        visit(baseUrlCarNet);
    }

    public String getBaseUrlCarNet() {
        return baseUrlCarNet;
    }

    public void setBaseUrlCarNet(String baseUrlCarNet) {
        this.baseUrlCarNet = baseUrlCarNet;
    }
}

package com.t8y.lucanerlich;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by lnerlich on 09.02.2016.
 */
public class Main {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");

        //closes the Browser
        driver.quit();
    }
}

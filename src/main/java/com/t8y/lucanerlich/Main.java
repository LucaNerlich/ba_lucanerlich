package com.t8y.lucanerlich;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by lnerlich on 09.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        File pathToBinary = new File("C:\\Program Files (x86)\\Firefox Developer Edition\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
        driver.get("http://www.google.com");
    }
}

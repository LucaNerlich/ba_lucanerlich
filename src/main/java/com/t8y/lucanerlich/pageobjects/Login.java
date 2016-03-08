package com.t8y.lucanerlich.pageobjects;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class Login extends Base {

    By usernameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By loginFormLocator = By.id("login");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    public Login(BrowserMobProxy proxy, WebDriver driver) {
        super(proxy, driver);
        visit("/login");
        assertTrue("The login form is not present", isDisplayed(loginFormLocator));
    }

    public void with(String username, String password) {
        type(username, usernameLocator);
        type(password, passwordLocator);
        submit(loginFormLocator);
    }

    public Boolean successMessagePresent() {
        return isDisplayed(successMessageLocator);
    }

    public Boolean failureMessagePresent() {
        return isDisplayed(failureMessageLocator);
    }

    public Boolean httpStatusOfPageIs200() {
        return getStatusOfHttpResponse(".*/login") == 200;
    }

}

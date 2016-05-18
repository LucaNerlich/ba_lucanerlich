package com.t8y.lucanerlich.pageobjects;

import com.t8y.lucanerlich.tests.Config;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

public class Base implements Config {

    private BrowserMobProxy proxy = new BrowserMobProxyServer();
    private WebDriver webDriver;

    public Base(BrowserMobProxy proxy, WebDriver driver) {
        this.proxy = proxy;
        this.webDriver = driver;
    }

    public void visit(String url) {
        // First, open URL...
        if (url.contains("http")) {
            webDriver.get(url);
        } else {
            webDriver.get(baseUrl + url);
        }

        // ... after that, check whether the page is showing a phishing warning (iOS) and if so, click the ignore link
        if (webDriver.getTitle().contains("Possible Phishing Site")) {
            System.out.println("The page is showing a phishing warning on iOS ");
            click(By.id("ignore_this_warning"));
        }
    }

    public WebElement find(By locator) {
        return webDriver.findElement(locator);
    }

    public void click(By locator) {
        if ((osType.equals("desktop")) && (browser.equals("IE")) && (browser_version.equals("9"))) {
            // IE9 workaround
            find(locator).sendKeys("");
            find(locator).sendKeys(Keys.chord(Keys.ENTER));
        } else {
            find(locator).click();
        }
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public void submit(By locator) {
        find(locator).submit();
    }

    public String textOf(By locator) {
        return find(locator).getText();
    }

    public void switchContextToiFrame(By locator) {
        webDriver.switchTo().frame(find(locator));
    }

    public String getClassName(By locator) {
        return webDriver.findElement(locator).getAttribute("class");
    }

    public Boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    // Wait for specified element for the specified amount of time
    public Boolean waitForIsDisplayed(By locator, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    // Used by waitForIsDisplayed, default wait time is 5sec
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(condition);
    }

    // Wait for specified string to appear as part of the URL
    // Comes in handy when the page under test appears only after one or more redirects
    public Boolean waitForUrlPart(final String urlPart, Integer... timeout) {
        try {
            waitForUrl((new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getCurrentUrl().contains(urlPart);
                }
            }), (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException exeption) {
            return false;
        }
        return true;
    }

    // Used by waitForUrlPart
    private void waitForUrl(ExpectedCondition<Boolean> urlContainsExpectedPart, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(urlContainsExpectedPart);
    }

    // Wait for specified string to appear as class name of the specified locator
    // Comes in handy on Javascript-heavy pages
    public Boolean waitForClassName(final By locator, final String className, Integer... timeout) {
        try {
            waitForClassName((new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return getClassName(locator).equals(className);
                }
            }), (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    // Used by waitForClassName
    private void waitForClassName(ExpectedCondition<Boolean> classNameContainsString, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(classNameContainsString);
    }

    // Diese Methode fragt den HTTP Status eines Requests ab. Setzt voraus, dass Browsermob Proxy gestartet wurde
    public long getStatusOfHttpResponse(String requestUrlRegExp) {
        for (HarEntry harEntry : proxy.getHar().getLog().getEntries()) {
            HarRequest harRequest = harEntry.getRequest();
            HarResponse harResponse = harEntry.getResponse();
            String requestUrl = harRequest.getUrl();
            if (requestUrl.matches(requestUrlRegExp)) {
                return (harResponse.getStatus());
            }
        }
        fail("URL " + requestUrlRegExp + " ist in den geladenen Ressourcen nicht vorhanden");
        return Long.parseLong(null);
    }

    // Diese Methode gibt den Content eines HTTP Response zurück
    public String getContentOfHttpResponse(String requestUrlRegExp) {
        for (HarEntry harEntry : proxy.getHar().getLog().getEntries()) {
            HarRequest harRequest = harEntry.getRequest();
            HarResponse harResponse = harEntry.getResponse();
            String requestUrl = harRequest.getUrl();
            if (requestUrl.matches(requestUrlRegExp)) {
                return (harResponse.getContent().getText());
            }
        }
        fail("URL " + requestUrlRegExp + " ist in den geladenen Ressourcen nicht vorhanden");
        return null;
    }

}

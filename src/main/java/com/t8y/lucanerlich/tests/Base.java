package com.t8y.lucanerlich.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import net.sf.randomjunit.RandomTestRunner;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(RandomTestRunner.class)
public class Base implements Config {

    protected BrowserMobProxy proxy = new BrowserMobProxyServer();
    protected WebDriver driver;
    private String testName;

    @Rule
    public ExternalResource resource = new ExternalResource() {

        @Override
        protected void before() throws Throwable {

            // DesiredCapabilities capabilities is needed for Browserstack and Browsermob (oh, and even the local Webdriver instances)
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Browsermob....
            // ... define whitelist
            List<String> allowUrlPatterns = new ArrayList<String>();
            allowUrlPatterns.add("http://the-internet.herokuapp.com.*");
            // all URLs matching the above defined patterns blocked with a 404
            proxy.whitelistRequests(allowUrlPatterns, 404);

            // ... start the proxy, get the Selenium proxy object, configure it as a desired capability
            // ... plus, create a HAR object and configure which information is being captured in it
            HashSet<CaptureType> enable = new HashSet<CaptureType>();
            enable.add(CaptureType.RESPONSE_HEADERS);
            enable.add(CaptureType.RESPONSE_CONTENT);
            enable.add(CaptureType.REQUEST_HEADERS);
            //enable.add(CaptureType.RESPONSE_BINARY_CONTENT);
            //enable.add(CaptureType.RESPONSE_COOKIES);
            proxy.enableHarCaptureTypes(enable);
            HashSet<CaptureType> disable = new HashSet<CaptureType>();
            //disable.add(CaptureType.RESPONSE_HEADERS);
            //disable.add(CaptureType.RESPONSE_CONTENT);
            disable.add(CaptureType.RESPONSE_BINARY_CONTENT);
            disable.add(CaptureType.RESPONSE_COOKIES);
            proxy.disableHarCaptureTypes(disable);
            // proxy.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
            proxy.start(0);
            proxy.newHar(testName);
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            if (host.equals("browserstack")) {

                // First, the Browserstack credentials are read from credentials.properties...
                Properties properties = new Properties();
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream("credentials.properties"));
                try {
                    properties.load(stream);
                    stream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String browserstackUser = properties.getProperty("browserstackUser");
                String browserstackKey = properties.getProperty("browserstackKey");
                String browserstackUrl = String.format("http://%s:%s@hub.browserstack.com/wd/hub", browserstackUser, browserstackKey);

                // ... then the actual Browserstack setup takes place

                capabilities.setCapability("project", project);
                capabilities.setCapability("build", build);
                capabilities.setCapability("name", testName);
                capabilities.setCapability("browserstack.debug", browserstackDebug);
                capabilities.setCapability("browserstack.video", browserstackVideo);
                capabilities.setCapability("acceptSslCerts", acceptSslCerts);
                capabilities.setCapability("browserstack.local", browserstackLocal);

                if (osType.equals("desktop")) {
                    capabilities.setCapability("browser", browser);
                    capabilities.setCapability("browser_version", browser_version);
                    capabilities.setCapability("os", os);
                    capabilities.setCapability("os_version", os_version);
                    capabilities.setCapability("resolution", resolution);
                    driver = new RemoteWebDriver(new URL(browserstackUrl), capabilities);
                    driver.manage().window().setSize(new Dimension(Integer.parseInt(windowWidth), Integer.parseInt(windowHeight)));
                    System.out.println(capabilities.getCapability(seleniumProxy.toString()));

                } else if (osType.equals("mobile")) {
                    capabilities.setCapability("browserName", browserName);
                    capabilities.setCapability("platform", platform);
                    capabilities.setCapability("device", device);
                    capabilities.setCapability("deviceOrientation", deviceOrientation);
                    driver = new RemoteWebDriver(new URL(browserstackUrl), capabilities);

                }

            } else if (host.equals("localhost")) {
                if (browser.equals("Firefox")) {
                    driver = new FirefoxDriver(capabilities);
                } else if (browser.equals("Chrome")) {
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver");
                    driver = new ChromeDriver(capabilities);
                } else if (browser.equals("Safari")) {
                    driver = new SafariDriver(capabilities);
                }

                driver.manage().window().setSize(new Dimension(Integer.parseInt(windowWidth), Integer.parseInt(windowHeight)));

            }

        }

        @Override
        protected void after() {

            // Browsermob
            // get the HAR data and write it to a file
            Har har = proxy.getHar();
            try {
                String directory = "har-files/";
                new File(directory).mkdirs();
                har.writeTo(new File(directory + testName + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date()) + ".json"));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            proxy.stop();

            driver.quit();
        }

    };

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            testName = description.getDisplayName();
        }
    };

}
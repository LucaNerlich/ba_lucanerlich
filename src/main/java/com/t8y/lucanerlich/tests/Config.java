package com.t8y.lucanerlich.tests;

// All these properties have default values and can be changed by using -D arguments
// Firefox v47+: https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette/WebDriver

public interface Config {
    String baseUrl = System.getProperty("baseUrl", "http://");
    String host = System.getProperty("host", "localhost");
    String browser = System.getProperty("browser", "Firefox");
    String windowWidth = System.getProperty("windowWidth", String.valueOf(1280));
    String windowHeight = System.getProperty("windowHeight", String.valueOf(1024));

    // ... Allgemein
    String project = System.getProperty("project", "Testprojekt");
    String build = System.getProperty("build", "Build 1");
    String browserstackDebug = System.getProperty("browserstack.debug", "false");
    String browserstackVideo = System.getProperty("browserstack.video", "false");
    String acceptSslCerts = System.getProperty("acceptSslCerts", "true");
    String browserstackLocal = System.getProperty("browserstack.local", "false");

    // ... Desktop
    String osType = System.getProperty("osType", "desktop");
    String browser_version = System.getProperty("browser_version", "45.0.1");
    String os = System.getProperty("os", "Windows");
    String os_version = System.getProperty("os_version", "7");
    String resolution = System.getProperty("resolution", "1920x1080");

    // ... Mobile
    String browserName = System.getProperty("browserName", "iPhone");
    String platform = System.getProperty("platform", "MAC");
    String device = System.getProperty("device", "iPhone 5S");
    String deviceOrientation = System.getProperty("deviceOrientation", "portrait");
}

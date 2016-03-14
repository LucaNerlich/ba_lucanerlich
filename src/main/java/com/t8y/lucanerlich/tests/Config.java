package com.t8y.lucanerlich.tests;

// All these properties have default values and can be changed by using -D arguments

public interface Config {
    final String baseUrl = System.getProperty("baseUrl", "http://");
    final String host = System.getProperty("host", "localhost");
    final String browser = System.getProperty("browser", "Firefox");
    final String windowWidth = System.getProperty("windowWidth", String.valueOf(1280));
    final String windowHeight = System.getProperty("windowHeight", String.valueOf(1024));

    // ... Allgemein
    final String project = System.getProperty("project", "Testprojekt");
    final String build = System.getProperty("build", "Build 1");
    final String browserstackDebug = System.getProperty("browserstack.debug", "false");
    final String browserstackVideo = System.getProperty("browserstack.video", "false");
    final String acceptSslCerts = System.getProperty("acceptSslCerts", "true");
    final String browserstackLocal = System.getProperty("browserstack.local", "false");

    // ... Desktop
    final String osType = System.getProperty("osType", "desktop");
    final String browser_version = System.getProperty("browser_version", "39.0");
    final String os = System.getProperty("os", "Windows");
    final String os_version = System.getProperty("os_version", "7");
    final String resolution = System.getProperty("resolution", "1920x1080");

    // ... Mobile
    final String browserName = System.getProperty("browserName", "iPhone");
    final String platform = System.getProperty("platform", "MAC");
    final String device = System.getProperty("device", "iPhone 5S");
    final String deviceOrientation = System.getProperty("deviceOrientation", "portrait");
}

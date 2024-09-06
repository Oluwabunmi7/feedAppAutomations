package com.bptn.feedAppAutomation.web;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bptn.feedAppAutomation.provider.ResourceProvider;

import jakarta.annotation.PreDestroy;

@Component
public class DriverManager {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private WebDriver driver;
	
	private boolean initialized;
	
    @Autowired
	ResourceProvider provider;
	

    private void createWebDriver() {
    	// Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver","/Users/sweythakannan/Downloads/chromedriver-mac-arm64/chromedriver");

						
		this.driver = switch( this.provider.getBrowser() ) {
            case "chrome"  -> new ChromeDriver();
			case "edge"    -> new EdgeDriver();
			case "firefox" -> this.getFirefoxDriver();
			default -> throw new IllegalArgumentException("Invalid browser: " + this.provider.getBrowser());
		};

		this.initialized = true;

		logger.debug("Selenium WebDriver Created! - {}", StringUtils.capitalize(this.provider.getBrowser()));	
    }
	
	private WebDriver getFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        firefoxOptions.setCapability("proxy", proxy);
        return new FirefoxDriver(firefoxOptions);
	}

	public WebDriver getDriver() {
		
		if (!this.initialized) {
			this.createWebDriver();
		}
		
		return this.driver;
	}

	/*
	 * Creates a new WebDriver 
	 */
	public WebDriver getDriver(boolean newDriver) {
		
		if (newDriver) {
			this.closeDriver();
		}
		
		return this.getDriver();
	}

	public void closeDriver() {
	
		if (this.initialized) {
			this.driver.quit();
		}
		
		this.initialized = false;
	}
	
    @PreDestroy
    public void preDestroy() {
        this.closeDriver();
    }
	
}